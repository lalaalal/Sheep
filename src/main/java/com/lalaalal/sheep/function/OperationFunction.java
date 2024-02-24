package com.lalaalal.sheep.function;

import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;
import com.lalaalal.sheep.sheet.SheetReader;

public class OperationFunction implements Function {
    private final Calculation calculation;

    public OperationFunction(Calculation calculation) {
        this.calculation = calculation;
    }

    @Override
    public Literal calculate(SheetReader sheet, Operand... parameters) throws CalculationError {
        double a = parameters[0].calculate(sheet).toDouble();
        double b = parameters[1].calculate(sheet).toDouble();

        return new Literal(calculation.calculate(a, b));
    }

    public interface Calculation {
        double calculate(double a, double b);
    }
}
