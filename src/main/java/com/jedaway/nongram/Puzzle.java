package com.jedaway.nongram;

public interface Puzzle {
    /**
     * Determine whether, within the constraints of the puzzle, the given position may be valid.
     * @param position
     * @return
     */
    Logical isValid(Position position);
}
