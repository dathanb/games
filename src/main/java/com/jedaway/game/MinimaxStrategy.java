package com.jedaway.game;

import java.util.Optional;

/**
 * A move strategy that recursively visits all possible moves from a position, and chooses the move with the best worst case.
 * @param <GameType>
 * @param <MoveType>
 */
public class MinimaxStrategy<GameType extends Game<GameType, MoveType>, MoveType extends Move> implements MoveStrategy<GameType, MoveType> {
    @Override
    public Optional<MoveType> chooseMove(GameType game) {
        return Optional.empty();
    }
}
