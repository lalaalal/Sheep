package com.lalaalal.sheep.exception;

public class BracketError extends ExpressionError {
    public BracketError(String expression, int errorPosition) {
        super(expression, errorPosition, "Cannot find pair bracket.");
    }
}
