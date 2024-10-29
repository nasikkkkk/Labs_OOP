import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory.LinkedListTabulatedFunctionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LinkedListTabulatedFunctionFactoryTest {
    @Test
    void testCreateReturnsLinkedListTabulatedFunction() {
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        var function = factory.create(new double[]{1, 2, 3}, new double[]{1, 4, 9});

        assertTrue(function instanceof LinkedListTabulatedFunction);
    }
}