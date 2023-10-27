package com.lalaalal.sheep.expression;

@FunctionalInterface
public interface OperationSupplier {
    Operation get(Operand... operands);
}
