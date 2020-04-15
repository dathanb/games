package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.jedaway.nonogram.CellState.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NonogramMoveGeneratorTest {
    @Test
    public void getMoves_ReturnsAllMovesForAllEmptyCells() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{
                {ON, EMPTY},
                {EMPTY, OFF}
        });

        NonogramMoveGenerator moveGenerator = new NonogramMoveGenerator();
        List<NonogramMove> moves = moveGenerator.getMoves(position);
        assertTrue(moves.contains(new NonogramMove(1, 0, ON)));
        assertTrue(moves.contains(new NonogramMove(1, 0, OFF)));
        assertTrue(moves.contains(new NonogramMove(0, 1, ON)));
        assertTrue(moves.contains(new NonogramMove(0, 1, OFF)));
    }

    @Test
    public void getMoves_DoesNotReturnMovesForNonEmptyCells() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{
                {ON, EMPTY},
                {EMPTY, OFF}
        });

        NonogramMoveGenerator moveGenerator = new NonogramMoveGenerator();
        List<NonogramMove> moves = moveGenerator.getMoves(position);
        assertFalse(moves.contains(new NonogramMove(0, 0, ON)));
        assertFalse(moves.contains(new NonogramMove(0, 0, OFF)));
        assertFalse(moves.contains(new NonogramMove(1, 1, ON)));
        assertFalse(moves.contains(new NonogramMove(1, 1, OFF)));
    }

}
