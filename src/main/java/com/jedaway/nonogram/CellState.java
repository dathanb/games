package com.jedaway.nonogram;

public enum CellState {
    ON,
    OFF,
    EMPTY;

    public boolean isOn() {
        return this == ON;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isOff() {
        return this == OFF;
    }
}
