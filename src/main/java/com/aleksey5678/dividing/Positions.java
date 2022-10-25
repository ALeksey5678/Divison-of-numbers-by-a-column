package com.aleksey5678.dividing;

public class Positions {
    private final int positionOfDivisor;
    private final int positionOfLineSpliter;
    private final int positionOfQuotient;

    public int getPositionOfDivisor() {
        return positionOfDivisor;
    }

    public int getPositionOfLineSplitter() {
        return positionOfLineSpliter;
    }

    public int getPositionOfQuotient() {
        return positionOfQuotient;
    }

    public Positions(int positionOfDivisor, int positionOfLineSpliter, int positionOfQuotient) {
        this.positionOfDivisor = positionOfDivisor;
        this.positionOfLineSpliter = positionOfLineSpliter;
        this.positionOfQuotient = positionOfQuotient;
    }
}

