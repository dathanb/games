package com.jedaway.nongram;

public class NonogramPosition implements Position {
    public enum CellState {
        ON,
        OFF,
        EMPTY;
    }

    private CellState[][] cells;

    public NongramePosition(int  numRows, int numCols) {
        this.cells = new CellState[numRows][numCols];
    }

    public NonogramPosition(CellState[][] cells) {
        this.cells = cells;
    }

    public Move[] getMoves() {
    }
}
