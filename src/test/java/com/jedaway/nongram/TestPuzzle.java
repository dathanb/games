package com.jedaway.nongram;

import java.util.Arrays;
import java.util.List;

public class TestPuzzle implements Puzzle {
    public int getNumRows() {
        return 10;
    }

    public int getNumCols() {
        return 10;
    }

    public List<Constraint> getColumnConstraints() {
        return Arrays.asList(
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
        );
    }

    public List<Constraint> getRowConstraints() {
        return Arrays.asList(
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
        );
    }

    @Override
    public Logical isValid(Position position) {
        return null;
    }
}
