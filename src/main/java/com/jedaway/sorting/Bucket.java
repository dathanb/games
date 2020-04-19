package com.jedaway.sorting;

import com.jedaway.game.PositionEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * In concept, a bucket is a LIFO stack of fixed capacity that allows game pieces ("balls") to be pushed and popped.
 *
 * In our SortingGame, a bucket is encoded into a long; the Bucket class contains static methods that make working with
 * those integers easier.
 */
public class Bucket {
    private final int capacity;
    private final List<Color> stack;

    public Bucket(int capacity) {
        this.capacity = capacity;
        stack = new Stack<>();
    }

    /**
     * Copy constructor
     * @param other
     */
    public Bucket(Bucket other) {
        capacity = other.capacity;
        stack = new ArrayList<>(other.stack);
    }

    public int getCapacity() {
        return capacity;
    }

    public void push(Color color) {
        if (stack.size() >= capacity) {
            throw new RuntimeException("Already at capacity");
        }
        stack.add(color);
    }

    public Color pop() {
        return stack.remove(stack.size()-1);
    }

    /**
     * @return the number of items currently in the bucket
     */
    public int size() {
        return stack.size();
    }

    /**
     * Get the values currently in the bucket. List index zero is the bottom of the bucket.
     *
     * This value method is intended for use by {@link PositionEvaluator}s.
     */
    List<Color> getValues() {
        return stack;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return capacity == bucket.capacity &&
                Objects.equals(stack, bucket.stack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, stack);
    }

    @Override
    public String toString() {
        return "Bucket{" +
                "capacity=" + capacity +
                ", stack=" + stack +
                '}';
    }
}
