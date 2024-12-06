package ru.ssau.tk.nasikkkkk.Labs_OOP.concurrent;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;

public class MultiplyingTask implements Runnable {

    private final TabulatedFunction function;

    public MultiplyingTask(TabulatedFunction function){

        this.function = function;
    }

    public void run(){
        for(int i = 0; i < function.getCount();i++){
            synchronized (function) {
                function.setY(i, function.getY(i)*2);
            }
        }
        System.out.println("Поток " + Thread.currentThread().getName() + "завершен");
    }
}