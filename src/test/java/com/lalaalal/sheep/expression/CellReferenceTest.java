package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Main;
import com.lalaalal.sheep.Sheet;
import org.junit.jupiter.api.Test;

class CellReferenceTest {
    static {
        Main.initialize();
    }

    @Test
    void parseCellReference() {
        Sheet sheet = new Sheet();
        sheet.setText("A1", "=2+1");
        Operand operand = Expression.parseExpression("A1");
        System.out.println(operand.calculate(sheet));
    }
}