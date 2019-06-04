package com.jedaway.nongram;

import java.util.Objects;

public class NonogramMove implements Move {
    private final int row;
    private final int col;
    private final NonogramPosition.CellState state;

    public NonogramMove(int row, int col, NonogramPosition.CellState state) {
        this.row = row;
        this.col = col;
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonogramMove that = (NonogramMove) o;
        return row == that.row &&
                col == that.col &&
                state == that.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col, state);
    }
}
