package com.lalaalal.sheep.exception;

public class CalculationError extends RuntimeException {
    private String expression;

    public CalculationError(String message) {
        super(message);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression + ": " + getMessage();
    }
}
