package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;


import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {
    // Табулированная функция, с которой будет работать задача
    private final TabulatedFunction tabulatedFunction;

    // Статус выполнения задачи, помеченный как volatile для обеспечения корректного чтения из разных потоков
    private volatile boolean completed = false;

    // Конструктор, принимающий табулированную функцию в качестве параметра
    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        // Синхронизация доступа к табулированной функции для предотвращения конфликтов при изменении данных
        synchronized (tabulatedFunction) {
            // Проходим по всем значениям табулированной функции
            for (int i = 0; i < tabulatedFunction.getCount(); i++) {
                // Умножаем каждое значение Y на 2
                double newValue = tabulatedFunction.getY(i) * 2;
                // Обновляем значение Y в табулированной функции
                tabulatedFunction.setY(i, newValue);
            }
            // Устанавливаем статус выполнения задачи как завершенный
            completed = true;
            // Выводим сообщение о завершении задачи и имя текущего потока
            System.out.println(Thread.currentThread().getName() + " completed task");
        }
    }

    // Метод для проверки статуса выполнения задачи
    public boolean isCompleted() {
        return completed;
    }
}