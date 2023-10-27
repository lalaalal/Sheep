package com.lalaalal.sheep.expression;

import java.util.function.Function;

public class OperandParser {
    private final Function<String, Boolean> checker;
    private final Function<String, Operand> parser;

    public OperandParser(Function<String, Boolean> checker, Function<String, Operand> parser) {
        this.checker = checker;
        this.parser = parser;
    }

    public boolean check(String expression) {
        return checker.apply(expression);
    }

    public Operand parse(String expression) {
        return parser.apply(expression);
    }
}
