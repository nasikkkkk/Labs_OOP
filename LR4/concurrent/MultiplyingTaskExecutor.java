package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.UnitFunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MultiplyingTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction tabulatedFunction = new LinkedListTabulatedFunction(new UnitFunction(), 1, 1000, 100);

        // Список для хранения потоков
        List<Thread> threads = new ArrayList<>();

        // задачи и потоки для их выполнения
        for (int i = 0; i < 10; i++) {
            MultiplyingTask task = new MultiplyingTask(tabulatedFunction);
            Thread thread = new Thread(task);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Вывод:");
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            System.out.println("x: " + tabulatedFunction.getX(i) + ", y: " + tabulatedFunction.getY(i));
        }
    }
}