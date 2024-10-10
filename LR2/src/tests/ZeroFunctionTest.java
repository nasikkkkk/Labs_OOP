package tests;

import functions.ZeroFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ZeroFunctionTest {

    @Test
    void apply() {
        ZeroFunction test = new ZeroFunction();
        Assertions.assertEquals(0, test.apply(9));
        Assertions.assertEquals(0, test.apply(134));
        Assertions.assertEquals(0, test.apply(22));
        Assertions.assertEquals(0, test.apply(55));
    }
}