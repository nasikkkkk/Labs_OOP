package functions;
public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.sqrt(x); // Вычисляем квадрат x с помощью Math.pow
    }
}