package com.jedaway.game;

/**
 * The state of the board at a point in time.
 */
public interface Position<T extends Move> {
    /**
     * Get all the possible moves from a given position.
     * @return
     */
    T[] getMoves();
}
