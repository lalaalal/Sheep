package com.lalaalal.sheep.sheet;

import java.util.HashMap;

public class Sheet {
    public static final int DEFAULT_ROW_HEIGHT = 1;
    public static final int DEFAULT_COLUMN_WIDTH = 8;

    private final HashMap<Position, Cell> cells = new HashMap<>();

    private final HashMap<Integer, Integer> rowHeights = new HashMap<>();
    private final HashMap<Integer, Integer> columnWidths = new HashMap<>();

    public Cell getCell(Position position) {
        if (cells.containsKey(position))
            return cells.get(position);
        return Cell.EMPTY;
    }

    public final void setText(String positionString, String text) {
        setText(Position.at(positionString), text);
    }

    public void setText(Position position, String text) {
        cells.put(position, new Cell(text));
    }

    public int getRowHeight(int row) {
        return rowHeights.getOrDefault(row, DEFAULT_ROW_HEIGHT);
    }

    public int getColumnWidth(int column) {
        return columnWidths.getOrDefault(column, DEFAULT_COLUMN_WIDTH);
    }

    public void setRowHeight(int row, int height) {
        rowHeights.put(row, height);
    }

    public void setColumnWidth(int column, int width) {
        columnWidths.put(column, width);
    }
}
