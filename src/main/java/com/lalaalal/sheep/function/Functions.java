package com.lalaalal.sheep.function;

import com.lalaalal.sheep.expression.CellRange;
import com.lalaalal.sheep.expression.CellReference;
import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;

import java.util.HashMap;

public class Functions {
    private static final HashMap<String, Function> registry = new HashMap<>();

    public static void register(String name, Function function) {
        registry.put(name, function);
    }

    public static Function get(String name) {
        return registry.get(name);
    }

    public static void initialize() {
        register("ADD", createSimpleFunction(Double::sum));
        register("SUBTRACT", createSimpleFunction((a, b) -> a - b));
        register("MULTIPLE", createSimpleFunction((a, b) -> a * b));
        register("DIVIDE", createSimpleFunction((a, b) -> a / b));

        register("SUM", (sheet, operands) -> {
            double sum = 0;
            for (Operand operand : operands) {
                if (operand instanceof CellRange range) {
                    for (CellReference cellReference : range)
                        sum += cellReference.calculate(sheet).toDouble();
                } else
                    sum += operand.calculate(sheet).toDouble();
            }
            return new Literal(sum);
        });
    }

    private static Function createSimpleFunction(SimpleFunction simpleFunction) {
        return (sheet, operands) -> {
            double a = operands[0].calculate(sheet).toDouble();
            double b = operands[1].calculate(sheet).toDouble();
            return new Literal(simpleFunction.calculate(a, b));
        };
    }

    private interface SimpleFunction {
        double calculate(double a, double b);
    }
}
