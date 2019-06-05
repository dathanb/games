package com.jedaway.nonogram;

import com.jedaway.game.Constraint;

import java.util.Arrays;

public class NonogramConstraint implements Constraint {
    private int nums[];

    public NonogramConstraint(int... nums) {
        this.nums = nums;
    }

    public int numOnCellsAllowed() {
        return Arrays.stream(nums).reduce(0, Integer::sum);
    }
}
