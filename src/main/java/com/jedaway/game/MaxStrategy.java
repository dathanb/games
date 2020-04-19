package com.jedaway.game;

import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

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
    private static final Logger LOG = LoggerFactory.getLogger(MaxStrategy.class);

    private static final double EARLY_RETURN_THRESHOLD = Double.POSITIVE_INFINITY;
    /**
     * Penalize moves at each additional level of depth by this much to encourage taking the fastest path to a good position.
     *
     * I don't like doing this because I don't think it translates well to other games; but it makes sense for the SortingGame, so trying it out.
     */
    private static final double LEVEL_ADJUSTMENT = 0.01;
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
            double scoreAdjustment = -i * LEVEL_ADJUSTMENT;
            Queue<MaxMoveTree<GameType, MoveType>> level = pending;
            pending = new LinkedBlockingDeque<>();
            LOG.debug("Checked ply {}; {} positions enqueued", i, level.size());
            Stopwatch stopwatch = Stopwatch.createStarted();
            traverseOneLevel(level, pending, scoreAdjustment);
            stopwatch.stop();
            LOG.debug("Finished in {} milliseconds", stopwatch.elapsed(TimeUnit.MILLISECONDS));
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

    private void traverseOneLevel(Queue<MaxMoveTree<GameType, MoveType>> level, Queue<MaxMoveTree<GameType, MoveType>> pending, double adjustment) {
        for (MaxMoveTree<GameType, MoveType> position : level) {
            for (MoveType move : moveGenerator.getMoves(position.getGame())) {
                GameType newGameState = position.getGame().apply(move);
                MaxMoveTree<GameType, MoveType> newPosition = new MaxMoveTree<>(position, newGameState);
                position.add(move, newPosition);
                newPosition.setScore(positionEvaluator.evaluate(newGameState) + adjustment);

                pending.add(newPosition); // TODO: when already at max depth, don't enqueue more positions
            }
        }
    }

}


