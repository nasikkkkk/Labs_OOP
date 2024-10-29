package ru.ssau.tk.nasikkkkk.Labs_OOP.io;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args){
        // Шаг 1: Определяем пути к файлам
        String arrayFunctionFilePath = "output/array function.txt";
        String linkedListFunctionFilePath = "output/linked list function.txt";
        // Шаг 2: Используем try-with-resources для создания потоков
        try (
                BufferedWriter arrayFunctionWriter = new BufferedWriter(new FileWriter(arrayFunctionFilePath));
                BufferedWriter linkedListFunctionWriter = new BufferedWriter(new FileWriter(linkedListFunctionFilePath))
        ) {
            // Шаг 3: Создаем табулированные функции
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 5);
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{1,2,3}, new double[]{1, 4, 9});
            // Шаг 4: Записываем функции в соответствующие файлы
            FunctionsIO.writeTabulatedFunction(arrayFunctionWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListFunctionWriter, linkedListFunction);
        } catch (IOException e) {
            // Шаг 5: Обработка исключения
            e.printStackTrace(); // Выводим стек-трейс в стандартный поток ошибок
        }
    }
}
