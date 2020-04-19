package com.jedaway.sorting;

import com.jedaway.game.PositionEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * In concept, a bucket is a LIFO stack of fixed capacity that allows game pieces ("balls") to be pushed and popped.
 * <p>
 * In our SortingGame, a bucket is encoded into a long; the Bucket class contains static methods that make working with
 * those integers easier.
 */
public class Bucket {
    public final int capacity;
    private final long stack;
    private final int size;

    public Bucket(int capacity) {
        this.capacity = capacity;
        stack = 0;
        size = 0;
    }

    /**
     * Copy constructor
     *
     * @param other
     */
    public Bucket(Bucket other) {
        this.capacity = other.capacity;
        this.stack = other.stack;
        this.size = other.size;
    }

    protected Bucket(long stack, int capacity, int size) {
        this.capacity = capacity;
        this.stack = stack;
        this.size = size;
    }

    public int getCapacity() {
        return capacity;
    }

    /**
     * Return a new bucket representing this bucket with the color pushed onto it.
     *
     * @param color the color to push
     * @return a new bucket with the provided color added
     * @throws RuntimeException if pushing the color would exceed the configured capacity of the bucket
     */
    public Bucket push(Color color) {
        if (size == capacity) {
            throw new RuntimeException("Already at capacity");
        }

        long newStack = stack | (((long)color.getCode()) << (size * 4));
        return new Bucket(newStack, capacity, size + 1);
    }

    /**
     * Return a new bucket that's the result of popping the top item off this one.
     * <p>
     * This is a little confusing because the common semantics of the common pop operation should return the popped item,
     * but I didn't want to futz with out parameters or returning a pair, etc. and this class is immutable. So instead
     * you have to {@link #peek} the top item and then {@code pop} it.
     *
     * @return a new bucket representing this bucket with the top item removed.
     */
    public Bucket pop() {
        long mask = 0xf;
        long newStack = stack & ~(mask << ((size - 1) * 4));
        return new Bucket(newStack, capacity, size - 1);
    }

    public Color peek() {
        long mask = 0xf;
        long maskedStackPart = stack & (mask << ((size - 1) * 4));
        long code = maskedStackPart >>> ((size - 1) * 4);
        return Color.getByCode(code);
    }

    /**
     * @return the number of items currently in the bucket
     */
    public int size() {
        return size;
    }

    /**
     * Get the values currently in the bucket. List index zero is the bottom of the bucket.
     * <p>
     * This value method is intended for use by {@link PositionEvaluator}s.
     */
    List<Color> getValues() {
        List<Color> colors = new ArrayList<>();
        long tempStack = stack;
        while (tempStack != 0) {
            colors.add(Color.getByCode(tempStack & 0xf));
            tempStack >>= 4;
        }
        return colors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return capacity == bucket.capacity &&
                stack == bucket.stack &&
                size == bucket.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, stack, size);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "capacity=" + capacity +
                ", stack=" + stack +
                ", size=" + size +
                '}';
    }
}
