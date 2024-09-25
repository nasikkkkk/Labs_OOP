class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass o = new MySecondClass(0, 0);  // создание и инициализация объекта “o”
        System.out.println(o.getSum());

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setX(i);  //первое числовое поле
                o.setY(j); //второе
                System.out.print(o.getSum());  // метод для суммы чисел
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
class MySecondClass {
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