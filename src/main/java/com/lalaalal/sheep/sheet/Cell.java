package com.lalaalal.sheep.sheet;

import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.exception.ExpressionError;
import com.lalaalal.sheep.expression.ErrorOperand;
import com.lalaalal.sheep.expression.Expression;
import com.lalaalal.sheep.expression.Literal;
import com.lalaalal.sheep.expression.Operand;

public class Cell {
    public static final Cell EMPTY = new Cell("");

    private String text;
    private Operand rootOperand;

    public Cell(String text) {
        this.text = text;
    }

    public boolean isExpression() {
        return text.startsWith("=");
    }

    public String getText() {
        return text;
    }

    public Literal calculate(Sheet sheet) {
        if (!isExpression())
            return new Literal(text);
        try {
            if (rootOperand == null)
                rootOperand = Expression.parseExpression(text.substring(1));
            return rootOperand.calculate(sheet);
        } catch (ExpressionError error) {
            ErrorOperand errorOperand = new ErrorOperand(error);
            this.rootOperand = errorOperand;
            return errorOperand;
        } catch (CalculationError error) {
            error.setExpression(text);
            ErrorOperand errorOperand = new ErrorOperand(error);
            this.rootOperand = errorOperand;
            return errorOperand;
        }
    }

    public void setText(String text) {
        this.text = text;
        try {
            if (isExpression())
                rootOperand = Expression.parseExpression(this.text);
        } catch (ExpressionError error) {
            this.rootOperand = new ErrorOperand(error);
        }
    }

    @Override
    public String toString() {
        return text;
    }
}
