package com.lalaalal.sheep.expression;

import com.lalaalal.sheep.exception.CalculationError;
import com.lalaalal.sheep.sheet.Cell;
import com.lalaalal.sheep.sheet.Position;
import com.lalaalal.sheep.sheet.Sheet;

public class CellReference extends Operand {
    private final Position position;

    public static boolean isCellReference(String expression) {
        boolean alphabetExists = false;
        boolean numberExists = false;
        for (int index = 0; index < expression.length(); index++) {
            char c = expression.charAt(index);
            if (Expression.isAlphabet(c))
                alphabetExists = true;
            else if (Expression.isNumber(c)) {
                numberExists = true;
                if (!alphabetExists)
                    return false;
            } else {
                return false;
            }
        }

        return numberExists;
    }

    public static CellReference parseCellReference(String expression) {
        return new CellReference(Position.at(expression));
    }

    public CellReference(Position position) {
        this.position = position;
    }

    @Override
    public Literal calculate(Sheet sheet) throws CalculationError {
        Cell cell = sheet.getCell(position);

        return cell.calculate(sheet);
    }
}
