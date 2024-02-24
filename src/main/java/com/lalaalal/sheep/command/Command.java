package com.lalaalal.sheep.command;

import com.lalaalal.sheep.SheetDrawer;
import com.lalaalal.sheep.sheet.Sheet;

public interface Command {
    void execute(Sheet sheet, SheetDrawer sheetDrawer, String... args);
}
