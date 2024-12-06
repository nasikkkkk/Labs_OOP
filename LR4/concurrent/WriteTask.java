package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public class WriteTask implements Runnable {
    private final TabulatedFunction Function;
    private final double value;

    public WriteTask(TabulatedFunction tabulatedFunction, double value) {
        this.Function = tabulatedFunction;
        this.value = value;
    }

    @Override
    public void run() {
        for (int i = 0; i < Function.getCount(); i++) {
            synchronized (Function) {
                Function.setY(i, value);
                System.out.printf("Writing for index %d complete%n", i);
            }
        }
    }
}