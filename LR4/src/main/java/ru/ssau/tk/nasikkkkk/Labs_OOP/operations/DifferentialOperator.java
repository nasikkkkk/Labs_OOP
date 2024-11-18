package ru.ssau.tk.nasikkkkk.Labs_OOP.operations;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
