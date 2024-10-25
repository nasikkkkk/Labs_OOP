package tests;

import functions.UnitFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnitFunctionTest {

    @Test
    void apply() {
        UnitFunction test = new UnitFunction();
        Assertions.assertEquals(1, test.apply(234));
        Assertions.assertEquals(1, test.apply(3));
        Assertions.assertEquals(1, test.apply(5));
    }
}