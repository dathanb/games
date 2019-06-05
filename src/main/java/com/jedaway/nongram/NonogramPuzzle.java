package com.jedaway.nongram;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.jedaway.nongram.Logical.FALSE;
import static com.jedaway.nongram.Logical.UNKNOWN;

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
            if (onCellsInRow(i, position) > rowConstraints.get(i).numOnCellsAllowd()) {
                return FALSE;
            }
        }

        for (int i = 0; i < numCols; i++) {
            if (onCellsInCol(i, position) > colConstraints.get(i).numOnCellsAllowd()) {
                return FALSE;
            }
        }

        return UNKNOWN;
    }

    private int onCellsInRow(int row, NonogramPosition position) {
        return (int) Stream.of(position.getCells()[row]).filter(NonogramPosition.CellState::isOn).count();
    }

    private int onCellsInCol(int col, NonogramPosition position) {
        return (int) Stream.of(position.getCells()).map(r -> r[col]).filter(NonogramPosition.CellState::isOn).count();
    }
}
