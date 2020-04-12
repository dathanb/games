package com.jedaway.nonogram;

import com.google.common.collect.Lists;
import com.jedaway.game.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.jedaway.nonogram.CellState.*;

/**
 * The state of the Nonogram game at any point in time, including the history of moves chosen to this point.
 */
public class NonogramGame implements Game<NonogramMove> {
    private final CellState[][] cells;
    private final NonogramPuzzle puzzle;
    private List<NonogramMove> moves;

    public NonogramGame(NonogramPuzzle puzzle, int numRows, int numCols) {
        this.puzzle = puzzle;
        this.cells = new CellState[numRows][numCols];
        this.moves = new ArrayList<>();
    }

    public NonogramGame(NonogramPuzzle puzzle, CellState[][] cells) {
        this.puzzle = puzzle;
        this.cells = cells;
        this.moves = new ArrayList<>();
    }

    public NonogramGame(NonogramPuzzle puzzle, CellState[][] cells, List<NonogramMove> moves) {
        this.puzzle = puzzle;
        this.cells = cells;
        this.moves = moves;
    }

    public NonogramGame apply(NonogramMove move) {
        CellState[][] newCells = new CellState[cells.length][cells[0].length];
        for (int r = 0; r<cells.length; r++) {
            CellState[] row = cells[r];
            for (int c = 0; c<row.length; c++) {
                newCells[r][c] = row[c];
            }
        }
        newCells[move.row][move.col] = move.state;
        List<NonogramMove> newMoves = Lists.newArrayList(moves);
        newMoves.add(move);
        return new NonogramGame(puzzle, newCells, newMoves);
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
        for (CellState[] row : cells) {
            for (CellState cellState : row) {
                if (cellState == ON) {
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
        NonogramGame that = (NonogramGame) o;
        return Arrays.deepEquals(cells, that.cells);
    }

    public boolean isTerminal() {
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .noneMatch(CellState::isEmpty);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(cells);
    }

    public NonogramPuzzle getPuzzle() {
        return puzzle;
    }
}
