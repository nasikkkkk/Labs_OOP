import myfirstpackage.*;
class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass o = new MySecondClass(10, 100);  // создание и инициализация объекта “o”
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