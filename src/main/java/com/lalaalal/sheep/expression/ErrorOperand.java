package com.lalaalal.sheep.expression;

public class ErrorOperand extends Literal {
    public static final String SIMPLE_ERROR_TEXT = "!ERR";

    private final Throwable error;

    public ErrorOperand(Throwable error) {
        super(SIMPLE_ERROR_TEXT, error.toString());
        this.error = error;
    }

    public Throwable getError() {
        return error;
    }
}
