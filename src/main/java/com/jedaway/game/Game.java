package com.jedaway.game;

/**
 * The state of the game to a particular point, including move and position history.
 *
 * The Game should be immutable, as we'll be creating lots of them.
 */
public interface Game<MoveType extends Move> {
    /**
     * Compose the given move with the current game state, returning a new game state.
     * @param move The move to apply
     * @return The new game state resulting from applying the move to the current game state.
     */
    Game<MoveType> apply(MoveType move);

    /**
     * Whether the game state represents a terminal position.
     * @return true if the game state is a terminal (play should not continue afterward);
     *         false if the game state is an intermediate position (play should continue from this state)
     */
    boolean isTerminal();
}
