package ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);



}
