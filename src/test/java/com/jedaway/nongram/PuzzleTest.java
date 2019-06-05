package com.jedaway.nongram;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.jedaway.nongram.Logical.*;
import static com.jedaway.nongram.NonogramPosition.CellState.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PuzzleTest {

    private List<NonogramPuzzle> getPuzzles() {
        return Arrays.asList(parrot());
    }

    private NonogramPuzzle parrot() {
        return new NonogramPuzzle(
                Arrays.asList(
                        new ConstraintImpl(5),
                        new ConstraintImpl(5),
                        new ConstraintImpl(5, 1),
                        new ConstraintImpl(5, 2),
                        new ConstraintImpl(1, 1),
                        new ConstraintImpl(10),
                        new ConstraintImpl(10),
                        new ConstraintImpl(2, 5),
                        new ConstraintImpl(2, 1, 5),
                        new ConstraintImpl(2, 5)
                ),
                Arrays.asList(
                        new ConstraintImpl(5),
                        new ConstraintImpl(8),
                        new ConstraintImpl(3, 2),
                        new ConstraintImpl(4, 2, 1),
                        new ConstraintImpl(4, 2),
                        new ConstraintImpl(4, 5),
                        new ConstraintImpl(2, 5),
                        new ConstraintImpl(1, 2, 5),
                        new ConstraintImpl(7),
                        new ConstraintImpl(5)
                )
        );
    }

    private NonogramPuzzle buffalo() {
        return new NonogramPuzzle(
                Arrays.asList(
                        new ConstraintImpl(4),
                        new ConstraintImpl(2, 2),
                        new ConstraintImpl(6),
                        new ConstraintImpl(10),
                        new ConstraintImpl(2, 1, 5),
                        new ConstraintImpl(2, 7),
                        new ConstraintImpl(3, 5),
                        new ConstraintImpl(4, 5),
                        new ConstraintImpl(4, 5),
                        new ConstraintImpl(1, 5)
                ),
                Arrays.asList(
                        new ConstraintImpl(7),
                        new ConstraintImpl(1, 6),
                        new ConstraintImpl(1, 2, 3),
                        new ConstraintImpl(6, 2),
                        new ConstraintImpl(4, 1),
                        new ConstraintImpl(8),
                        new ConstraintImpl(9),
                        new ConstraintImpl(9),
                        new ConstraintImpl(7),
                        new ConstraintImpl(7)
                )
        );
    }

    private Logical[][] buffaloSolution() {
        return new Logical[][]{
                {FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {TRUE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE},
                {TRUE, FALSE, TRUE, TRUE, FALSE, FALSE, TRUE, TRUE, TRUE, FALSE},
                {TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, TRUE, FALSE},
                {TRUE, TRUE, TRUE, FALSE, TRUE, FALSE, FALSE, FALSE, FALSE},
                {FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE}
        };
    }

    private NonogramPuzzle baobab() {
        return new NonogramPuzzle(
                Arrays.asList(
                        new ConstraintImpl(6),
                        new ConstraintImpl(6),
                        new ConstraintImpl(6, 1),
                        new ConstraintImpl(7, 1),
                        new ConstraintImpl(10),
                        new ConstraintImpl(10),
                        new ConstraintImpl(7, 1),
                        new ConstraintImpl(6, 1),
                        new ConstraintImpl(8),
                        new ConstraintImpl(4, 1)
                ),
                Arrays.asList(
                        new ConstraintImpl(6),
                        new ConstraintImpl(9),
                        new ConstraintImpl(10),
                        new ConstraintImpl(10),
                        new ConstraintImpl(10),
                        new ConstraintImpl(9),
                        new ConstraintImpl(2, 4, 1),
                        new ConstraintImpl(1, 1, 2, 3),
                        new ConstraintImpl(4, 1),
                        new ConstraintImpl(2)
                )
        );
    }

    private Logical[][] baobabSolution() {
        return new Logical[][]{
                {FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, FALSE},
                {FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE},
                {TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE,},
                {TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE},
                {TRUE, TRUE, FALSE, TRUE, TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, FALSE},
                {TRUE, FALSE, TRUE, FALSE, TRUE, TRUE, FALSE, TRUE, TRUE, TRUE},
                {FALSE, FALSE, FALSE, TRUE, TRUE, TRUE, TRUE, FALSE, TRUE, FALSE},
                {FALSE, FALSE, FALSE, FALSE, TRUE, TRUE, FALSE, FALSE, FALSE, FALSE}
        };
    }

    @Test
    public void isValid_EmptyPosition_ReturnsTrue() {
        Puzzle puzzle = parrot();
        NonogramPosition position = new NonogramPosition(new NonogramPosition.CellState[][]{
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
                {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, },
        });
        assertEquals(UNKNOWN, puzzle.isValid(position));
    }

    @Test
    public void isValid_WithPositionWhoseDimensionsDontMatchThePuzzle_ReturnsFalse() {
        Puzzle puzzle = parrot();
        NonogramPosition position = new NonogramPosition(new NonogramPosition.CellState[][]{
                {EMPTY}
        });
        assertEquals(FALSE, puzzle.isValid(position));
    }

}
