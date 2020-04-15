package com.jedaway.game;

/**
 * A PositionEvaluator is responsible for assigning a score to a particular game state.
 * @param <GameType>
 * @param <MoveType>
 */
public interface PositionEvaluator<GameType extends Game<GameType, MoveType>, MoveType extends Move> {
    /**
     * Evaluate a game state and return a score.
     * @param gameState The game state to evaluate.
     * @return Double.NEGATIVE_INFINITY if it's a losing position,
     *         Double.POSITIVE_INFINITY is it's a winning position,
     *         some valid double representing a "score" for the position, where higher scores are better.
     */
    double evaluate(GameType gameState);
}
