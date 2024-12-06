

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;

class SynchronizedTabulatedFunctionTest {
    // Создаем синхронизированную табулированную функцию на основе квадратной функции
    private final SynchronizedTabulatedFunction synchronizedFunction =
            new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 5));

    @Test
    void getCount() {        Assertions.assertEquals(5, synchronizedFunction.getCount());
    }

    @Test
    void getX() {
        Assertions.assertEquals(5, synchronizedFunction.getX(2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> synchronizedFunction.getX(1000))
                ;
    }

    @Test
    void getY() {

        Assertions.assertEquals(2.236, synchronizedFunction.getY(2), 0.001);
        Assertions.assertThrows(IllegalArgumentException.class, () -> synchronizedFunction.getY(1000));
    }

    @Test
    void setY() {
        // Устанавливаем новое значение Y и проверяем его
        synchronizedFunction.setY(3, 3);
        Assertions.assertEquals(3, synchronizedFunction.getY(3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> synchronizedFunction.setY(1000, 8));
    }

    @Test
    void indexOfX() {
        // Проверяем индексы для различных значений X
        Assertions.assertEquals(1, synchronizedFunction.indexOfX(2.5));
        Assertions.assertEquals(2, synchronizedFunction.indexOfX(5));
        Assertions.assertEquals(-1, synchronizedFunction.indexOfX(1000));
    }

    @Test
    void indexOfY() {
        // Проверяем индексы для различных значений Y
        Assertions.assertEquals(0, synchronizedFunction.indexOfY(0));
        Assertions.assertEquals(2, synchronizedFunction.indexOfY(2.23606797749979));

    }

    @Test
    void leftBound() {
        // Проверяем левую границу функции
        Assertions.assertEquals(0, synchronizedFunction.leftBound());
    }

    @Test
    void rightBound() {
        // Проверяем правую границу функции
        Assertions.assertEquals(10, synchronizedFunction.rightBound());
    }

    @Test
    void apply() {
        // Проверяем значения функции при различных входах
        Assertions.assertEquals(-1.897, synchronizedFunction.apply(-3), 0.001, "Неверное значение функции для X = -3.");
        Assertions.assertEquals(3.501, synchronizedFunction.apply(12), 0.001, "Неверное значение функции для X = 12.");
        Assertions.assertEquals(2.236, synchronizedFunction.apply(5), 0.001, "Неверное значение функции для X = 5.");
        Assertions.assertEquals(1.712, synchronizedFunction.apply(3), 0.001, "Неверное значение функции для X = 3.");
    }



    @Test
    void testGetCount() {

        Integer count = synchronizedFunction.doSynchronously(SynchronizedTabulatedFunction::getCount);
        Assertions.assertEquals(5, count);
    }

    @Test
    void testSetY() {
        synchronizedFunction.doSynchronously(func -> {
            func.setY(0, 50);
            return null;
        });

        double updatedValue = synchronizedFunction.doSynchronously(func -> func.getY(0));
        Assertions.assertEquals(50, updatedValue);
    }


}