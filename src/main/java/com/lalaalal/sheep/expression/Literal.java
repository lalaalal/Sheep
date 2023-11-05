package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.exception.NumberFormatError;
import com.lalaalal.sheep.sheet.Sheet;

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
    private final String detail;

    public Literal(String text) {
        this.text = text;
        this.detail = "Text";
    }

    public Literal(String text, String detail) {
        this.text = text;
        this.detail = detail;
    }

    public Literal(double number) {
        this.text = String.valueOf(number);
        this.detail = "Number";
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
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException exception) {
            throw new NumberFormatError(text);
        }
    }

    public String getDetail() {
        return detail;
    }

    public String toString(int maxLength) {
        String result = toString();
        if (result.length() > 4)
            return result.substring(0, maxLength);
        return result;
    }
}
