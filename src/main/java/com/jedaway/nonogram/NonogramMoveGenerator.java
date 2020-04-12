package com.jedaway.nonogram;

import com.jedaway.game.MoveGenerator;

import java.util.ArrayList;
import java.util.List;

import static com.jedaway.nonogram.CellState.EMPTY;
import static com.jedaway.nonogram.CellState.OFF;
import static com.jedaway.nonogram.CellState.ON;

public class NonogramMoveGenerator implements MoveGenerator<NonogramGame, NonogramMove> {
    @Override
    public NonogramMove[] getMoves(NonogramGame game) {
        List<NonogramMove> moves = new ArrayList<>();
        CellState[][] cells = game.getCells();
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
}
