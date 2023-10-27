package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.function.Functions;

import java.util.HashMap;

public class Operator implements Component {
    private static final HashMap<Character, Operator> registry = new HashMap<>();

    private final OperationSupplier operationSupplier;
    private final int priority;

    public static void register(char operationCharacter, String functionName) {
        register(operationCharacter, functionName, Integer.MAX_VALUE);
    }

    public static void register(char operationCharacter, String functionName, int priority) {
        registry.put(operationCharacter, new Operator(operands -> new Operation(Functions.get(functionName), operands), priority));
    }

    public static Operator get(char operationCharacter) {
        return registry.get(operationCharacter);
    }

    public static boolean isOperator(char c) {
        return registry.containsKey(c);
    }

    public static void initialize() {
        register('+', "ADD", 2);
        register('-', "SUBTRACT", 2);
        register('*', "MULTIPLE", 1);
        register('/', "DIVIDE", 1);
    }

    private Operator(OperationSupplier operationSupplier, int priority) {
        this.operationSupplier = operationSupplier;
        this.priority = priority;
    }

    @Override
    public Operand asOperand(Operand... args) {
        return operationSupplier.get(args);
    }

    @Override
    public int requiredArguments() {
        return 2;
    }

    public boolean isImportantThan(Operator operator) {
        return this.priority < operator.priority;
    }
}
