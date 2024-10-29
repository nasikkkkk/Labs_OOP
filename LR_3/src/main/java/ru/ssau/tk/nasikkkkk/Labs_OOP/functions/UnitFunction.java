package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;

public class UnitFunction extends ConstantFunction{
    public UnitFunction(){
        super(1);
    }
    @Override
    public double apply(double x){
        return getConstant();
    }
}
