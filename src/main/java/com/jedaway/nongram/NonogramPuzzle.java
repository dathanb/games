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
            if (tooManyOnCellsInRow(position, i) || tooManyOffCellsInRow(position, i)) {
                return FALSE;
            }
        }

        for (int i = 0; i < numCols; i++) {
            if (tooManyOnCellsInCol(position, i) || tooManyOffCellsInCol(position, i)) {
                return FALSE;
            }
        }

        return UNKNOWN;
    }

    private boolean tooManyOffCellsInCol(NonogramPosition position, int i) {
        return offCellsInCol(i, position) > (numRows - colConstraints.get(i).numOnCellsAllowed());
    }

    private boolean tooManyOnCellsInCol(NonogramPosition position, int i) {
        return onCellsInCol(i, position) > colConstraints.get(i).numOnCellsAllowed();
    }

    private boolean tooManyOffCellsInRow(NonogramPosition position, int i) {
        return offCellsInRow(i,position) > (numCols - rowConstraints.get(i).numOnCellsAllowed());
    }

    private boolean tooManyOnCellsInRow(NonogramPosition position, int i) {
        return onCellsInRow(i, position) > rowConstraints.get(i).numOnCellsAllowed();
    }

    private int onCellsInRow(int row, NonogramPosition position) {
        return (int) Stream.of(position.getCells()[row]).filter(NonogramPosition.CellState::isOn).count();
    }

    private int offCellsInRow(int row, NonogramPosition position) {
        return (int) Stream.of(position.getCells()[row]).filter(NonogramPosition.CellState::isOff).count();
    }

    private int onCellsInCol(int col, NonogramPosition position) {
        return (int) Stream.of(position.getCells()).map(r -> r[col]).filter(NonogramPosition.CellState::isOn).count();
    }

    private int offCellsInCol(int col, NonogramPosition position) {
        return (int) Stream.of(position.getCells()).map(r -> r[col]).filter(NonogramPosition.CellState::isOff).count();
    }
}
