package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.sheet.Position;
import com.lalaalal.sheep.sheet.Sheet;

import java.util.Iterator;

public class CellRange extends Operand implements Iterable<CellReference> {
    private final Position from;
    private final Position to;

    public static boolean isCellRange(String expression) {
        String[] positions = expression.split(":");
        if (positions.length != 2)
            return false;
        for (String position : positions) {
            if (!CellReference.isCellReference(position))
                return false;
        }

        return true;
    }

    public static CellRange parseCellRange(String expression) {
        String[] positions = expression.split(":");
        Position from = Position.at(positions[0]);
        Position to = Position.at(positions[1]);
        return new CellRange(from, to);
    }

    public CellRange(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public int getMinimumColumn() {
        return from.getColumn();
    }

    public int getMaximumColumn() {
        return to.getColumn();
    }

    public int getMinimumRow() {
        return from.getRow();
    }

    public int getMaximumRow() {
        return to.getRow();
    }

    @Override
    public Iterator<CellReference> iterator() {
        return new RangeIterator();
    }

    @Override
    public Literal calculate(Sheet sheet) {
        return null;
    }

    private class RangeIterator implements Iterator<CellReference> {
        private Position current = from;

        @Override
        public boolean hasNext() {
            return current.getRow() <= to.getRow();
        }

        @Override
        public CellReference next() {
            Position result = current;

            int row = current.getRow();
            int column = current.getColumn();
            if (column < getMaximumColumn())
                column += 1;
            if (column == getMaximumColumn()) {
                row += 1;
                column = getMinimumColumn();
            }

            current = Position.at(row, column);

            return new CellReference(result);
        }
    }
}
