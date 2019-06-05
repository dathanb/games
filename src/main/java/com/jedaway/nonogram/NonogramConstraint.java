package com.jedaway.nonogram;

import com.jedaway.game.Constraint;
import com.jedaway.game.Logical;

import java.util.Arrays;

import static com.jedaway.game.Logical.FALSE;
import static com.jedaway.game.Logical.UNKNOWN;

public class NonogramConstraint implements Constraint {
    private int groups[];

    public NonogramConstraint(int... groups) {
        this.groups = groups;
    }

    public Logical isValid(NonogramPosition.CellState nums[]) {
        int numOnCells = (int)Arrays.stream(nums).filter(NonogramPosition.CellState::isOn).count();
        int numOffCells = (int)Arrays.stream(nums).filter(NonogramPosition.CellState::isOff).count();
        int maxOnCells = Arrays.stream(groups).reduce(0, Integer::sum);
        int maxOffCells = nums.length - maxOnCells;

        if (numOnCells > maxOnCells || numOffCells > maxOffCells) {
            return FALSE;
        }

        return UNKNOWN;
    }

    public int numOnCellsAllowed() {
        return Arrays.stream(groups).reduce(0, Integer::sum);
    }
}
