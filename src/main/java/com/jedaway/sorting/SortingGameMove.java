package com.jedaway.sorting;

import com.jedaway.game.Move;

import java.util.Objects;

/**
 * Move of a color from one bucket to another.
 */
public class SortingGameMove implements Move {
    private final int sourceBucket;
    private final int destinationBucket;

    public SortingGameMove(int sourceBucket, int destinationBucket) {
        this.sourceBucket = sourceBucket;
        this.destinationBucket = destinationBucket;
    }

    public int getSourceBucket() {
        return sourceBucket;
    }

    public int getDestinationBucket() {
        return destinationBucket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SortingGameMove that = (SortingGameMove) o;
        return sourceBucket == that.sourceBucket &&
                destinationBucket == that.destinationBucket;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceBucket, destinationBucket);
    }

    @Override
    public String toString() {
        return "SortingGameMove{" +
                "sourceBucket=" + sourceBucket +
                ", destinationBucket=" + destinationBucket +
                '}';
    }
}
