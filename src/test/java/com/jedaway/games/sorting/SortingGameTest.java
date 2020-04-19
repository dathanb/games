package com.jedaway.games.sorting;

import com.jedaway.sorting.Bucket;
import com.jedaway.sorting.Color;
import com.jedaway.sorting.SortingGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SortingGame")
public class SortingGameTest {
    @Nested
    @DisplayName(".randomGame")
    public class RandomGame {
        SortingGame sortingGame;

        @BeforeEach
        public void beforeEach() {
            sortingGame = SortingGame.randomGame();
        }

        @Test
        @DisplayName("creates the right number of buckets")
        public void createsTheRightNumberOfBuckets() {
            assertEquals(16, sortingGame.getBuckets().size());
        }

        @Test
        @DisplayName("creates each bucket with the right size and capacity")
        public void createsBucketsTheRightSize() {
            for (int i = 0; i < sortingGame.getBuckets().size() - 1; i++) {
                Bucket bucket = sortingGame.getBuckets().get(i);
                assertEquals(4, bucket.getCapacity());
                assertEquals(4, bucket.size());
            }
            assertEquals(0, sortingGame.getBuckets().get(sortingGame.getBuckets().size()-1).size());
        }

        @Test
        @DisplayName("uses each color the right number of times")
        public void usesEachColorTheRightNumberOfTimes() {
            HashMap<Color, Integer> colorCounts = new HashMap<>();
            for (int i = 0; i < sortingGame.getBuckets().size() - 1; i++) {
                Bucket bucket = sortingGame.getBuckets().get(i);
                colorCounts.compute(bucket.peek(), (color, colorCount) -> (colorCount == null ? 0 : colorCount) + 1);
                bucket = bucket.pop();
                colorCounts.compute(bucket.peek(), (color, colorCount) -> (colorCount == null ? 0 : colorCount) + 1);
                bucket = bucket.pop();
                colorCounts.compute(bucket.peek(), (color, colorCount) -> (colorCount == null ? 0 : colorCount) + 1);
                bucket = bucket.pop();
                colorCounts.compute(bucket.peek(), (color, colorCount) -> (colorCount == null ? 0 : colorCount) + 1);
                bucket.pop();
            }
            assertTrue(colorCounts.values().stream().allMatch(i -> i == 4));
        }
    }
}
