package com.jedaway.nonogram;

import org.junit.jupiter.api.Test;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.game.Logical.TRUE;
import static com.jedaway.game.Logical.UNKNOWN;
import static com.jedaway.nonogram.CellState.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NonogramConstraintTest {
    @Test
    public void isValid_WithAGroupThatsTooBig_ReturnsFalse() {
        NonogramConstraint constraint = new NonogramConstraint(1,1);
        CellState[] row = new CellState[]{OFF, ON, ON};

        assertEquals(FALSE, constraint.isValid(row));
    }

    @Test
    public void isValid_WithEmptyCellsAndNotTooManyFilledIn_ReturnsUnknown() {
        NonogramConstraint constraint = new NonogramConstraint(2);
        CellState[] row = new CellState[]{EMPTY, ON, OFF};
        assertEquals(UNKNOWN, constraint.isValid(row));
    }

    @Test
    public void isValid_WithMismatchedGroups_ReturnsFalse() {
        NonogramConstraint constraint = new NonogramConstraint(2);
        CellState[] row = new CellState[]{ON, OFF, ON};
        assertEquals(FALSE, constraint.isValid(row));
    }

    @Test
    public void isValid_WithAllGroupsMatched_ReturnsTrue() {
        NonogramConstraint constraint = new NonogramConstraint(1,1);
        CellState[] row = new CellState[]{ON, OFF, ON};
        assertEquals(TRUE, constraint.isValid(row));
    }
}
