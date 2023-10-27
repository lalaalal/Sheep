package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Main;
import org.junit.jupiter.api.Test;

class ExpressionTest {
    static {
        Main.initialize();
    }

    @Test
    void parseExpression() {
        Operand operand = Expression.parseExpression("1+2*3+MULTIPLE(ADD(1,1),2)+SUM(1,2,3,4)");
        System.out.println(operand.calculate());
    }

    @Test
    void parseComponent() {
        Component component = Expression.parseToOperand("ADD(ADD(1,1),1)");

        System.out.println(component.asOperand().calculate());

    }
}