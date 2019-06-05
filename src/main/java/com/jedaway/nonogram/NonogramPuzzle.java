package com.jedaway.nonogram;

import com.jedaway.game.Logical;
import com.jedaway.game.Puzzle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.game.Logical.UNKNOWN;

public class NonogramPuzzle implements Puzzle<NonogramPosition> {
    private final int numRows;
    private final int numCols;
    private final List<NonogramConstraint> rowConstraints;
    private final List<NonogramConstraint> colConstraints;

    public NonogramPuzzle(List<NonogramConstraint> rowConstraints, List<NonogramConstraint> colConstraints) {
        this.numRows = rowConstraints.size();
        this.numCols = colConstraints.size();
        this.rowConstraints = new ArrayList<>(rowConstraints);
        this.colConstraints = new ArrayList<>(colConstraints);
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numCols;
    }

    public List<NonogramConstraint> getRowConstraints() {
        return rowConstraints;
    }

    public List<NonogramConstraint> getColConstraints() {
        return colConstraints;
    }

    @Override
    public Logical isValid(NonogramPosition position) {
        if (position.getCells().length != numRows || position.getCells()[0].length != numCols) {
            return FALSE;
        }

        for (int i = 0; i < numRows; i++) {
            if (rowConstraints.get(i).isValid(position.getRow(i)) == FALSE) {
                return FALSE;
            }
        }

        for (int i = 0; i < numCols; i++) {
            if (colConstraints.get(i).isValid(position.getCol(i)) == FALSE) {
                return FALSE;
            }
        }

        return UNKNOWN;
    }
}
