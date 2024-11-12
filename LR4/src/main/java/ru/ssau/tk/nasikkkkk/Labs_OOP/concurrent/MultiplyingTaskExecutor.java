package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.UnitFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        // Создаем экземпляр табулированной функции с использованием UnitFunction
        // и задаем начальные значения, конечные значения и количество точек
        LinkedListTabulatedFunction tabulatedFunction =
                new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 1000);

        // Списки для хранения задач и потоков
        List<MultiplyingTask> tasks = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        // Запускаем 10 задач умножения в отдельных потоках
        for (int i = 0; i < 10; i++) {
            // Создаем новую задачу умножения с общей табулированной функцией
            MultiplyingTask task = new MultiplyingTask(tabulatedFunction);
            // Создаем поток для выполнения задачи
            Thread thread = new Thread(task);

            // Добавляем задачу и поток в соответствующие списки
            tasks.add(task);
            threads.add(thread);

            // Запускаем поток
            thread.start();
        }

        // Ожидаем завершения всех задач
        while (!tasks.isEmpty()) {
            Iterator<MultiplyingTask> iterator = tasks.iterator();
            while (iterator.hasNext()) {
                MultiplyingTask task = iterator.next();
                // Проверяем, завершена ли задача
                if (task.isCompleted()) {
                    // Удаляем завершенную задачу из списка
                    iterator.remove();
                }
            }
        }

        // Выводим результат табулированной функции после выполнения всех задач
        System.out.println(tabulatedFunction);
    }
}