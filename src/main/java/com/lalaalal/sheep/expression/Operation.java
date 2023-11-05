package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.exception.ExpressionError;
import com.lalaalal.sheep.exception.NoSuchFunctionError;
import com.lalaalal.sheep.function.Function;
import com.lalaalal.sheep.function.Functions;
import com.lalaalal.sheep.sheet.Sheet;

public class Operation extends Operand {
    private final Function function;
    private final Operand[] parameters;

    public static boolean isFunction(String expression) throws ExpressionError {
        int firstBracketIndex = Expression.findFirstBracketIndex(expression);
        if (firstBracketIndex == expression.length())
            return false;
        for (int index = 0; index < firstBracketIndex; index++) {
            char c = expression.charAt(index);
            if (!isAllowedFunctionNameCharacter(c))
                return false;
        }
        int pairBracketIndex = Expression.findPairBracketIndex(expression, firstBracketIndex);
        return (pairBracketIndex == expression.length() - 1);
    }

    private static boolean isAllowedFunctionNameCharacter(char c) {
        return ('A' <= c && c <= 'Z') || c == '_';
    }

    public static Operation parseFunction(String expression) throws ExpressionError {
        int firstBracket = Expression.findFirstBracketIndex(expression);
        String name = expression.substring(0, firstBracket);
        String parametersString = expression.substring(firstBracket + 1, expression.length() - 1);

        Function function = Functions.get(name);
        if (function == null)
            throw new NoSuchFunctionError(expression, name);
        Operand[] parameters = parseParameters(parametersString);

        return new Operation(function, parameters);
    }

    private static Operand[] parseParameters(String expression) throws ExpressionError {
        if (expression.isEmpty())
            return new Operand[0];
        return parseParameters(expression, 0);
    }

    private static Operand[] parseParameters(String expression, int depth) throws ExpressionError {
        int commaIndex = findNextCommaIndex(expression, 0);
        if (commaIndex == expression.length()) {
            Operand[] parameters = new Operand[depth + 1];
            parameters[depth] = Expression.parseExpression(expression);

            return parameters;
        }

        Operand[] parameters = parseParameters(expression.substring(commaIndex + 1), depth + 1);
        String componentString = expression.substring(0, commaIndex);
        parameters[depth] = Expression.parseExpression(componentString);

        return parameters;
    }

    private static int findNextCommaIndex(String expression, int from) {
        int depth = 0;
        for (int index = from; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (Expression.isOpenBracket(c))
                depth += 1;
            if (Expression.isCloseBracket(c))
                depth -= 1;

            if (depth == 0 && c == ',')
                return index;
        }
        return expression.length();
    }

    public Operation(Function function, Operand[] parameters) {
        this.function = function;
        this.parameters = parameters;
    }

    @Override
    public Literal calculate(Sheet sheet) throws CalculationError {
        return function.calculate(sheet, parameters);
    }
}
