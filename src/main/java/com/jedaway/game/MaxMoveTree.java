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

    private Map<MoveType, MaxMoveTree<GameType, MoveType>> childMoves;
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

    public double getScore() {
        return score;
    }

    public Map<MoveType, MaxMoveTree<GameType, MoveType>> getChildren() {
        return childMoves;
    }

    public void add(MoveType move, GameType game, double score) {
        MaxMoveTree<GameType, MoveType> child = new MaxMoveTree<>(this, game);
        childMoves.put(move, child);
        child.setScore(score);
    }

    public void add(MoveType move, MaxMoveTree<GameType, MoveType> child) {
        this.childMoves.put(move, child);
    }

    public GameType getGame() {
        return game;
    }
}
