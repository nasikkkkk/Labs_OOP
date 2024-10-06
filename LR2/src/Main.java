import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String[] args) {
        TabulatedFunction sinFunction = new TabulatedFunction(-Math.PI, Math.PI, 11);
        for (int i = 0; i < sinFunction.getPointsCount(); i++) {
            double x = sinFunction.getPointX(i);
            double y = Math.sin(x);
            sinFunction.setPointY(i, y);
        }
        System.out.println("Function values:");
        System.out.println("x = -3.14, f(x) = " + sinFunction.getFunctionValue(-3.14)); // Outside the domain
        System.out.println("x = -1.57, f(x) = " + sinFunction.getFunctionValue(-1.57)); // Within an interval
        System.out.println("x = 0.0, f(x) = " + sinFunction.getFunctionValue(0.0));
        System.out.println("x = 1.57, f(x) = " + sinFunction.getFunctionValue(1.57)); // Within an interval
        System.out.println("x = 3.14, f(x) = " + sinFunction.getFunctionValue(3.14)); // Outside the domain
        sinFunction.setPointY(0, 1.0);
        sinFunction.deletePoint(5);
        sinFunction.setPoint(3, new FunctionPoint(10, 0.0));
        System.out.println("\nFunction values after modification:");
        System.out.println("x = -3.14, f(x) = " + sinFunction.getFunctionValue(-3.14));
        System.out.println("x = -1.57, f(x) = " + sinFunction.getFunctionValue(-1.57));
        System.out.println("x = 1.0, f(x) = " + sinFunction.getFunctionValue(1.0));
        System.out.println("x = 1.57, f(x) = " + sinFunction.getFunctionValue(1.57));
        System.out.println("x = 3.14, f(x) = " + sinFunction.getFunctionValue(3.14));
    }
}