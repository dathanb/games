package com.jedaway.sorting;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BucketTest {
    @Test
    public void pushAndPopAreReversible() {
        Bucket emptyBucket = new Bucket(16);
        assertBucketIsEmpty(emptyBucket);
        Bucket bucket = emptyBucket;
        for (int i = 0; i < 16; i++) {
            Color color = Color.getByCode(i);
            bucket = bucket.push(color);
            assertEquals(color, bucket.peek());
        }
        for (int i = 15; i >= 0; i--) {
            assertEquals(Color.getByCode(i), bucket.peek());
            bucket = bucket.pop();
        }
        assertBucketIsEmpty(bucket);
    }

    private void assertBucketIsEmpty(Bucket bucket) {
        assertEquals(0, bucket.size());
    }
}
