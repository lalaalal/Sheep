package com.lalaalal.sheep;

import com.lalaalal.sheep.expression.Expression;
import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;

public class Cell {
    private Position position;
    private String literal;
    private Operand rootOperand;

    public Cell(Position position, String text) {
        this.position = position;
        this.literal = text;
    }

    public boolean isExpression() {
        return literal.charAt(0) == '=';
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
