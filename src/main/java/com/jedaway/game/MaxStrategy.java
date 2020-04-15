package com.jedaway.game;

import com.google.common.collect.Lists;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * The MaxStrategy recursively descends the move tree to a particular depth, and returns the move with the greatest possible outcome.
 *
 * Note that this is only appropriate for single-player (or cooperative) games, because it assumes play isn't competitive. For a competitive game,
 * we'd want to use the {@link MinimaxStrategy}.
 * @param <GameType>
 * @param <MoveType>
 */
public class MaxStrategy<GameType extends Game<GameType, MoveType>, MoveType extends Move> implements MoveStrategy<GameType, MoveType> {
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
        // TODO: make this more efficient
        MaxMoveTree<GameType, MoveType> moveTree = new MaxMoveTree<>(null, game);

        // How would I want to use the moveTree?
        // Not the MoveTree, per se, but I'm thinking of a producer / consumer impl, where we basically just ask for all the moves according to
        // some strategy, and we score them.
        // That's just begging the question of how the strategy works, though.
        // So let's leave higher-level abstractions for later.
        // For now, let's just do a classic breadth-first traversal, capping it at some set depth

        int depth = 0;
        Queue<MaxMoveTree<GameType, MoveType>> pending = new ArrayBlockingQueue<>(1);
        pending.add(new MaxMoveTree<>(null, game));

        // by using a do ... while loop, we evaluate the
        do {
            Queue<MaxMoveTree<GameType, MoveType>> level = pending;
            pending = new ArrayBlockingQueue<>(1024);

            for (MaxMoveTree<GameType, MoveType> position: level){
                position.setScore(positionEvaluator.evaluate(position.getGame()));
                if (depth != maxDepth) {
                    continue; // skip adding another level of depth if we're already at max
                }
//                pending.addAll(moveGenerator.getMoves(position.getGame()));
            }
            // TODO: iterate over all the trees in level, and add all their
        } while (++depth <= maxDepth);
        HashMap<MoveType, Double> scores = new HashMap<>();
        for (MoveType move: moveGenerator.getMoves(game)) {
            GameType newPosition = game.apply(move);
            double score = positionEvaluator.evaluate(newPosition);
            scores.put(move, score);
        }

        return scores.entrySet().stream()
                .reduce((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? entry1 : entry2)
                .map(Map.Entry::getKey);
    }

}


