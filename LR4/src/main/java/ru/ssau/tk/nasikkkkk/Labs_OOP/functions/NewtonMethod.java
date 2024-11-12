package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;



public class NewtonMethod implements MathFunction {
    // Приватные поля для функции и ее производной
    private final MathFunction function;     // f(x)
    private final MathFunction derivative;   // f'(x)

    // Конструктор, принимающий два объекта типа MathFunction
    public NewtonMethod(MathFunction function, MathFunction derivative) {
        this.function = function;
        this.derivative = derivative;
    }

    // Публичный метод для нахождения корня уравнения методом Ньютона
    @Override
    public double apply(double x) {
        return findRoot(x);
    }

    // Приватный метод для выполнения одной итерации метода Ньютона
    private double iterate(double x) {
        return x - function.apply(x) / derivative.apply(x);
    }

    // Приватный метод для выполнения полной процедуры поиска корня
    private double findRoot(double x) {
        final double tolerance = 1e-6; // Точность вычислений
        final int maxIterations = 1000; // Максимальное количество итераций
        int iterations = 0;
        while (Math.abs(function.apply(x)) > tolerance && iterations < maxIterations) {
            x = iterate(x);
            iterations++;
        }
        if (iterations == maxIterations) {
            throw new RuntimeException("Достигнуто максимальное количество итераций");
        }
        return x;
    }
}
