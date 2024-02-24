package com.lalaalal.sheep.sheet;

import java.util.HashMap;

public class SheetReader {
    public static final int DEFAULT_ROW_HEIGHT = 1;
    public static final int DEFAULT_COLUMN_WIDTH = 8;

    protected final HashMap<Position, Cell> cells = new HashMap<>();

    protected final HashMap<Integer, Integer> rowHeights = new HashMap<>();
    protected final HashMap<Integer, Integer> columnWidths = new HashMap<>();

    public Cell getCell(Position position) {
        if (cells.containsKey(position))
            return cells.get(position);
        return Cell.EMPTY;
    }

    public int getRowHeight(int row) {
        return rowHeights.getOrDefault(row, DEFAULT_ROW_HEIGHT);
    }

    public int getColumnWidth(int column) {
        return columnWidths.getOrDefault(column, DEFAULT_COLUMN_WIDTH);
    }

}
