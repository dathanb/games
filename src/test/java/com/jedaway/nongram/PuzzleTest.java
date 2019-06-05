package com.jedaway.nongram;

import org.junit.jupiter.api.Test;

import static com.jedaway.nongram.Logical.FALSE;
import static com.jedaway.nongram.Logical.UNKNOWN;
import static com.jedaway.nongram.NonogramPosition.CellState.EMPTY;
import static com.jedaway.nongram.TestPuzzles.PARROT;
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
}
