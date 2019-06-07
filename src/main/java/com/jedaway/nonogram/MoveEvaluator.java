package com.jedaway.nonogram;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.nonogram.MoveEvaluation.INVALID;

/**
 * Evaluates moves in the context of a given position.
 */
public class MoveEvaluator {
    public MoveEvaluation evaluate(NonogramMove move, NonogramPosition position, NonogramPuzzle puzzle) {
        if (puzzle.isValid(position) == FALSE) {
            return INVALID;
        }

        NonogramPosition movedPosition = position.apply(move);
        if (puzzle.isValid(movedPosition) == FALSE) {
            return INVALID;
        }
        // Given a move that assigns a value to a given cell, determine whether the new cell assignment is part of every
        // valid row and column that intersects the cell

        // We can take a brute-force approach first: just generate every possible row, filter down to ones that are valid,
        // and check the cell value.
        // Then repeat for the column.

        // Test the

        // 1) verify the position is not invalid.
        // 2. Get a list of indices of empty cells in the row
        // 3. Generate all possible assignments to those indices
        // 4. for each one, test whether it's valid.
        // 5. for each valid one, test whether the move is refletcted in that position
        // 6. if step 5 returns true for all, then return a high ranking
        // 7. if

        return null;
    }
}
