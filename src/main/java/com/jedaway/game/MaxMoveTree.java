package com.jedaway.game;

import java.util.HashMap;
import java.util.Map;

/**
 * A tree of moves starting from some base game state. Used by MaxMoveStrategy to choose moves.
 *
 * When a score is associated with a node, if the score is greater than its parent's score, the new score is also assigned to the parent.
 */
class MaxMoveTree<GameType extends Game<GameType, MoveType>, MoveType extends Move> {
    private final GameType game;
    private final MaxMoveTree<GameType, MoveType> parent;

    private Map<MoveType, GameType> childMoves;
    private double score = Double.NEGATIVE_INFINITY;

    public MaxMoveTree(MaxMoveTree<GameType, MoveType> parent, GameType game) {
        this.parent = parent;
        this.game = game;
        childMoves = new HashMap<>();
    }

    /**
     * Associate a score with this game state, and if it's greater than the
     * @param score
     */
    public void setScore(double score) {
        this.score = score;
        propagateMaxScore(score);
    }

    protected void propagateMaxScore(double newScore) {
        if (this.parent != null && this.parent.score < newScore) {
            this.parent.setScore(newScore);
        }
    }

    public GameType getGame() {
        return game;
    }
}
