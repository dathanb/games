package com.jedaway.nongram;

import java.util.List;

public interface Puzzle {
    int getNumRows();
    int getNumCols();

    List<Constraint> getColumnConstraints();
    List<Constraint> getRowConstraints();
}
