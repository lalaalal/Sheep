package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Main;
import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.exception.ExpressionError;
import com.lalaalal.sheep.sheet.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellReferenceTest {
    @BeforeEach
    void setUp() {
        Main.initialize();
    }

    @Test
    void parseCellReference() throws ExpressionError, CalculationError {
        Sheet sheet = new Sheet();
        sheet.setText("A1", "=2+1");
        Operand operand = Expression.parseExpression("A1");
        System.out.println(operand.calculate(sheet));
    }
}