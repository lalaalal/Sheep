package com.lalaalal.sheep.sheet;

public class Sheet extends SheetReader {

    public final void setText(String positionString, String text) {
        setText(Position.at(positionString), text);
    }

    public void setText(Position position, String text) {
        cells.put(position, new Cell(text));
    }

    public void setRowHeight(int row, int height) {
        rowHeights.put(row, height);
    }

    public void setColumnWidth(int column, int width) {
        columnWidths.put(column, width);
    }
}
