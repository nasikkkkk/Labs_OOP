package ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory;


import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory{
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }
}
