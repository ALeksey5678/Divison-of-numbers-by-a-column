package com.aleksey5678.dividing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongDivisionTest {
    private final LongDivision divide = new LongDivision();

    @Test
    void globalProgramTest() {

        String globalResultOFDividing = "_100│3\n" +
                " 10 │--\n" +
                " -- │33\n" +
                " _10\n" +
                "  10\n" +
                "  --\n" +
                "   1\n";
        assertEquals(globalResultOFDividing, divide.division(100, 3));
    }

    @Test
    void shouldTrowIllegalArgumentExceptionIfDivisorIsZero() {
        String message = assertThrows(IllegalArgumentException.class, () -> divide.division(100, 0)).getMessage();
        assertEquals("Divisor can't be 0", message);
    }

    @Test
    void shouldTrowIllegalArgumentExceptionWhileDividendLessThanDivisor() {
        assertThrows(IllegalArgumentException.class, () -> divide.division(50, 100));
    }

}