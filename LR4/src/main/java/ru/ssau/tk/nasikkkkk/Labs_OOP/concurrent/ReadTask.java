package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;



import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public class ReadTask implements Runnable {
    // Табулированная функция, из которой будут считываться значения
    private final TabulatedFunction tabulatedFunction;

    // Конструктор, принимающий табулированную функцию в качестве параметра
    public ReadTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        // Проходим по всем индексам табулированной функции
        for (int i = 0; i < tabulatedFunction.getCount(); ++i) {
            // Синхронизация доступа к табулированной функции для предотвращения конфликтов при чтении данных
            synchronized (tabulatedFunction) {
                // Получаем значение X по индексу i
                double x = tabulatedFunction.getX(i);
                // Получаем значение Y по индексу i
                double y = tabulatedFunction.getY(i);
                // Выводим считанные значения на консоль
                System.out.printf("After read: i = %d, x = %f, y = %f%n", i, x, y);
            }
        }
    }
}