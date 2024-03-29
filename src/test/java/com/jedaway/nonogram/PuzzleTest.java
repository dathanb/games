package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.game.Logical.UNKNOWN;
import static com.jedaway.nonogram.CellState.*;
import static com.jedaway.nonogram.TestPuzzles.PARROT;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void isValid_EmptyPosition_ReturnsTrue() {
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
        assertEquals(UNKNOWN, PARROT.isValid(position));
    }

    @Test
    public void isValid_WithPositionWhoseDimensionsDontMatchThePuzzle_ReturnsFalse() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{
                {EMPTY}
        });
        assertEquals(FALSE, PARROT.isValid(position));
    }

    @Test
    public void isValid_WhenRowContainsMoreOnCellsThanConstrainsAllow_ReturnsFalse() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{{ON}});
        // we allow one ON cell in the column, but zero in the row, to be sure we're isolating the row checking
        // behavior
        NonogramPuzzle puzzle = new NonogramPuzzle(
                Collections.singletonList(new NonogramConstraint(0)),
                Collections.singletonList(new NonogramConstraint(1)));
        assertEquals(FALSE, puzzle.isValid(position));
    }

    @Test
    public void isValid_WhenRowContainsMoreOffCellsThanConstrainsAllow_ReturnsFalse() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{{OFF}});
        // we allow one OFF cell in the column, but zero in the row, to be sure we're isolating the row checking behavior
        NonogramPuzzle puzzle = new NonogramPuzzle(
                Collections.singletonList(new NonogramConstraint(1)),
                Collections.singletonList(new NonogramConstraint(0)));
        assertEquals(FALSE, puzzle.isValid(position));
    }

    @Test
    public void isValid_WhenColContainsMoreOnCellsThanConstrainsAllow_ReturnsFalse() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{{ON}});
        // Note that there aren't any valid solutions to this puzzle; that's fine, we're just using it to test some specific
        // behaviors; we allow one ON cell in the row, but zero in the column, to be sure we're isolating the column checking
        // behavior
        NonogramPuzzle puzzle = new NonogramPuzzle(
                Collections.singletonList(new NonogramConstraint(1)),
                Collections.singletonList(new NonogramConstraint(0)));
        assertEquals(FALSE, puzzle.isValid(position));
    }

    @Test
    public void isValid_WhenColContainsMoreOffCellsThanConstrainsAllow_ReturnsFalse() {
        NonogramGame position = new NonogramGame(null, new CellState[][]{{OFF}});
        // we allow one OFF cell in the row, but zero in the column, to be sure we're isolating the column checking logic
        NonogramPuzzle puzzle = new NonogramPuzzle(
                Collections.singletonList(new NonogramConstraint(0)),
                Collections.singletonList(new NonogramConstraint(1)));
        assertEquals(FALSE, puzzle.isValid(position));
    }
}
