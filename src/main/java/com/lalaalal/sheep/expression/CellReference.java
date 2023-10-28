package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.Cell;
import com.lalaalal.sheep.Position;
import com.lalaalal.sheep.Sheet;

public class CellReference extends Operand {
    private final Position position;

    public static boolean isCellReference(String expression) {
        boolean alphabetExists = false;
        for (int index = 0; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (Expression.isAlphabet(c))
                alphabetExists = true;
            else if (Expression.isNumber(c)) {
                if (!alphabetExists)
                    return false;
            } else {
                return false;
            }
        }

        return true;
    }

    public static CellReference parseCellReference(String expression) {
        return new CellReference(Position.at(expression));
    }

    public CellReference(Position position) {
        this.position = position;
    }

    @Override
    public Literal calculate(Sheet sheet) {
        Cell cell = sheet.getCell(position);

        return cell.calculate(sheet);
    }
}
