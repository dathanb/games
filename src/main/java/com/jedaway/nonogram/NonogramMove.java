package com.jedaway.nonogram;

import com.jedaway.game.Move;

import java.util.Objects;

public class NonogramMove implements Move {
    final int row;
    final int col;
    final CellState state;

    public NonogramMove(int row, int col, CellState state) {
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

    @Override
    public String toString() {
        return "NonogramMove{" +
                "row=" + row +
                ", col=" + col +
                ", state=" + state +
                '}';
    }
}
