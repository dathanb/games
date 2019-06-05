package com.jedaway.nongram;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static com.jedaway.nongram.Logical.FALSE;
import static com.jedaway.nongram.Logical.UNKNOWN;
import static com.jedaway.nongram.NonogramPosition.CellState.EMPTY;
import static com.jedaway.nongram.NonogramPosition.CellState.ON;
import static com.jedaway.nongram.TestPuzzles.PARROT;
import static com.jedaway.nongram.TestPuzzles.TRIVIAL_FALSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {
    @Test
    public void isValid_EmptyPosition_ReturnsTrue() {
        NonogramPosition position = new NonogramPosition(new NonogramPosition.CellState[][]{
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
        NonogramPosition position = new NonogramPosition(new NonogramPosition.CellState[][]{
                {EMPTY}
        });
        assertEquals(FALSE, PARROT.isValid(position));
    }

    @Test
    public void isValid_WhenRowContainsMoreTrueCellsThanConstrainsAllow_ReturnsFalse() {
        NonogramPosition position = new NonogramPosition(new NonogramPosition.CellState[][]{{ON}});
        // Note that there aren't any valid solutions to this puzzle; that's fine, we're just using it to test some specific
        // behaviors; we allow one ON cell in the column, but zero in the row, to be sure we're isolating the row checking
        // behavior
        NonogramPuzzle puzzle = new NonogramPuzzle(
                Collections.singletonList(new NonogramConstraint(0)),
                Collections.singletonList(new NonogramConstraint(1)));
        assertEquals(FALSE, puzzle.isValid(position));
    }

    @Test
    public void isValid_WhenColContainsMoreTrueCellsThanConstrainsAllow_ReturnsFalse() {
        NonogramPosition position = new NonogramPosition(new NonogramPosition.CellState[][]{{ON}});
        // Note that there aren't any valid solutions to this puzzle; that's fine, we're just using it to test some specific
        // behaviors; we allow one ON cell in the row, but zero in the column, to be sure we're isolating the column checking
        // behavior
        NonogramPuzzle puzzle = new NonogramPuzzle(
                Collections.singletonList(new NonogramConstraint(1)),
                Collections.singletonList(new NonogramConstraint(0)));
        assertEquals(FALSE, puzzle.isValid(position));
    }
}
