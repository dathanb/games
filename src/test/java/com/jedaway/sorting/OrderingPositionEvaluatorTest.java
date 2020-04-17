package com.jedaway.sorting;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("OrderingPositionEvaluator")
public class OrderingPositionEvaluatorTest {

    @DisplayName("#evaluate")
    public static class EvaluateMethod {
        @Test
        @DisplayName("returns positive infinity for terminal state")
        public void returnsPositiveInfinityForTerminalState() {
            List<Bucket> buckets = new ArrayList<>();
            Bucket bucket = new Bucket(2);
            bucket.push(Color.RED);
            bucket.push(Color.RED);
            buckets.add(bucket);
            buckets.add(new Bucket(2));
            SortingGame game = new SortingGame(buckets);
            assertTrue(game.isTerminal());
            assertEquals(Double.POSITIVE_INFINITY, new OrderingPositionEvaluator().evaluate(game));
        }

        @Test
        @DisplayName("gives a bonus to groups anchored at the bottom of the bucket")
        public void rewardsAnchorGroups() {
            List<Bucket> buckets = new ArrayList<>();
            Bucket bucket = new Bucket(3);
            bucket.push(Color.RED);
            bucket.push(Color.RED);
            bucket.push(Color.ORANGE);
            buckets.add(bucket);
            buckets.add(new Bucket(2));
            SortingGame game = new SortingGame(buckets);
            assertFalse(game.isTerminal());
            assertEquals(5.0, new OrderingPositionEvaluator().evaluate(game));
        }
    }
}
