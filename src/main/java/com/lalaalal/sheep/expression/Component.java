package com.lalaalal.sheep.expression;

public interface Component {
    Operand asOperand(Operand... args);

    int requiredArguments();
}
