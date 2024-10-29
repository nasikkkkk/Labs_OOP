package ru.ssau.tk.nasikkkkk.Labs_OOP.exceptions;

public class InconsistentFunctionsException extends RuntimeException{
    public InconsistentFunctionsException(){
        super();
    }
    public InconsistentFunctionsException(String massage){
        super(massage);
    }
}
