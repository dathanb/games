package com.jedaway.sorting;

import com.google.common.util.concurrent.AtomicDouble;
import com.jedaway.game.PositionEvaluator;

import java.util.List;

/**
 * The SimplePositionEvaluator evaluates a {@link SortingGame} by scoring it only based on how many matching colors are piled on each other in contact
 * with the bottom of the bucket.
 */
public class SimplePositionEvaluator implements PositionEvaluator<SortingGame, SortingGameMove> {
    @Override
    public double evaluate(SortingGame gameState) {
        final AtomicDouble score = new AtomicDouble(0);
        gameState.getBuckets().stream()
                .filter(bucket -> bucket.size() > 0)
                .map(Bucket::getValues)
                .forEach(colors -> {
                    double bucketScore = 1;
                    Color previousColor = colors.get(0);
                    for (int i = 1; i < colors.size(); i++) {
                        if (colors.get(i) != previousColor) {
                            break;
                        }
                        bucketScore *= 2;
                    }

                    score.addAndGet(bucketScore);
                });
        return score.get();
    }
}
