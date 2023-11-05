package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.exception.ExpressionError;

public class OperandParser {
    private final Checker checker;
    private final Parser parser;

    public OperandParser(Checker checker, Parser parser) {
        this.checker = checker;
        this.parser = parser;
    }

    public boolean check(String expression) throws ExpressionError {
        return checker.check(expression);
    }

    public Operand parse(String expression) throws ExpressionError {
        return parser.parse(expression);
    }

    @FunctionalInterface
    public interface Checker {
        boolean check(String expression) throws ExpressionError;
    }

    @FunctionalInterface
    public interface Parser {
        Operand parse(String expression) throws ExpressionError;
    }
}
