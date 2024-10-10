package tests;

import functions.IdentityFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdentityFunctionTest {

    @Test
    void apply() {
        IdentityFunction obj = new IdentityFunction();

        // Пример использования функции идентичности
        Assertions.assertEquals(3, obj.apply(3));
        Assertions.assertEquals(5, obj.apply(5));
        Assertions.assertEquals(1, obj.apply(1));
    }
}
