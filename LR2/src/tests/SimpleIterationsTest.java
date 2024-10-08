package tests;

import functions.SimpleIterations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SimpleIterationsTest {

    @Test
    void apply() {
        SimpleIterations obj = new SimpleIterations();
        Assertions.assertEquals(1, obj.apply(3));
        Assertions.assertEquals(1, obj.apply(7));
        Assertions.assertEquals(1, obj.apply(9));
        Assertions.assertEquals(1, obj.apply(99));
    }
}