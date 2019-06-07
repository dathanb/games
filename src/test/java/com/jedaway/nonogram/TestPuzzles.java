package com.jedaway.nonogram;

import com.jedaway.game.Logical;

import java.util.Arrays;
import java.util.Collections;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.game.Logical.TRUE;

public class TestPuzzles {
    public static final NonogramPuzzle PARROT = new NonogramPuzzle(
            Arrays.asList(
                    new NonogramConstraint(5),
                    new NonogramConstraint(5),
                    new NonogramConstraint(5, 1),
                    new NonogramConstraint(5, 2),
                    new NonogramConstraint(1, 1),
                    new NonogramConstraint(10),
                    new NonogramConstraint(10),
                    new NonogramConstraint(2, 5),
                    new NonogramConstraint(2, 1, 5),
                    new NonogramConstraint(2, 5)
            ),
            Arrays.asList(
                    new NonogramConstraint(5),
                    new NonogramConstraint(8),
                    new NonogramConstraint(3, 2),
                    new NonogramConstraint(4, 2, 1),
                    new NonogramConstraint(4, 2),
                    new NonogramConstraint(4, 5),
                    new NonogramConstraint(2, 5),
                    new NonogramConstraint(1, 2, 5),
                    new NonogramConstraint(7),
                    new NonogramConstraint(5)
            )
    );

    public static final NonogramPuzzle BUFFALO = new NonogramPuzzle(
            Arrays.asList(
                    new NonogramConstraint(4),
                    new NonogramConstraint(2, 2),
                    new NonogramConstraint(6),
                    new NonogramConstraint(10),
                    new NonogramConstraint(2, 1, 5),
                    new NonogramConstraint(2, 7),
                    new NonogramConstraint(3, 5),
                    new NonogramConstraint(4, 5),
                    new NonogramConstraint(4, 5),
                    new NonogramConstraint(1, 5)
            ),
            Arrays.asList(
                    new NonogramConstraint(7),
                    new NonogramConstraint(1, 6),
                    new NonogramConstraint(1, 2, 3),
                    new NonogramConstraint(6, 2),
                    new NonogramConstraint(4, 1),
                    new NonogramConstraint(8),
                    new NonogramConstraint(9),
                    new NonogramConstraint(9),
                    new NonogramConstraint(7),
                    new NonogramConstraint(7)
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
                    new NonogramConstraint(6),
                    new NonogramConstraint(6),
                    new NonogramConstraint(6, 1),
                    new NonogramConstraint(7, 1),
                    new NonogramConstraint(10),
                    new NonogramConstraint(10),
                    new NonogramConstraint(7, 1),
                    new NonogramConstraint(6, 1),
                    new NonogramConstraint(8),
                    new NonogramConstraint(4, 1)
            ),
            Arrays.asList(
                    new NonogramConstraint(6),
                    new NonogramConstraint(9),
                    new NonogramConstraint(10),
                    new NonogramConstraint(10),
                    new NonogramConstraint(10),
                    new NonogramConstraint(9),
                    new NonogramConstraint(2, 4, 1),
                    new NonogramConstraint(1, 1, 2, 3),
                    new NonogramConstraint(4, 1),
                    new NonogramConstraint(2)
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

    public static final NonogramPuzzle HARD = new NonogramPuzzle(
            Arrays.asList(
                    new NonogramConstraint(1, 3),
                    new NonogramConstraint(6, 1),
                    new NonogramConstraint(6, 2),
                    new NonogramConstraint(2, 2, 1),
                    new NonogramConstraint(2, 1, 1, 1),
                    new NonogramConstraint(2, 2, 1, 3),
                    new NonogramConstraint(3, 3, 2),
                    new NonogramConstraint(12),
                    new NonogramConstraint(11, 1),
                    new NonogramConstraint(8, 1),
                    new NonogramConstraint(6, 4),
                    new NonogramConstraint(6),
                    new NonogramConstraint(8),
                    new NonogramConstraint(15),
                    new NonogramConstraint(4,3)
            ),
            Arrays.asList(
                    new NonogramConstraint(5, 1, 1),
                    new NonogramConstraint(8, 1),
                    new NonogramConstraint(1, 5, 1),
                    new NonogramConstraint(1, 1, 4, 1),
                    new NonogramConstraint(1, 2, 4, 1),
                    new NonogramConstraint(1, 4, 2),
                    new NonogramConstraint(4, 4, 3),
                    new NonogramConstraint(9, 3),
                    new NonogramConstraint(2, 4, 4),
                    new NonogramConstraint(2, 2, 3),
                    new NonogramConstraint(2, 2, 3),
                    new NonogramConstraint(3, 2, 5),
                    new NonogramConstraint(5, 7),
                    new NonogramConstraint(1, 1, 5),
                    new NonogramConstraint(1, 1, 1)
            )
    );

    public static final NonogramPuzzle TRIVIAL = new NonogramPuzzle(
            Collections.singletonList(new NonogramConstraint(1)),
            Collections.singletonList(new NonogramConstraint(1))
    );
}
