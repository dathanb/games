package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import static com.jedaway.nonogram.CellState.*;
import static com.jedaway.nonogram.MoveEvaluation.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoveEvaluatorTest {
    @Test
    public void evaluate_WithAnInvalidPosition_ReturnsInvalid() {
        NonogramPuzzle puzzle = TestPuzzles.TRIVIAL;
        NonogramGame position = new NonogramGame(null, new CellState[][]{{OFF}});
        NonogramMove move = new NonogramMove(0, 0, ON);

        assertEquals(INVALID, new MoveEvaluator(move, position, puzzle).evaluate());
    }

    @Test
    public void evaluate_WithAnInvalidMove_ReturnsInvalid() {
        NonogramPuzzle puzzle = TestPuzzles.TRIVIAL;
        NonogramGame position = new NonogramGame(null, new CellState[][]{{EMPTY}});
        NonogramMove move = new NonogramMove(0, 0, OFF);

        assertEquals(INVALID, new MoveEvaluator(move, position, puzzle).evaluate());
    }

    @Test
    public void evaluate_WithAWinningMove_ReturnsWinning() {
        NonogramPuzzle puzzle = TestPuzzles.TRIVIAL;
        NonogramGame position = new NonogramGame(null, new CellState[][]{{EMPTY}});
        NonogramMove move = new NonogramMove(0, 0, ON);

        assertEquals(WINNING, new MoveEvaluator(move, position, puzzle).evaluate());
    }

    @Test
    public void evaluate_WithAWinningMoveOnALargerPuzzle_ReturnsWinning() {
        NonogramPuzzle puzzle = TestPuzzles.PARROT;
        NonogramGame position = new NonogramGame(null, new CellState[][]{
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
        });
        NonogramMove move = new NonogramMove(6, 0, ON);

        assertEquals(WINNING, new MoveEvaluator(move, position, puzzle).evaluate());
    }

    @Test
    public void evaluate_WithAnUnprovableMove_ReturnsMeh() {
        NonogramPuzzle puzzle = TestPuzzles.PARROT;
        NonogramGame position = new NonogramGame(null, new CellState[][]{
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY,},
        });
        NonogramMove move = new NonogramMove(0, 0, ON);

        assertEquals(MEH, new MoveEvaluator(move, position, puzzle).evaluate());
    }
}
