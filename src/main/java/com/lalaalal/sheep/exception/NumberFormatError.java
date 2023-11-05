package com.lalaalal.sheep.exception;

public class NumberFormatError extends CalculationError {
    public static String createMessage(String cause) {
        return "Cannot parse \"%s\" to number".formatted(cause);
    }

    public NumberFormatError(String cause) {
        super(createMessage(cause));
    }
}
