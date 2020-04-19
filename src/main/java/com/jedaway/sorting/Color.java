package com.jedaway.sorting;

public enum Color {
    NONE(0, false),
    RED(1, true),
    PINK(2, true),
    ORANGE(3, true),
    YELLOW(4, true),
    GREEN(5, true),
    BLUE(6, true),
    CYAN(7, true),
    INDIGO(8, true),
    VIOLET(9, true),
    PURPLE(10, true),
    WHITE(11, true),
    BLACK(12, true),
    GRAY(13, true),
    BROWN(14, true),
    MAGENTA(15, true);

    private final int code;
    private final boolean isReal;

    Color(int code, boolean isReal) {
        this.code = code;
        this.isReal = isReal;
    }

    public static Color getByCode(int code) {
        switch (code) {
            case 0:
                return NONE;
            case 1:
                return RED;
            case 2:
                return PINK;
            case 3:
                return ORANGE;
            case 4:
                return YELLOW;
            case 5:
                return GREEN;
            case 6:
                return BLUE;
            case 7:
                return CYAN;
            case 8:
                return INDIGO;
            case 9:
                return VIOLET;
            case 10:
                return PURPLE;
            case 11:
                return WHITE;
            case 12:
                return BLACK;
            case 13:
                return GRAY;
            case 14:
                return BROWN;
            case 15:
                return MAGENTA;
            default:
                throw new RuntimeException(String.format("Code %d does not correspond to a color", code));
        }
    }
}
