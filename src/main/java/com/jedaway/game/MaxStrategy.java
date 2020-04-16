package com.jedaway.game;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The MaxStrategy recursively descends the move tree to a particular depth, and returns the move with the greatest possible outcome.
 * <p>
 * Note that this is only appropriate for single-player (or cooperative) games, because it assumes play isn't competitive. For a competitive game,
 * we'd want to use the {@link MinimaxStrategy}.
 *
 * @param <GameType>
 * @param <MoveType>
 */
public class MaxStrategy<GameType extends Game<GameType, MoveType>, MoveType extends Move> implements MoveStrategy<GameType, MoveType> {
    private static final double EARLY_RETURN_THRESHOLD = Double.POSITIVE_INFINITY;
    private final PositionEvaluator<GameType, MoveType> positionEvaluator;
    private final MoveGenerator<GameType, MoveType> moveGenerator;
    private final int maxDepth;

    public MaxStrategy(PositionEvaluator<GameType, MoveType> positionEvaluator,
                       MoveGenerator<GameType, MoveType> moveGenerator,
                       int maxDepth) {
        this.positionEvaluator = positionEvaluator;
        this.moveGenerator = moveGenerator;
        this.maxDepth = maxDepth;
        // TODO: validate maxDepth >= 1
    }

    @Override
    public Optional<MoveType> chooseMove(GameType game) {
        // Just a breadth-first traversal to maximum configured depth

        Queue<MaxMoveTree<GameType, MoveType>> pending = new ArrayBlockingQueue<>(1);
        MaxMoveTree<GameType, MoveType> root = new MaxMoveTree<>(null, game);
        root.setScore(positionEvaluator.evaluate(game));
        pending.add(root);

        for (int i = 0; i < maxDepth; i++) {
            Queue<MaxMoveTree<GameType, MoveType>> level = pending;
            pending = new LinkedBlockingDeque<>();
            traverseOneLevel(level, pending);
            if (root.getScore() >= EARLY_RETURN_THRESHOLD) {
                break;
            }
        }

        // return the best move
        return root.getChildren()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getScore() == root.getScore())
                .findFirst()
                .map(Map.Entry::getKey);
    }

    private void traverseOneLevel(Queue<MaxMoveTree<GameType, MoveType>> level, Queue<MaxMoveTree<GameType, MoveType>> pending) {
        for (MaxMoveTree<GameType, MoveType> position : level) {
            for (MoveType move : moveGenerator.getMoves(position.getGame())) {
                GameType newGameState = position.getGame().apply(move);
                MaxMoveTree<GameType, MoveType> newPosition = new MaxMoveTree<>(position, newGameState);
                position.add(move, newPosition);
                newPosition.setScore(positionEvaluator.evaluate(newGameState));

                pending.add(newPosition); // TODO: when already at max depth, don't enqueue more positions
            }
        }
    }

}


