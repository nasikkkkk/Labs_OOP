package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.Point;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.operations.TabulatedFunctionOperationService;

import java.util.Iterator;
import java.util.NoSuchElementException;

// Класс, обеспечивающий потокобезопасный доступ к табулированной функции
public class SynchronizedTabulatedFunction implements TabulatedFunction {
    // Храним ссылку на исходную табулированную функцию
    private final TabulatedFunction function;

    // Интерфейс для определения операций, которые могут быть выполнены над синхронизированной табулированной функцией
    public interface Operation<T> {
        T apply(SynchronizedTabulatedFunction function);
    }

    // Конструктор, принимающий табулированную функцию
    public SynchronizedTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }

    // Получаем количество точек в табулированной функции с синхронизацией
    @Override
    public synchronized int getCount() {
        return function.getCount();
    }

    // Получаем значение X по индексу с синхронизацией
    @Override
    public synchronized double getX(int index) {
        return function.getX(index);
    }

    // Получаем значение Y по индексу с синхронизацией
    @Override
    public synchronized double getY(int index) {
        return function.getY(index);
    }

    // Устанавливаем значение Y по индексу с синхронизацией
    @Override
    public synchronized void setY(int index, double value) {
        function.setY(index, value);
    }

    // Находим индекс по значению X с синхронизацией
    @Override
    public synchronized int indexOfX(double x) {
        return function.indexOfX(x);
    }

    // Находим индекс по значению Y с синхронизацией
    @Override
    public synchronized int indexOfY(double y) {
        return function.indexOfY(y);
    }

    // Получаем левую границу табулированной функции с синхронизацией
    @Override
    public synchronized double leftBound() {
        return function.leftBound();
    }

    // Получаем правую границу табулированной функции с синхронизацией
    @Override
    public synchronized double rightBound() {
        return function.rightBound();
    }

    // Применяем функцию к значению X с синхронизацией
    @Override
    public synchronized double apply(double x) {
        return function.apply(x);
    }

    // Создаем итератор для перебора точек табулированной функции с синхронизацией
    @Override
    public synchronized Iterator<Point> iterator() {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        return new Iterator<Point>() {
            private int index = 0; // Индекс текущей точки

            // Проверяем, есть ли следующая точка
            @Override
            public boolean hasNext() {
                return index < points.length;
            }

            // Получаем следующую точку, если она существует
            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(); // Выбрасываем исключение, если следующей точки нет
                }
                return points[index++];
            }
        };
    }

    // Метод для выполнения операций над синхронизированной функцией
    public synchronized <T> T doSynchronously(Operation<T> operation) {
        return operation.apply(this); // Применяем операцию и возвращаем результат
    }
}