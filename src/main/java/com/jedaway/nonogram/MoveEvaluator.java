package com.jedaway.nonogram;

import java.util.ArrayList;
import java.util.List;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.nonogram.CellState.*;
import static com.jedaway.nonogram.MoveEvaluation.*;

/**
 * Evaluates moves in the context of a given position.
 */
public class MoveEvaluator {
    private final NonogramMove move;
    private final NonogramPosition position;
    private final NonogramPuzzle puzzle;
    private final List<CellState[]> possibleRows = new ArrayList<>();
    private final List<CellState[]> possibleCols = new ArrayList<>();

    public MoveEvaluator(NonogramMove move, NonogramPosition position, NonogramPuzzle puzzle) {
        this.move = move;
        this.position = position;
        this.puzzle = puzzle;
    }

    public MoveEvaluation evaluate() {
        if (puzzle.isValid(position) == FALSE) {
            return INVALID;
        }

        NonogramPosition movedPosition = position.apply(move);
        if (puzzle.isValid(movedPosition) == FALSE) {
            return INVALID;
        }

        generateRows();
        generateCols();

        if (moveIsPartOfAllValidRows() || moveIsPartOfAllValidCols()) {
            return WINNING;
        } else if (moveHasNoValidRows() || moveHasNoValidCols()) {
            return LOSING;
        }

        return MEH;
    }

    private void generateRows() {
        CellState[] row = position.getRow(move.row);
        generate(row, 0, possibleRows);
        filter(possibleRows, puzzle.getRowConstraints().get(move.row));
    }

    private void generateCols() {
        CellState[] row = position.getCol(move.col);
        generate(row, 0, possibleCols);
        filter(possibleRows, puzzle.getColConstraints().get(move.col));
    }

    private boolean moveIsPartOfAllValidRows() {
        return possibleRows.stream().allMatch(r -> r[move.col] == move.state);
    }

    private boolean moveIsPartOfAllValidCols() {
        return possibleCols.stream().allMatch(r -> r[move.row] == move.state);
    }

    private boolean moveHasNoValidRows() {
        return possibleRows.size() == 0;
    }

    private boolean moveHasNoValidCols() {
        return possibleCols.size() == 0;
    }

    private void generate(CellState[] template, int index, List<CellState[]> holder) {
        if (index >= template.length) {
            holder.add(template);
        } else if (template[index] != EMPTY) {
            generate(template, index+1, holder);
        } else {
            CellState[] newTemplate = template.clone();
            newTemplate[index] = ON;
            generate(newTemplate, index + 1, holder);
            newTemplate[index] = OFF;
            generate(newTemplate, index + 1, holder);
        }
    }

    private void filter(List<CellState[]> cells, NonogramConstraint constraint) {
        cells.removeIf(currentCells -> constraint.isValid(currentCells) == FALSE);
    }
}
