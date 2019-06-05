package com.jedaway.nongram;

import java.util.Arrays;

import static com.jedaway.nongram.Logical.FALSE;
import static com.jedaway.nongram.Logical.TRUE;

public class TestPuzzles {
    public static final NonogramPuzzle PARROT = new NonogramPuzzle(
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

    public static final NonogramPuzzle BUFFALO = new NonogramPuzzle(
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

    public static final Logical[][] BUFFALO_SOLUTION = new Logical[][]{
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

    public static final NonogramPuzzle BAOBAB = new NonogramPuzzle(
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

    public static final Logical[][] BAOBAB_SOLUTION = new Logical[][]{
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
