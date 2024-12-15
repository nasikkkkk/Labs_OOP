package ru.ssau.tk.nasikkkkk.Labs_OOP.io;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.nasikkkkk.Labs_OOP.operations.TabulatedDifferentialOperator;

import java.io.*;


public class LinkedListTabulatedFunctionSerialization {
    public static void main(String[] args) {

        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("output/linked_list_function.bin"))) {
            double[] xValues = {1, 2, 3, 4, 5};
            double[] yValues = {1, 4, 9, 16, 25};

            TabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
            TabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
            TabulatedDifferentialOperator operator = new TabulatedDifferentialOperator(factory);

            TabulatedFunction Derive1 = operator.derive(function);
            TabulatedFunction Derive2 = operator.derive(Derive1);

            FunctionsIO.serialize(out, function);
            FunctionsIO.serialize(out, Derive1);
            FunctionsIO.serialize(out, Derive2);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("output/linked_list_function.bin"))) {
            System.out.println("Функция - " + FunctionsIO.deserialize(in));
            System.out.println("Первая производная - " + FunctionsIO.deserialize(in));
            System.out.println("Вторая производная - " + FunctionsIO.deserialize(in));
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
