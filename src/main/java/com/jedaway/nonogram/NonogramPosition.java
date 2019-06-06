package com.jedaway.nonogram;

import com.jedaway.game.Move;
import com.jedaway.game.Position;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.jedaway.nonogram.CellState.*;

public class NonogramPosition implements Position<NonogramMove> {

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
