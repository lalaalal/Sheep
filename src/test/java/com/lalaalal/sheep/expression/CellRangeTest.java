package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Main;
import com.lalaalal.sheep.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellRangeTest {
    @BeforeEach
    void setUp() {
        Main.initialize();
    }

    @Test
    void parseCellRange() {
        Sheet sheet = new Sheet();
        sheet.setText("A1", "1");
        sheet.setText("A2", "2");
        sheet.setText("A3", "3");

        Operand operand = Expression.parseExpression("SUM(A1:A3)");
        System.out.println(operand.calculate(sheet));
    }
}