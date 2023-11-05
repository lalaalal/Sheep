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
        sheet.setText("BA11", "1");
        sheet.setText("BA12", "2");
        sheet.setText("BA13", "3");
        sheet.setText("BA14", "=BA11+BA13");
        sheet.setText("BA15", "=AVERAGE(BA11:BA14)");

        SheetDrawer sheetDrawer = new SheetDrawer();
        sheetDrawer.draw(System.out, sheet, CellRange.parseCellRange("BA11:BC15"));
        sheetDrawer.printDetail(System.out, sheet, "BA15");

    }
}