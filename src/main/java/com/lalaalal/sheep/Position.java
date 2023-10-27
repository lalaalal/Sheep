package com.lalaalal.sheep;

/**
 * An immutable class storing position of cell.
 *
 * @author lalaalal
 */
public class Position {
    private final int row;
    private final int column;
    private final String rowText;
    private final String columnText;

    public static String parseColumn(int column) {
        int front = column / 26;
        int tail = column % 26;
        String tailString = String.valueOf((char) ('A' + tail));

        if (front == 0)
            return tailString;
        return parseColumn(front - 1) + tailString;
    }

    public static int columnToInt(String column) {
        int columnInt = 0;
        for (int i = 0; i < column.length(); i++) {
            int index = column.length() - 1 - i;
            char c = column.charAt(index);
            if (i != 0)
                c += 1;
            columnInt += (int) (Math.pow(26, i) * (c - 'A'));
        }

        return columnInt;
    }

    public static Position at(String position) {
        throw new UnsupportedOperationException();
    }

    public static Position at(int row, int column) {
        return new Position(row, column);
    }

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
        this.rowText = String.valueOf(row);
        this.columnText = parseColumn(column);
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public String getRowText() {
        return rowText;
    }

    public String getColumnText() {
        return columnText;
    }

    @Override
    public String toString() {
        return columnText + rowText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (row != position.row) return false;
        return column == position.column;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + column;
        return result;
    }
}
