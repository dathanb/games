package com.jedaway.sorting;

import com.jedaway.game.PositionEvaluator;

/**
 * The OrderingPositionEvaluator evaluates a {@link SortingGame} by scoring it according to homogenous groups of colors, no matter where they are (but
 * gives extra points to homogenous groups of colors on the bottom of the bucket).
 */
public class OrderingPositionEvaluator implements PositionEvaluator<SortingGame, SortingGameMove> {
    @Override
    public double evaluate(SortingGame gameState) {
        double maxScore = (gameState.getBuckets().size() - 1) * Math.pow(2, gameState.getBuckets().get(0).capacity);

        double positionScore = gameState.getBuckets().stream()
                .filter(bucket -> bucket.size() > 0)
                .map(Bucket::getRawStack)
                .reduce(0, (cumulativeScore, stack) -> {
                    int bucketScore = 0;
                    int groupScore = 1; // score for current group of consecutive homogeneous colors
                    long previousColor = stack & 0x0f;
                    while (stack != 0) {
                        long currentColor = stack & 0x0f;
                        stack >>>= 4;
                        if (currentColor != previousColor) {
                            bucketScore += groupScore;
                            groupScore = 1;
                            continue;
                        }
                        groupScore *= 2;
                    }
                    bucketScore += groupScore;
                    return cumulativeScore + bucketScore;
                }, Integer::sum);

        if (positionScore >= maxScore) {
            return Double.POSITIVE_INFINITY; // score for terminal position
        }
        return positionScore;
    }
}
