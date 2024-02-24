package com.lalaalal.sheep.function;

import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;
import com.lalaalal.sheep.sheet.SheetReader;

@FunctionalInterface
public interface Function {
    Literal calculate(SheetReader sheet, Operand... parameters) throws CalculationError;
}
