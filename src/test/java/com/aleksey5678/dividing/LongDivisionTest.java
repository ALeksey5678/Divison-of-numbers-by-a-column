package com.aleksey5678.dividing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LongDivisionTest {
    private final LongDivision longDivision = new LongDivision();
    private final static String expectedResultOfDividing = "_100│3\n" +
            " 10 │--\n" +
            " -- │33\n" +
            " _10\n" +
            "  10\n" +
            "  --\n" +
            "   1\n";

    @Test
    void shouldReturnFormattedDivisionResultIfValidParameters() {

        assertEquals(expectedResultOfDividing, longDivision.divide(100, 3));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfDivisorIsZero() {
        String message = assertThrows(IllegalArgumentException.class, () -> longDivision.divide(100, 0)).getMessage();
        assertEquals("Divisor can't be 0", message);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhileDividendLessThanDivisor() {
        assertThrows(IllegalArgumentException.class, () -> longDivision.divide(50, 100));
    }

}