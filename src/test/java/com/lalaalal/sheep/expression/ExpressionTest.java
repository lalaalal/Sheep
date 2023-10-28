package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Main;
import org.junit.jupiter.api.Test;

class ExpressionTest {
    static {
        Main.initialize();
    }

    @Test
    void parseExpression() {
        Operand operand = Expression.parseExpression("1+2*3+MULTIPLE(ADD(1*SUM(1,0),0+1),1+1)+SUM(1,2,3,4)");
        System.out.println(operand.calculate(null));
    }
}