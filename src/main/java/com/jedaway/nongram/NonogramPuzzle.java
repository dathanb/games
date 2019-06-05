package com.jedaway.nongram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.jedaway.nongram.Logical.FALSE;
import static com.jedaway.nongram.Logical.UNKNOWN;

public class NonogramPuzzle implements Puzzle<NonogramPosition> {
    private final int numRows;
    private final int numCols;
    private final List<Constraint> rowConstraints;
    private final List<Constraint> colConstraints;

    public NonogramPuzzle(List<Constraint> rowConstraints, List<Constraint> colConstraints) {
        this.numRows = rowConstraints.size();
        this.numCols = colConstraints.size();
        this.rowConstraints = new ArrayList<>();
        Collections.copy(rowConstraints, this.rowConstraints);
        this.colConstraints = new ArrayList<>();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumColumns() {
        return numCols;
    }

    public List<Constraint> getRowConstraints() {
        return rowConstraints;
    }

    public List<Constraint> getColConstraints() {
        return colConstraints;
    }

    @Override
    public Logical isValid(NonogramPosition position) {
        if (position.getCells().length != numRows || position.getCells()[0].length != numCols) {
            return FALSE;
        }
        return UNKNOWN;
    }
}
