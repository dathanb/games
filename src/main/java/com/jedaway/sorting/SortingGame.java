package com.jedaway.sorting;

import com.jedaway.game.Game;
import com.jedaway.game.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * An instance of the SortingGame up to a particular point in time, including move history.
 * <p>
 * Its primary roles are (1) to support driving the game by transitioning from one game state to another via {@link Move}s, and (2) to support deduping
 * game states for the purpose of pruning search trees.
 * <p>
 * A SortingGame is a set of N colors, and N+1 buckets that will each hold up to M balls.
 */
public class SortingGame implements Game<SortingGame, SortingGameMove> {
    private final List<Bucket> buckets;

    public SortingGame(List<Bucket> startingBuckets) {
        this.buckets = startingBuckets;
    }

    public static SortingGame randomGame(int numColors, int bucketSize) {
        List<Color> colorsToPlace = new ArrayList<>(numColors * bucketSize);
        for (int i=0; i<numColors; i++) {
            for (int j=0; j<bucketSize; j++) {
                colorsToPlace.add(Color.values()[i]);
            }
        }
        Collections.shuffle(colorsToPlace);

        List<Bucket> buckets = new ArrayList<>(numColors + 1);
        for (int i=0; i<numColors; i++) {
            buckets.add(new Bucket(bucketSize));
            for (int j = 0; j < bucketSize; j++) {
                buckets.get(i).push(colorsToPlace.get(colorsToPlace.size()-1));
                colorsToPlace.remove(colorsToPlace.size()-1);
            }
        }
        buckets.add(new Bucket(bucketSize));

        return new SortingGame(buckets);
    }

    @Override
    public SortingGame apply(SortingGameMove move) {
        return null;
    }

    @Override
    public boolean isTerminal() {
        return false;
    }

    public List<Bucket> getBuckets() {
        // TODO: return an immutable wrapper, or return a copy; not hard to do for the list, but requires extra work for the Buckets
        return buckets;

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortingGame that = (SortingGame) o;
        return Objects.equals(buckets, that.buckets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buckets);
    }

    @Override
    public String toString() {
        return "SortingGame{" +
                "buckets=" + buckets +
                '}';
    }
}
