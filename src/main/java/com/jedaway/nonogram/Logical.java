package com.jedaway.nonogram;

public enum Logical {
    TRUE,
    FALSE,
    UNKNOWN;

    public Logical or(Logical other) {
        if (this == TRUE || other == TRUE) {
            return TRUE;
        }
        if (this == UNKNOWN || other == UNKNOWN) {
            return UNKNOWN;
        }
        return FALSE;
    }

    public Logical and(Logical other) {
        if (this == TRUE && other == TRUE) {
            return TRUE;
        }
        if (this == FALSE || other == FALSE) {
            return FALSE;
        }
        return UNKNOWN;
    }
}
