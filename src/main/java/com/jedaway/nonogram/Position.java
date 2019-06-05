package com.jedaway.nonogram;

/**
 * The state of the board at a point in time.
 */
public interface Position {
    /**
     * Get all the possible moves from a given position.
     * @return
     */
    public Move[] getMoves();
}
