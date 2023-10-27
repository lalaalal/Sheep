package com.lalaalal.sheep;

public class Cell {
    private Position position;
    private String literal;

    public Cell(Position position, String text) {
        this.position = position;
        this.literal = text;
    }

    public String getLiteral() {
        return literal;
    }

    public void setText(String text) {
        this.literal = text;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
