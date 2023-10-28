package com.lalaalal.sheep;

import com.lalaalal.sheep.expression.Expression;
import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;

public class Cell {
    public static final Cell EMPTY = new Cell("");

    private String literal;
    private Operand rootOperand;

    public Cell(String text) {
        this.literal = text;
    }

    public boolean isExpression() {
        if (!literal.isEmpty())
            return literal.charAt(0) == '=';
        return false;
    }

    public String getLiteral() {
        return literal;
    }

    public Literal calculate(Sheet sheet) {
        if (!isExpression())
            return new Literal(literal);
        if (rootOperand == null)
            rootOperand = Expression.parseExpression(literal.substring(1));
        return rootOperand.calculate(sheet);
    }

    public void setText(String text) {
        this.literal = text;
        if (isExpression())
            rootOperand = Expression.parseExpression(literal);
    }

    @Override
    public String toString() {
        return literal;
    }
}
