package tests;

import functions.SqrFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SqrFunctionTest {

    @Test
    void apply() {
        SqrFunction obj = new SqrFunction();
        Assertions.assertEquals(3, obj.apply(9)); // Квадратный корень из 9 равен 3
        Assertions.assertEquals(0, obj.apply(0)); // Квадратный корень из 0 равен 0
        Assertions.assertEquals(4, obj.apply(16)); // Квадратный корень из 16 равен 4
    }}