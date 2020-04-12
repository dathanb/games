package com.jedaway.game;

/**
 *
 */
public interface MoveGenerator<GameType extends Game<GameType, MoveType>, MoveType extends Move> {
    MoveType[] getMoves(GameType game);
}
