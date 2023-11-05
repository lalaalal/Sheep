package com.lalaalal.sheep;

import com.lalaalal.sheep.expression.CellRange;
import com.lalaalal.sheep.sheet.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SheetDrawerTest {
    @BeforeEach
    void setUp() {
        Main.initialize();
    }

    @Test
    void draw() {
        Sheet sheet = new Sheet();
        sheet.setText("A1", "1");
        sheet.setText("A2", "2");
        sheet.setText("A3", "3");
        sheet.setText("A4", "=A+A3");
        sheet.setText("A5", "=SUM(A1:A4)");

        SheetDrawer sheetDrawer = new SheetDrawer();
        sheetDrawer.draw(System.out, sheet, CellRange.parseCellRange("A1:C5"));
        sheetDrawer.printDetail(System.out, sheet, "A5");

    }
}