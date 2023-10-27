package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.util.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Function;

public class Expression {
    private static final ArrayList<OperandParser> parsers = new ArrayList<>();

    public static void addParser(Function<String, Boolean> checker, Function<String, Operand> parser) {
        parsers.add(new OperandParser(checker, parser));
    }

    public static void initialize() {
        addParser(Literal::isLiteral, Literal::parseLiteral);
        addParser(Operation::isFunction, Operation::parseFunction);
    }

    public static Operand parseExpression(String expression) {
        int firstOperatorIndex = findNextOperatorIndex(expression, 0);
        Component firstComponent = parseNextComponent(expression, 0);
        BinaryTree<Component> tree = new BinaryTree<>(firstComponent);

        parseExpression(tree, expression, firstOperatorIndex);

        Queue<Component> queue = new LinkedList<>();
        tree.preorderTraverse(queue::add);
        return getOperandFromQueue(queue);
    }

    private static void parseExpression(BinaryTree<Component> tree, String expression, int current) {
        Operator previousOperator = null;

        for (int index = current; index < expression.length(); index++) {
            char c = expression.charAt(index);
            Operator currentOperator = Operator.get(c);
            Component component = parseNextComponent(expression, index + 1);

            addComponentByOperatorPriority(previousOperator, currentOperator, component, tree);
            index = findNextOperatorIndex(expression, index) - 1;
            previousOperator = currentOperator;
        }
    }

    private static Operand getOperandFromQueue(Queue<Component> queue) {
        // TODO: 10/27/23 handle exception
        if (queue.isEmpty())
            throw new IllegalArgumentException();
        Component component = queue.poll();
        int requiredArguments = component.requiredArguments();
        if (requiredArguments <= 0)
            return component.asOperand();
        Operand[] arguments = new Operand[requiredArguments];
        for (int i = 0; i < requiredArguments; i++)
            arguments[i] = getOperandFromQueue(queue);

        return component.asOperand(arguments);
    }

    private static Component parseNextComponent(String expression, int current) {
        char c = expression.charAt(current);
        if (isOpenBracket(c)) {
            int closeBracketIndex = findPairBracketIndex(expression, current);
            return parseExpression(expression.substring(current + 1, closeBracketIndex));
        } else {
            int nextOperatorIndex = findNextOperatorIndex(expression, current);
            return parseToOperand(expression.substring(current, nextOperatorIndex));
        }
    }

    public static boolean isOpenBracket(char c) {
        return c == '(';
    }

    public static boolean isCloseBracket(char c) {
        return c == ')';
    }

    private static void addComponentByOperatorPriority(Operator previous, Operator current, Component component, BinaryTree<Component> tree) {
        if (previous == null) {
            tree.addRoot(current, BinaryTree.MovingOption.LEFT);
            tree.setRight(component);
            return;
        }
        if (current.isImportantThan(previous)) {
            BinaryTree<Component> subTree = new BinaryTree<>(current);
            subTree.setLeft(tree.getRightSubtree());
            subTree.setRight(component);
            tree.setRight(subTree);
        } else {
            tree.addRoot(current, BinaryTree.MovingOption.LEFT);
            tree.setRight(component);
        }
    }

    private static Operand parseToOperand(String expression) {
        for (OperandParser parser : parsers) {
            if (parser.check(expression))
                return parser.parse(expression);
        }

        throw new IllegalArgumentException();
    }

    public static int findFirstBracketIndex(String expression) {
        for (int index = 0; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (isOpenBracket(c))
                return index;
        }

        return expression.length();
    }

    public static int findPairBracketIndex(String expression, int openBracketIndex) {
        for (int index = openBracketIndex + 1; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (isCloseBracket(c))
                return index;
            if (isOpenBracket(c))
                index = findPairBracketIndex(expression, index);
        }

        return openBracketIndex;
    }

    public static int findFirstCharacterIndex(String expression, char searchingCharacter, int from) {
        for (int index = from; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (c == searchingCharacter)
                return index;
        }

        return expression.length();
    }

    private static int findNextOperatorIndex(String expression, int current) {
        int depth = 0;
        for (int index = current + 1; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (isOpenBracket(c))
                depth += 1;
            if (isCloseBracket(c))
                depth -= 1;
            if (depth == 0 && Operator.isOperator(c))
                return index;
        }
        return expression.length();
    }
}


