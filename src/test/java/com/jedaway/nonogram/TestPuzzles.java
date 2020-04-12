package com.jedaway.nonogram;

import com.jedaway.game.Logical;

import java.util.Arrays;
import java.util.Collections;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.game.Logical.TRUE;
import static com.jedaway.nonogram.CellState.OFF;
import static com.jedaway.nonogram.CellState.ON;

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
            ),
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
            )
    );

    public static final NonogramGame BUFFALO_SOLUTION = new NonogramGame(new CellState[][]{
            {OFF, OFF, OFF, ON, ON, ON, ON, ON, ON, ON},
            {ON, OFF, OFF, ON, ON, ON, ON, ON, ON, OFF},
            {ON, OFF, ON, ON, OFF, OFF, ON, ON, ON, OFF},
            {ON, ON, ON, ON, ON, ON, OFF, ON, ON, OFF},
            {ON, ON, ON, ON, OFF, ON, OFF, OFF, OFF, OFF},
            {OFF, OFF, ON, ON, ON, ON, ON, ON, ON, ON},
            {OFF, ON, ON, ON, ON, ON, ON, ON, ON, ON},
            {OFF, ON, ON, ON, ON, ON, ON, ON, ON, ON},
            {OFF, OFF, OFF, ON, ON, ON, ON, ON, ON, ON},
            {OFF, OFF, OFF, ON, ON, ON, ON, ON, ON, ON}
    });

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
            ),
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
                    new NonogramConstraint(4, 3)
            )
    );

    public static final NonogramPuzzle TRIVIAL = new NonogramPuzzle(
            Collections.singletonList(new NonogramConstraint(1)),
            Collections.singletonList(new NonogramConstraint(1))
    );

    public static final NonogramPuzzle EASY = new NonogramPuzzle(
            Arrays.asList(
                    new NonogramConstraint(2, 1),
                    new NonogramConstraint(2, 2),
                    new NonogramConstraint(2),
                    new NonogramConstraint(1),
                    new NonogramConstraint(1, 1, 1)
            ),
            Arrays.asList(
                    new NonogramConstraint(3, 1),
                    new NonogramConstraint(3),
                    new NonogramConstraint(1),
                    new NonogramConstraint(1),
                    new NonogramConstraint(2, 2)
            )
    );

    public static final NonogramGame EASY_SOLUTION = new NonogramGame(new CellState[][]{
            {ON, ON, OFF, OFF, ON},
            {ON, ON, OFF, ON, ON},
            {ON, ON, OFF, OFF, OFF},
            {OFF, OFF, OFF, OFF, ON},
            {ON, OFF, ON, OFF, ON}
    });

}
