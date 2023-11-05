package com.lalaalal.sheep.exception;

public class NoSuchOperandError extends ExpressionError {
    protected static String getMessage(String operand) {
        return "Cannot parse \"%s\"".formatted(operand);
    }

    public NoSuchOperandError(String expression, int errorPosition, String operand) {
        super(expression, errorPosition, getMessage(operand));
    }
}
