package com.lalaalal.sheep;

import com.lalaalal.sheep.expression.CellRange;
import com.lalaalal.sheep.expression.Literal;

import java.io.PrintStream;

public class SheetDrawer {
    public static final char DEFAULT_CORNER_LETTER = '+';
    public static final char DEFAULT_HORIZONTAL_LETTER = '-';
    public static final char DEFAULT_VERTICAL_LETTER = '|';

    private final char cornerLetter;
    private final char horizontalLetter;
    private final char verticalLetter;

    public SheetDrawer() {
        this(DEFAULT_CORNER_LETTER, DEFAULT_HORIZONTAL_LETTER, DEFAULT_VERTICAL_LETTER);
    }

    public SheetDrawer(char cornerLetter, char horizontalLetter, char verticalLetter) {
        this.cornerLetter = cornerLetter;
        this.horizontalLetter = horizontalLetter;
        this.verticalLetter = verticalLetter;
    }

    public void draw(PrintStream printStream, Sheet sheet, CellRange cellRange) {
        int maxRow = cellRange.getMaximumRow();
        int minRow = cellRange.getMinimumRow();
        int numRows = maxRow - cellRange.getMinimumRow() + 1;
        int maxColumn = cellRange.getMaximumColumn();
        int minColumn = cellRange.getMinimumColumn();
        int numColumns = maxColumn - cellRange.getMinimumColumn() + 1;
        int rowNumberWidth = String.valueOf(maxRow).length() + 1;

        drawLine(printStream, sheet, rowNumberWidth, numColumns, cellRange.getMinimumColumn());
        drawColumnAlphabets(printStream, sheet, rowNumberWidth, numColumns, minColumn);
        drawLine(printStream, sheet, rowNumberWidth, numColumns, cellRange.getMinimumColumn());

        for (int i = 0; i < numRows; i++) {
            int rowIndex = minRow + i;
            drawCellRow(printStream, sheet, rowNumberWidth, rowIndex, numColumns, minColumn);

            drawLine(printStream, sheet, rowNumberWidth, numColumns, cellRange.getMinimumColumn());
        }

    }

    private void drawCellRow(PrintStream printStream, Sheet sheet, int rowNumberWidth, int rowIndex, int numColumns, int minColumn) {
        printStream.print(verticalLetter);
        String format = String.format("%%%dd", rowNumberWidth);
        String rowNumber = String.format(format, rowIndex + 1);
        printStream.print(rowNumber);
        printStream.print(verticalLetter);

        for (int i = 0; i < numColumns; i++) {
            int columnIndex = minColumn + i;
            Cell cell = sheet.getCell(Position.at(rowIndex, columnIndex));
            Literal literal = cell.calculate(sheet);
            int columnWidth = sheet.getColumnWidth(columnIndex);
            String cellText = literal.toString(columnWidth);
            if (cellText.length() < columnWidth)
                cellText = " ".repeat(columnWidth - cellText.length()) + cellText;
            printStream.print(cellText);
            printStream.print(verticalLetter);
        }
        printStream.println();
    }

    private void drawColumnAlphabets(PrintStream printStream, Sheet sheet, int rowNumberWidth, int numColumns, int minColumn) {
        printStream.print(verticalLetter);
        printStream.print(" ".repeat(rowNumberWidth));
        printStream.print(verticalLetter);

        for (int i = 0; i < numColumns; i++) {
            int columnIndex = minColumn + i;
            int columnWidth = sheet.getColumnWidth(columnIndex);
            String cellText = Position.parseColumn(columnIndex);
            if (cellText.length() < columnWidth)
                cellText = " ".repeat(columnWidth - cellText.length()) + cellText;
            printStream.print(cellText);
            printStream.print(verticalLetter);
        }
        printStream.println();
    }

    private void drawLine(PrintStream printStream, Sheet sheet, int rowNumberWidth, int numColumns, int minColumn) {
        printStream.print(cornerLetter);
        for (int i = 0; i < rowNumberWidth; i++)
            printStream.print(horizontalLetter);
        printStream.print(cornerLetter);

        for (int i = 0; i < numColumns; i++) {
            int columnWidth = sheet.getColumnWidth(minColumn + i);
            String line = String.valueOf(horizontalLetter).repeat(columnWidth);
            printStream.print(line);
            printStream.print(cornerLetter);
        }
        printStream.println();
    }
}
