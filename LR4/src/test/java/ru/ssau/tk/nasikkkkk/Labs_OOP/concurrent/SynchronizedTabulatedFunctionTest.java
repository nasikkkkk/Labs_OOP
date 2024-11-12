package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import org.junit.jupiter.api.Test;

import ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent.SynchronizedTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.Point;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;
import org.junit.jupiter.api.Assertions;

import java.util.Iterator;
import java.util.NoSuchElementException;
class SynchronizedTabulatedFunctionTest {
    // Создаем синхронизированную табулированную функцию с использованием квадратичной функции
    private final SynchronizedTabulatedFunction syncObj = new SynchronizedTabulatedFunction(new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 5));

    @Test
    void getCount() {
        // Проверяем, что количество точек равно 5
        Assertions.assertEquals(5, syncObj.getCount());
    }

    @Test
    void getX() {
        // Проверяем получение значения X по индексу
        Assertions.assertEquals(5, syncObj.getX(2));
        // Проверяем выброс исключения при неверном индексе
        Assertions.assertThrows(IllegalArgumentException.class, () -> syncObj.getX(1000));
    }

    @Test
    void getY() {
        // Проверяем получение значения Y по индексу с заданной точностью
        Assertions.assertEquals(2.236, syncObj.getY(2), 0.001);
        // Проверяем выброс исключения при неверном индексе
        Assertions.assertThrows(IllegalArgumentException.class, () -> syncObj.getY(1000));
    }

    @Test
    void setY() {
        // Устанавливаем новое значение Y и проверяем его
        syncObj.setY(3, 3);
        Assertions.assertEquals(3, syncObj.getY(3));
        // Проверяем выброс исключения при неверном индексе
        Assertions.assertThrows(IllegalArgumentException.class, () -> syncObj.setY(1000, 8));
    }

    @Test
    void indexOfX() {
        // Проверяем индексы для заданных значений X
        Assertions.assertEquals(1, syncObj.indexOfX(2.5));
        Assertions.assertEquals(2, syncObj.indexOfX(5));
        // Проверка на несуществующее значение X
        Assertions.assertEquals(-1, syncObj.indexOfX(1000));
    }

    @Test
    void indexOfY() {
        // Проверяем индексы для заданных значений Y
        Assertions.assertEquals(0, syncObj.indexOfY(0));
        Assertions.assertEquals(2, syncObj.indexOfY(2.23606797749979));

        // Обновляем значение Y и проверяем индекс обновленного значения
        syncObj.setY(4, 32);
        Assertions.assertEquals(4, syncObj.indexOfY(32));

        // Проверка на несуществующее значение Y
        Assertions.assertEquals(-1, syncObj.indexOfY(1000));
    }

    @Test
    void leftBound() {
        // Проверяем левую границу табулированной функции
        Assertions.assertEquals(0, syncObj.leftBound());
    }

    @Test
    void rightBound() {
        // Проверяем правую границу табулированной функции
        Assertions.assertEquals(10, syncObj.rightBound());
    }

    @Test
    void apply() {
        // Проверяем применение функции к различным значениям с заданной точностью
        Assertions.assertEquals(-1.897, syncObj.apply(-3), 0.001);
        Assertions.assertEquals(3.501, syncObj.apply(12), 0.001);
        Assertions.assertEquals(2.236, syncObj.apply(5), 0.001);
        Assertions.assertEquals(1.712, syncObj.apply(3), 0.001);

        // Добавляем дополнительные проверки на граничные значения
        Assertions.assertEquals(0, syncObj.apply(0), 0.001); // Проверка на нуле
    }

    @Test
    void testIterator() {
        Iterator<Point> iterator = syncObj.iterator();

        int i = 0;

        while (iterator.hasNext()) {
            Point point = iterator.next();
            // Проверка соответствия значений X и Y с индексом i
            Assertions.assertEquals(syncObj.getX(i), point.x);
            Assertions.assertEquals(syncObj.getY(i), point.y);
            ++i;
        }

        // Проверка на выброс исключения при попытке получить следующий элемент после последнего
        Assertions.assertThrows(NoSuchElementException.class, iterator::next);

        // Дополнительная проверка на корректность итератора при пустом массиве точек (если применимо)
    }



    @Test
    void testGetCount() {
        Integer count = syncObj.doSynchronously(SynchronizedTabulatedFunction::getCount);
        // Проверка количества точек через синхронизированный метод
        Assertions.assertEquals(5, count);
    }

    @Test
    void testSetY() {
        // Устанавливаем новое значение через синхронизированный метод и проверяем его
        syncObj.doSynchronously(func -> {
            func.setY(0, 50);
            return null;
        });

        double newY = syncObj.doSynchronously(func -> func.getY(0));
        Assertions.assertEquals(50, newY);
    }

    @Test
    void testGetYAfterUpdate() {
        // Обновляем значение Y через синхронизированный метод и проверяем его получение
        syncObj.doSynchronously(func -> {
            func.setY(1, 100);
            return null;
        });

        double result = syncObj.doSynchronously(func -> func.getY(1));
        Assertions.assertEquals(100, result);
    }
}