package com.jedaway.nonogram;

import com.jedaway.game.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.jedaway.nonogram.CellState.*;

public class NonogramPosition implements Game<NonogramMove> {

    private final CellState[][] cells;

    public NonogramPosition(int  numRows, int numCols) {
        this.cells = new CellState[numRows][numCols];
    }

    public NonogramPosition(CellState[][] cells) {
        this.cells = cells;
    }

    @Override
    public NonogramMove[] getMoves() {
        List<NonogramMove> moves = new ArrayList<>();
        for (int r=0; r<cells.length; r++) {
            CellState[] row = cells[r];
            for (int c=0; c<row.length; c++) {
                if (row[c] == EMPTY) {
                    moves.add(new NonogramMove(r,c,ON));
                    moves.add(new NonogramMove(r,c,OFF));
                }
            }
        }
        return moves.toArray(new NonogramMove[]{});
    }

    public NonogramPosition apply(NonogramMove move) {
        CellState[][] newCells = new CellState[cells.length][cells[0].length];
        for (int r = 0; r<cells.length; r++) {
            CellState[] row = cells[r];
            for (int c = 0; c<row.length; c++) {
                newCells[r][c] = row[c];
            }
        }
        newCells[move.row][move.col] = move.state;
        return new NonogramPosition(newCells);
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int r=0; r<cells.length; r++) {
            CellState[] row = cells[r];
            for (int c=0; c<row.length; c++) {
                if (row[c] == ON) {
                    sb.append((char) 0x2589);
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NonogramPosition that = (NonogramPosition) o;
        return Arrays.deepEquals(cells, that.cells);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cells);
    }
}
