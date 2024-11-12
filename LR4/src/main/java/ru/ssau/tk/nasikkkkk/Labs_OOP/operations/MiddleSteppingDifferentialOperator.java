package ru.ssau.tk.nasikkkkk.Labs_OOP.operations;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.MathFunction;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        // Возвращаем анонимный класс, реализующий MathFunction
        return new MathFunction() {
            @Override
            public double apply(double x) {
                // Используем центральную разность для вычисления производной
                double forwardValue = function.apply(x + step);
                double backwardValue = function.apply(x - step);
                return (forwardValue - backwardValue) / (2 * step);
            }
        };
    }
}