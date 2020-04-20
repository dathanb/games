package com.jedaway.game;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;
import com.google.common.collect.MinMaxPriorityQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Queue;

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

    private static final int MAX_QUEUE_SIZE = 1_000_000;
    private static final double EARLY_RETURN_THRESHOLD = 16;
    private static final String NUM_POSITIONS_EVALUATED = "com.jedaway.game.maxStrategy.numPositionsEvaluated";
    private static final String POSITION_EVALUATION_METER = "com.jedaway.game.maxStrategy.positionEvaluatedMeter";
    /**
     * Penalize moves at each additional level of depth by this much to encourage taking the fastest path to a good position.
     * <p>
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

        MinMaxPriorityQueue<MaxMoveTree<GameType, MoveType>> pending = MinMaxPriorityQueue.orderedBy(Comparator.<MaxMoveTree<GameType,MoveType>>comparingDouble(MaxMoveTree::getScore))
                .maximumSize(MAX_QUEUE_SIZE)
                .create();
        MaxMoveTree<GameType, MoveType> root = new MaxMoveTree<>(null, game);
        root.setScore(positionEvaluator.evaluate(game));

        MaxMoveTree<GameType, MoveType> latest = root;

        do {
            visit(latest, pending);
            latest = pending.poll();
        } while (latest != null && latest.getScore() - root.getScore() > -EARLY_RETURN_THRESHOLD);

        return getNextMoveInPath(root, latest);
    }

    private void visit(MaxMoveTree<GameType, MoveType> tree, Queue<MaxMoveTree<GameType, MoveType>> pending) {
        for (MoveType move : moveGenerator.getMoves(tree.getGame())) {
            GameType newGameState = tree.getGame().apply(move);
            MaxMoveTree<GameType, MoveType> newPosition = new MaxMoveTree<>(tree, newGameState);
            tree.add(move, newPosition);
            newPosition.setScore(-(positionEvaluator.evaluate(newGameState) - newPosition.getDepth() * LEVEL_ADJUSTMENT));
            pending.add(newPosition);
            numPositionsEvaluated.inc();
            positionEvaluationMeter.mark();
        }
    }

    private Optional<MoveType> getNextMoveInPath(MaxMoveTree<GameType, MoveType> root, MaxMoveTree<GameType, MoveType> winningMove) {
        MaxMoveTree<GameType, MoveType> node = winningMove;
        while (!node.getParent().equals(root)) {
            node = node.getParent();
        }

        final MaxMoveTree<GameType, MoveType> lambdaNode = node;

        // TODO: make this more efficient
        // But not a high-priority todo, since it happens a tiny fraction as often as move evaluation
        return root.getChildren()
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue().getGame().equals(lambdaNode.getGame()))
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


