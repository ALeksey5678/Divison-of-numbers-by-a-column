package com.aleksey5678.dividing;

public class Positions {
    private static final int POSITION_OF_DIVISOR = 0;
    private static final int POSITION_OF_LINE_SPLITTER = 1;
    private static final int POSITION_OF_QUOTIENT = 2;
    private final int positionOfDivisor;
    private final int positionOfLineSplitter;
    private final int positionOfQuotient;

    public int getPositionOfDivisor() {
        return positionOfDivisor;
    }

    public int getPositionOfLineSplitter() {
        return positionOfLineSplitter;
    }

    public int getPositionOfQuotient() {
        return positionOfQuotient;
    }

    public Positions(int positionOfDivisor, int positionOfLineSplitter, int positionOfQuotient) {
        this.positionOfDivisor = positionOfDivisor;
        this.positionOfLineSplitter = positionOfLineSplitter;
        this.positionOfQuotient = positionOfQuotient;
    }

    public static Positions getPositions(String result) {
        int[] index = new int[3];
        for (int indexOfDigitInArray = 0, numberOfDigitInArray = 0;
             indexOfDigitInArray < result.length();
             indexOfDigitInArray++) {
            if (result.charAt(indexOfDigitInArray) == LongDivision.LINE_FEED) {
                index[numberOfDigitInArray] = indexOfDigitInArray;
                numberOfDigitInArray++;
            }
            if (numberOfDigitInArray == 3) {
                break;
            }
        }
        return new Positions(
                index[POSITION_OF_DIVISOR],
                index[POSITION_OF_LINE_SPLITTER],
                index[POSITION_OF_QUOTIENT]);
    }
}

