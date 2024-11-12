package ru.ssau.tk.nasikkkkk.Labs_OOP.io;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args){
        String arrayFunctionFilePath = "output/array function.bin";
        String linkedListFunctionFilePath = "output/linked list function.bin";
        try (
                BufferedOutputStream arrayFunctionWriter = new BufferedOutputStream(new FileOutputStream(arrayFunctionFilePath));
                BufferedOutputStream linkedListFunctionWriter = new BufferedOutputStream(new FileOutputStream(linkedListFunctionFilePath))
        ) {
            TabulatedFunction arrayFunction = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 5);
            TabulatedFunction linkedListFunction = new LinkedListTabulatedFunction(new double[]{1.123,2.1221,3.99999}, new double[]{1.23, 44, 90});
            FunctionsIO.writeTabulatedFunction(arrayFunctionWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListFunctionWriter, linkedListFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
