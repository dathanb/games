package com.jedaway.sorting;

import java.util.Objects;
import java.util.Stack;

/**
 * A Bucket is a LIFO stack of fixed capacity that allows game pieces ("balls") to be pushed and popped.
 */
public class Bucket {
    private final int capacity;
    private final Stack<Color> stack;

    public Bucket(int capacity) {
        this.capacity = capacity;
        stack = new Stack<>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void push(Color color) {
        if (stack.size() >= capacity) {
            throw new RuntimeException("Already at capacity");
        }
        stack.push(color);
    }

    public Color pop() {
        return stack.pop();
    }

    /**
     * @return the number of items currently in the bucket
     */
    public int size() {
        return stack.size();
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
