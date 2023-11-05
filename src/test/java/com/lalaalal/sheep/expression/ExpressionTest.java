package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Main;
import com.lalaalal.sheep.exception.ExpressionError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpressionTest {
    @BeforeEach
    void setUp() {
        Main.initialize();
    }

    @Test
    void parseExpression() throws ExpressionError {
        Operand operand = Expression.parseExpression("1+2*3+MULTIPLE(ADD(1*SUM(1,0),0+1),1+1)+SUM(1,2,3,4)");
        System.out.println(operand.calculate(null));
    }
}