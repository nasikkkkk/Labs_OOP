package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ConstantFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        // Создаем экземпляр табулированной функции с использованием ConstantFunction
        // и задаем начальные значения, конечные значения и количество точек
        LinkedListTabulatedFunction list = new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        // Создаем поток для выполнения задачи чтения из табулированной функции
        Thread readStream = new Thread(new ReadTask(list));

        // Создаем поток для выполнения задачи записи в табулированную функцию
        // Записываем значение 0.5 в табулированную функцию
        Thread writeStream = new Thread(new WriteTask(list, 0.5));

        // Запускаем поток чтения
        readStream.start();

        // Запускаем поток записи
        writeStream.start();
    }
}