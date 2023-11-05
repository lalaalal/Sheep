package com.lalaalal.sheep.exception;

public class NoSuchFunctionError extends ExpressionError {
    public static String getMessage(String functionName) {
        return "No such function named \"%s\"".formatted(functionName);
    }

    public NoSuchFunctionError(String expression, String functionName) {
        this(expression, 0, functionName);
    }

    public NoSuchFunctionError(String expression, int errorPosition, String functionName) {
        super(expression, errorPosition, getMessage(functionName));
    }
}
