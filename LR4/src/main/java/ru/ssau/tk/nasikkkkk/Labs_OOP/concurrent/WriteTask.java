package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public class WriteTask implements Runnable {
    // Табулированная функция, в которую будут записываться значения
    private final TabulatedFunction tabulatedFunction;

    // Значение, которое будет записываться в табулированную функцию
    private final double value;

    // Конструктор, принимающий табулированную функцию и значение для записи
    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.tabulatedFunction = tabulatedFunction;
        this.value = value;
    }

    @Override
    public void run() {
        // Проходим по всем индексам табулированной функции
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            // Синхронизация доступа к табулированной функции для предотвращения конфликтов при записи данных
            synchronized (tabulatedFunction) {
                // Записываем значение в Y по индексу i
                tabulatedFunction.setY(i, value);
                // Выводим сообщение о завершении записи для текущего индекса
                System.out.printf("Writing for index %d complete\n", i);
            }
        }
    }
}