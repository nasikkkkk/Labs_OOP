import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nasikkkkk.Labs_OOP.operations.TabulatedDifferentialOperator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TabulatedDifferentialOperatorTest {
    private final TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator();
    private final ArrayTabulatedFunction arrayTabFunc = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 5);

    // Определяем полиномиальную функцию
    private double polynomialFunction(double x) {
        return Math.pow(x, 3) + 5; // Пример функции
    }

    private final LinkedListTabulatedFunction linkedListTabFunc = new LinkedListTabulatedFunction(arrayTabFunc, -3, 3, 7);

    @Test
    void testDeriveUsingArrayFactory() {
        TabulatedFunction derivedArrayFunc = operator.derive(arrayTabFunc);
        double[] expectedXValues = {0, 2.5, 5, 7.5, 10};
        double[] expectedYValues = {0.6324, 0.2619, 0.2010, 0.1694, 0.1694};

        for (int i = 0; i < derivedArrayFunc.getCount(); i++) {
            assertEquals(expectedXValues[i], derivedArrayFunc.getX(i));
            assertEquals(expectedYValues[i], derivedArrayFunc.getY(i), 0.0001);
        }
    }
}