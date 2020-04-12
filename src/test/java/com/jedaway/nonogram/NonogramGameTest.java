package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.jedaway.nonogram.CellState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonogramGameTest {
    @Test
    public void getMoves_ReturnsAllMovesForAllEmptyCells() {
        NonogramGame position = new NonogramGame(new CellState[][]{
                {ON, EMPTY},
                {EMPTY, OFF}
        });

        List<NonogramMove> moves = Arrays.asList(position.getMoves());
        assertEquals(true, moves.contains(new NonogramMove(1, 0, ON)));
        assertEquals(true, moves.contains(new NonogramMove(1, 0, OFF)));
        assertEquals(true, moves.contains(new NonogramMove(0, 1, ON)));
        assertEquals(true, moves.contains(new NonogramMove(0, 1, OFF)));
    }

    @Test
    public void getMoves_DoesNotReturnMovesForNonEmptyCells() {
        NonogramGame position = new NonogramGame(new CellState[][]{
                {ON, EMPTY},
                {EMPTY, OFF}
        });

        List<NonogramMove> moves = Arrays.asList(position.getMoves());
        assertEquals(false, moves.contains(new NonogramMove(0, 0, ON)));
        assertEquals(false, moves.contains(new NonogramMove(0, 0, OFF)));
        assertEquals(false, moves.contains(new NonogramMove(1, 1, ON)));
        assertEquals(false, moves.contains(new NonogramMove(1, 1, OFF)));
    }

}
