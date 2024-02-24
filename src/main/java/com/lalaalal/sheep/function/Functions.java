package com.lalaalal.sheep.function;

import com.lalaalal.sheep.expression.*;
import com.lalaalal.sheep.sheet.SheetReader;

import java.util.HashMap;

public class Functions {
    private static final HashMap<String, Function> registry = new HashMap<>();

    public static void register(String name, Function function) {
        registry.put(name, function);
    }

    public static Function get(String name) {
        return registry.get(name);
    }

    public static double calculate(String name, SheetReader sheet, Operand[] parameters) {
        return get(name).calculate(sheet, parameters).toDouble();
    }

    public static void initialize() {
        register("ADD", new OperationFunction(Double::sum));
        register("SUBTRACT", new OperationFunction((a, b) -> a - b));
        register("MULTIPLE", new OperationFunction((a, b) -> a * b));
        register("DIVIDE", new OperationFunction((a, b) -> a / b));

        register("SUM", (sheet, parameters) -> {
            double sum = 0;
            for (Operand operand : parameters) {
                if (operand instanceof CellRange range) {
                    for (CellReference cellReference : range)
                        sum += cellReference.calculate(sheet).toDouble();
                } else
                    sum += operand.calculate(sheet).toDouble();
            }
            return new Literal(sum);
        });
        register("AVERAGE", (sheet, parameters) -> {
            double sum = calculate("SUM", sheet, parameters);
            return new Literal(sum / Operation.getActualParameterNumber(parameters));
        });
    }
}
