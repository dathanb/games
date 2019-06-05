package com.jedaway.game;

public interface Puzzle<T extends Position> {
    /**
     * Determine whether, within the constraints of the puzzle, the given position may be valid.
     * @param position
     * @return
     */
    Logical isValid(T position);
}
