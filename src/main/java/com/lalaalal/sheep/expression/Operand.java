package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.sheet.SheetReader;

public abstract class Operand implements Component {
    @Override
    public final Operand asOperand(Operand... args) {
        return this;
    }

    @Override
    public final int requiredArguments() {
        return 0;
    }

    public abstract Literal calculate(SheetReader sheet) throws CalculationError;
}
