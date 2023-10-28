package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Sheet;

public class Literal extends Operand {
    public static boolean isLiteral(String expression) {
        if (expression.charAt(0) == '\"' && expression.charAt(expression.length() - 1) == '\"')
            return true;
        int dotCount = 0;
        for (int index = 0; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (c == '.') {
                dotCount += 1;
                continue;
            }
            if (c < '0' || c > '9')
                return false;
        }
        return dotCount <= 1;
    }

    public static Literal parseLiteral(String expression) {
        if (expression.charAt(0) == '\"')
            return new Literal(expression.substring(1, expression.length() - 1));
        return new Literal(Double.parseDouble(expression));
    }

    private final String text;

    public Literal(String text) {
        this.text = text;
    }

    public Literal(double number) {
        this.text = String.valueOf(number);
    }

    @Override
    public Literal calculate(Sheet sheet) {
        return this;
    }

    @Override
    public String toString() {
        return text;
    }

    public double toDouble() {
        return Double.parseDouble(text);
    }
}
