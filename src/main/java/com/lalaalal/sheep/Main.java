package com.lalaalal.sheep;

import com.lalaalal.sheep.expression.Expression;
import com.lalaalal.sheep.expression.Operator;
import com.lalaalal.sheep.function.Functions;

public class Main {
    public static void initialize() {
        Expression.initialize();
        Functions.initialize();
        Operator.initialize();
    }

    public static void main(String[] args) {
        initialize();
		
    }
}
