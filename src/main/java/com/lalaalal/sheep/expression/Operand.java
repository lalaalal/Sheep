package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Sheet;

public abstract class Operand implements Component {
    @Override
    public final Operand asOperand(Operand... args) {
        return this;
    }

    @Override
    public final int requiredArguments() {
        return 0;
    }

    public abstract Literal calculate(Sheet sheet);
}
