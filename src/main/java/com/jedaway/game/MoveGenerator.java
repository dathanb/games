package com.jedaway.game;

import java.util.List;

/**
 * A MoveGenerator generates moves from a game state. It is not necessarily the case that the MoveGenerator generates *all* moves from a game state,
 * but any moves not generated will not be considered.
 */
public interface MoveGenerator<GameType extends Game<GameType, MoveType>, MoveType extends Move> {
    List<MoveType> getMoves(GameType game);
}
