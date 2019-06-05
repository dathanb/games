package com.jedaway.nonogram;

import com.jedaway.game.Move;
import com.jedaway.game.Position;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NonogramPosition implements Position {
    public enum CellState {
        ON,
        OFF,
        EMPTY;

        public boolean isOn() {
            return this == ON;
        }

        public boolean isEmpty() {
            return this == EMPTY;
        }

        public boolean isOff() {
            return this == OFF;
        }
    }

    private final CellState[][] cells;

    public NonogramPosition(int  numRows, int numCols) {
        this.cells = new CellState[numRows][numCols];
    }

    public NonogramPosition(CellState[][] cells) {
        this.cells = cells;
    }

    public Move[] getMoves() {
        return null;
    }

    public CellState[][] getCells() {
        return cells;
    }

    public CellState[] getRow(int r) {
        return cells[r];
    }

    public CellState[] getCol(int c) {
        return Arrays.stream(cells).map(r -> r[c]).collect(Collectors.toList()).toArray(new CellState[]{});
    }
}
