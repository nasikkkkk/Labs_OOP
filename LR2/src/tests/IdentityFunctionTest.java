package tests;

import functions.IdentityFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentityFunctionTest {

    @Test
    void apply() {
        IdentityFunction obj = new IdentityFunction();
        double x = 10;
        Assertions.assertEquals(x, obj.apply(x));
    }
}