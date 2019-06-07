package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import static com.jedaway.nonogram.CellState.*;
import static com.jedaway.nonogram.MoveEvaluation.INVALID;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveEvaluatorTest {
    @Test
    public void evaluate_WithAnInvalidPosition_ReturnsInvalid() {
        NonogramPuzzle puzzle = TestPuzzles.TRIVIAL;
        NonogramPosition position = new NonogramPosition(new CellState[][]{{OFF}});
        NonogramMove move = new NonogramMove(0,0, ON);

        assertEquals(INVALID, new MoveEvaluator().evaluate(move, position, puzzle));
    }

    @Test
    public void evaluate_WithAnInvalidMove_ReturnsInvalid() {
        NonogramPuzzle puzzle = TestPuzzles.TRIVIAL;
        NonogramPosition position = new NonogramPosition(new CellState[][]{{EMPTY}});
        NonogramMove move = new NonogramMove(0,0, OFF);

        assertEquals(INVALID, new MoveEvaluator().evaluate(move, position, puzzle));
    }

}
