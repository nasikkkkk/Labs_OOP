import myfirstpackage.MySecondClass;
class MyFirstClass {
    public static void main(String[] args) {
        MySecondClass o = new MySecondClass(10, 100);  
        System.out.println(o.getSum());

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                o.setX(i); 
                o.setY(j);
                System.out.print(o.getSum()); 
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}