package com.lalaalal.sheep;

import java.util.Iterator;

public class Range implements Iterable<Position> {
    private final Position from;
    private final Position to;

    public Range(Position from, Position to) {
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
    public Iterator<Position> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Position> {
        private Position current = from;

        @Override
        public boolean hasNext() {
            return !to.equals(current);
        }

        @Override
        public Position next() {
            Position result = current;

            int row = current.getRow();
            int column = current.getColumn();
            if (column < getMaximumColumn())
                column += 1;
            if (column == getMaximumColumn() && row < getMaximumRow()) {
                row += 1;
                column = getMinimumColumn();
            }

            current = Position.at(row, column);

            return result;
        }
    }
}
