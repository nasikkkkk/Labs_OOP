package ru.ssau.tk.nasikkkkk.Labs_OOP.io;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.operations.TabulatedDifferentialOperator;

import java.io.*;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {
        String path = "output/serialized_linked_list_functions.bin";

        // Создание исходной функции с использованием простого массива значений
        double[] xValues = {0, 2.5, 5, 7.5, 10};
        double[] yValues = {0, 6.25, 25, 56.25, 100}; // Пример значений для функции y = x^2

        LinkedListTabulatedFunction originalFunction = new LinkedListTabulatedFunction(xValues, yValues);
        TabulatedFunction firstDerivative = new TabulatedDifferentialOperator().derive(originalFunction);
        TabulatedFunction secondDerivative = new TabulatedDifferentialOperator().derive(firstDerivative);

        try (FileOutputStream fileOutputStream = new FileOutputStream(path);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            FunctionsIO.serialize(bufferedOutputStream, originalFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(path);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream)) {
            TabulatedFunction deserializedOriginalFunction = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println(deserializedOriginalFunction.toString());
            System.out.println(deserializedFirstDerivative.toString());
            System.out.println(deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}