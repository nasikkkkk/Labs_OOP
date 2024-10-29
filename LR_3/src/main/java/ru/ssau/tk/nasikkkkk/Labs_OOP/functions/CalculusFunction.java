package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;

public interface CalculusFunction extends MathFunction {
    double calculateDerivative(double x);
    double calculateIntegral(double x0, double x);

    @Override
    double apply(double x);
}
