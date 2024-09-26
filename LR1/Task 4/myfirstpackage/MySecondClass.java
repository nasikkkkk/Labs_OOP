package myfirstpackage;
public class MySecondClass {
    private int X;   // 1 приватное поле
    private int Y;  // 2 приватное поле

    // для получения значения первого числа
    public int getX() {
        return X;
    }

    //для установки значения первого числа
    public void setX(int X) {
        this.X = X;
    }
    //  для получения значения второго числа
    public int getY() {
        return Y;
    }

    //для установки значения второго числа
    public void setY(int Y) {
        this.Y = Y;
    }

    // конструктор, создающий объект и инициализирующий значения полей
    public MySecondClass(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    // метод, реализующий действие над числами (например, сложение)
    public int getSum() {
        return X +Y;
    }
}