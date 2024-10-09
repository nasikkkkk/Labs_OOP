package functions;
public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, 2); // Вычисляем квадрат x с помощью Math.pow
    }
}