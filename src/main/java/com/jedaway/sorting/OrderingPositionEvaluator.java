package com.jedaway.sorting;

import com.google.common.util.concurrent.AtomicDouble;
import com.jedaway.game.PositionEvaluator;

/**
 * The OrderingPositionEvaluator evaluates a {@link SortingGame} by scoring it according to homogenous groups of colors, no matter where they are (but
 * gives extra points to homogenous groups of colors on the bottom of the bucket).
 */
public class OrderingPositionEvaluator implements PositionEvaluator<SortingGame, SortingGameMove> {
    @Override
    public double evaluate(SortingGame gameState) {
        if (gameState.isTerminal()) {
            return Double.POSITIVE_INFINITY;
        }

        return gameState.getBuckets().stream()
                .filter(bucket -> bucket.size() > 0)
                .map(Bucket::getValues)
                .reduce(0, (cumulativeScore, colors) -> {
                    int bucketScore = 0;
                    int groupScore = 1; // score for current group of consecutive homogeneous colors
                    Color previousColor = colors.get(0);
                    for (int i = 0; i < colors.size(); i++) {
                        if (colors.get(i) != previousColor) {
                            bucketScore += groupScore;
                            groupScore = 1;
                            continue;
                        }
                        groupScore *= 2;
                    }
                    bucketScore += groupScore;
                    return cumulativeScore + bucketScore;
                }, Integer::sum);
    }
}
