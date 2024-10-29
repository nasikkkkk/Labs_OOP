package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;
import java.lang.Math;
public class SqrFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return Math.sqrt(x);
    }
}
