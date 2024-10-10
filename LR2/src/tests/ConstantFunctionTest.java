package tests;

import functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConstantFunctionTest {

    @Test
    void apply() {
        ConstantFunction fun  = new ConstantFunction(1);
        Assertions.assertEquals(1, fun.apply(10));
        Assertions.assertEquals(1, fun.apply(100));
        Assertions.assertEquals(1, fun.apply(88));
        Assertions.assertEquals(1, fun.apply(-8));
        Assertions.assertEquals(1, fun.apply(5));
    }
}