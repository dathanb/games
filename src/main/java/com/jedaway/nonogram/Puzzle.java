package com.jedaway.nonogram;

import com.jedaway.game.Logical;

public interface Puzzle<T> {
    /**
     * Determine whether, within the constraints of the puzzle, the given position may be valid.
     * @param position
     * @return
     */
    Logical isValid(T position);
}
