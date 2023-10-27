package com.lalaalal.sheep.function;

import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;

@FunctionalInterface
public interface Function {
    Literal calculate(Operand... args);
}
