package com.lalaalal.sheep.expression;

public abstract class Operand implements Component {
    @Override
    public Operand asOperand(Operand... args) {
        return this;
    }

    @Override
    public int requiredArguments() {
        return 0;
    }

    public abstract Literal calculate();
}
