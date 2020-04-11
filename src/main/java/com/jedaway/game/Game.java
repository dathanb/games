package com.jedaway.game;

/**
 * The state of the game to a particular point, including move and position history.
 */
public interface Game<MoveType extends Move> {

    /**
     * Get all the possible moves from a given position.
     * @return
     */
    MoveType[] getMoves();
}
