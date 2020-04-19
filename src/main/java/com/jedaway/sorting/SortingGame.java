package com.jedaway.sorting;

import com.jedaway.game.Game;
import com.jedaway.game.Move;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * An instance of the SortingGame up to a particular point in time, including move history.
 * <p>
 * Its primary roles are (1) to support driving the game by transitioning from one game state to another via {@link Move}s, and (2) to support deduping
 * game states for the purpose of pruning search trees.
 * <p>
 * A SortingGame is a set of N colors, and N+1 buckets that will each hold up to M balls.
 */
public class SortingGame implements Game<SortingGame, SortingGameMove> {
    private List<Bucket> buckets;
    private static final int BUCKET_SIZE = 4;
    private static final int NUM_COLORS = 15;

    public SortingGame(List<Bucket> startingBuckets) {
        this.buckets = startingBuckets;
    }

    public static SortingGame randomGame() {
        return randomGame(new Random());
    }

    public static SortingGame randomGame(Random random) {
        List<Color> colorsToPlace = new ArrayList<>(NUM_COLORS * BUCKET_SIZE);
        for (int i = 0; i < NUM_COLORS; i++) {
            for (int j = 0; j < BUCKET_SIZE; j++) {
                colorsToPlace.add(Color.values()[i]);
            }
        }
        Collections.shuffle(colorsToPlace, random);

        List<Bucket> buckets = new ArrayList<>(NUM_COLORS + 1);
        for (int i = 0; i < NUM_COLORS; i++) {
            buckets.add(new Bucket(BUCKET_SIZE));
            for (int j = 0; j < BUCKET_SIZE; j++) {
                buckets.get(i).push(colorsToPlace.get(colorsToPlace.size() - 1));
                colorsToPlace.remove(colorsToPlace.size() - 1);
            }
        }
        buckets.add(new Bucket(BUCKET_SIZE));

        return new SortingGame(buckets);
    }

    @Override
    public SortingGame apply(SortingGameMove move) {
        return applyWithShallowCopy(move);
    }

    private SortingGame applyWithDeepCopy(SortingGameMove move) {
        // get copy of buckets
        List<Bucket> newBuckets = buckets.stream().map(Bucket::new).collect(Collectors.toList());

        // apply the move
        Color color = newBuckets.get(move.getSourceBucket()).pop();
        newBuckets.get(move.getDestinationBucket()).push(color);

        return new SortingGame(newBuckets);
    }

    private SortingGame applyWithShallowCopy(SortingGameMove move) {
        int minModifiedBucket = Math.min(move.getSourceBucket(), move.getDestinationBucket());
        int maxModifiedBucket = Math.max(move.getSourceBucket(), move.getDestinationBucket());

        // get the modified buckets
        Bucket newSourceBucket = new Bucket(buckets.get(move.getSourceBucket()));
        Bucket newDestinationBucket = new Bucket(buckets.get(move.getDestinationBucket()));
        newDestinationBucket.push(newSourceBucket.pop());

        List<Bucket> newBuckets = new ArrayList<>();

        // shallow all buckets except the modified buckets
        for (int i = 0; i < minModifiedBucket; i++) {
            newBuckets.add(buckets.get(i));
        }
        newBuckets.add(minModifiedBucket == move.getSourceBucket() ? newSourceBucket : newDestinationBucket);
        for (int i = minModifiedBucket + 1; i < maxModifiedBucket; i++) {
            newBuckets.add(buckets.get(i));
        }
        newBuckets.add(maxModifiedBucket == move.getSourceBucket() ? newSourceBucket : newDestinationBucket);
        for (int i = maxModifiedBucket + 1; i < buckets.size(); i++) {
            newBuckets.add(buckets.get(i));
        }

        return new SortingGame(newBuckets);
    }

    @Override
    public boolean isTerminal() {
        return buckets.stream()
                .allMatch(colors -> (colors.size() == buckets.get(0).getCapacity() || colors.size() == 0) && colors.getValues()
                        .stream()
                        .allMatch(color -> color == colors.getValues().get(0)));
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
        StringBuilder sb = new StringBuilder();
        for (int row = buckets.get(0).getCapacity() - 1; row >= 0; row--) {
            for (Bucket bucket : buckets) {
                String value = "";
                if (bucket.size() > row) {
                    value = bucket.getValues().get(row).name();
                }
                sb.append(String.format("%8s", value));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
