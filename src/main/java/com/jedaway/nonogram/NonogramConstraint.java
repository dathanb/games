package com.jedaway.nonogram;

import com.jedaway.game.Constraint;
import com.jedaway.game.Logical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jedaway.game.Logical.*;
import static com.jedaway.nonogram.CellState.OFF;

public class NonogramConstraint implements Constraint {
    private int groups[];

    public NonogramConstraint(int... groups) {
        this.groups = groups;
    }

    public Logical isValid(CellState cells[]) {
        // detect whether there are too many cells in total
        int numOnCells = (int)Arrays.stream(cells).filter(CellState::isOn).count();
        int numOffCells = (int)Arrays.stream(cells).filter(CellState::isOff).count();
        int maxOnCells = Arrays.stream(groups).reduce(0, Integer::sum);
        int maxOffCells = cells.length - maxOnCells;

        if (numOnCells > maxOnCells || numOffCells > maxOffCells) {
            return FALSE;
        }

        // TODO: do other, more sophisticated validations on incompletely filled rows

        // for now, just return UNKNOWN if the row/col isn't fully specified
        if (Arrays.stream(cells).filter(CellState::isEmpty).count() > 0) {
            return UNKNOWN;
        }

        // now, a completely filled-in row is valid if and only if the groups of ON cells in it exactly match
        // the groups defined in this constraint
        return groupsMatch(group(cells), groups);
    }

    private static Logical groupsMatch(int groupedCells[], int groups[]) {
        if (groupedCells.length != groups.length) {
            return FALSE;
        }

        for (int i=0; i<groups.length; i++) {
            if (groupedCells[i] != groups[i]) {
                return FALSE;
            }
        }
        return TRUE;
    }

    private static int[] group(CellState cells[]) {
        List<Integer> groups = new ArrayList<>();
        CellState previous = OFF;

        int currentGroup = 0;

        for (int i=0; i<cells.length; i++) {
            if (cells[i] == previous) {
                if (cells[i].isOn()) {
                    currentGroup += 1;
                }
            } else {
                if (cells[i].isOn()) {
                    currentGroup = 1;
                } else if (previous.isOn()) {
                    groups.add(currentGroup);
                    currentGroup = 0;
                }
            }
            previous = cells[i];
        }
        if (currentGroup != 0) {
            groups.add(currentGroup);
        }

        int groupArray[] = new int[groups.size()];
        for (int i=0; i<groups.size(); i++) {
            groupArray[i] = groups.get(i);
        }
        return groupArray;
    }
}
