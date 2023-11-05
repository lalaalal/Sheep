package com.lalaalal.sheep.exception;

public class ExpressionError extends Throwable {
    private final String expression;
    private final int errorPosition;

    public ExpressionError(String expression, int errorPosition) {
        this.expression = expression;
        this.errorPosition = errorPosition;
    }

    public ExpressionError(String expression, int errorPosition, Throwable cause) {
        super(cause);
        this.expression = expression;
        this.errorPosition = errorPosition;
    }

    public ExpressionError(String expression, int errorPosition, String message) {
        super(message);
        this.expression = expression;
        this.errorPosition = errorPosition;
    }

    public ExpressionError(String expression, int errorPosition, String message, Throwable cause) {
        super(message, cause);
        this.expression = expression;
        this.errorPosition = errorPosition;
    }

    @Override
    public String toString() {
        return "%s:%d error: %s".formatted(expression, errorPosition, getMessage());
    }
}
