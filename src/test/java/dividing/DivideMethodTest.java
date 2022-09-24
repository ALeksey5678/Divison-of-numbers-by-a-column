package dividing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DivideMethodTest {

    @Test
    void globalProgramTest() {
        DivideMethod divide = new DivideMethod();
        final String GlobalResultOFDividing = "_100│3\n" +
                " 10 │--\n" +
                " -- │33\n" +
                " _10\n" +
                "  10\n" +
                "  --\n" +
                "   1\n";
        assertEquals(GlobalResultOFDividing, divide.division(100, 3));
    }


}