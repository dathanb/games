package com.jedaway.game;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOG = LoggerFactory.getLogger(MaxStrategy.class);

    private static final double EARLY_RETURN_THRESHOLD = Double.POSITIVE_INFINITY;
    private static final String NUM_POSITIONS_EVALUATED = "com.jedaway.game.maxStrategy.numPositionsEvaluated";
    private static final String POSITION_EVALUATION_METER = "com.jedaway.game.maxStrategy.positionEvaluatedMeter";
    /**
     * Penalize moves at each additional level of depth by this much to encourage taking the fastest path to a good position.
     *
     * I don't like doing this because I don't think it translates well to other games; but it makes sense for the SortingGame, so trying it out.
     */
    private static final double LEVEL_ADJUSTMENT = 0.01;
    private final PositionEvaluator<GameType, MoveType> positionEvaluator;
    private final MoveGenerator<GameType, MoveType> moveGenerator;
    private final int maxDepth;
    private final MetricRegistry metrics;
    private final Counter numPositionsEvaluated;
    private final Meter positionEvaluationMeter;

    public MaxStrategy(PositionEvaluator<GameType, MoveType> positionEvaluator,
                       MoveGenerator<GameType, MoveType> moveGenerator,
                       int maxDepth,
                       MetricRegistry metrics) {
        this.positionEvaluator = positionEvaluator;
        this.moveGenerator = moveGenerator;
        this.maxDepth = maxDepth;
        this.metrics = metrics;
        this.numPositionsEvaluated = metrics.counter(NUM_POSITIONS_EVALUATED);
        this.positionEvaluationMeter = metrics.meter(POSITION_EVALUATION_METER);
    }

    @Override
    public Optional<MoveType> chooseMove(GameType game) {
        // Just a breadth-first traversal to maximum configured depth

        Queue<MaxMoveTree<GameType, MoveType>> pending = new ArrayBlockingQueue<>(1);
        MaxMoveTree<GameType, MoveType> root = new MaxMoveTree<>(null, game);
        root.setScore(positionEvaluator.evaluate(game));
        pending.add(root);

        for (int depth = 0; depth < maxDepth; depth++) {
            double scoreAdjustment = -depth * LEVEL_ADJUSTMENT;
            Queue<MaxMoveTree<GameType, MoveType>> level = pending;
            pending = new LinkedBlockingDeque<>();
            int positionsToVisit = level.size();
            LOG.debug("Checked ply {}; {} positions enqueued", depth, positionsToVisit);
            Stopwatch stopwatch = Stopwatch.createStarted();
            traverseOneLevel(level, pending, scoreAdjustment, depth);
            stopwatch.stop();

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

    private void traverseOneLevel(Queue<MaxMoveTree<GameType, MoveType>> level, Queue<MaxMoveTree<GameType, MoveType>> pending, double adjustment, int depth) {
        for (MaxMoveTree<GameType, MoveType> position : level) {
            for (MoveType move : moveGenerator.getMoves(position.getGame())) {
                GameType newGameState = position.getGame().apply(move);
                MaxMoveTree<GameType, MoveType> newPosition = new MaxMoveTree<>(position, newGameState);
                position.add(move, newPosition);
                newPosition.setScore(positionEvaluator.evaluate(newGameState) + adjustment);
                numPositionsEvaluated.inc();
                positionEvaluationMeter.mark();

                if (depth < maxDepth - 1) {
                    pending.add(newPosition); // TODO: when already at max depth, don't enqueue more positions
                }
            }
        }
    }

}


