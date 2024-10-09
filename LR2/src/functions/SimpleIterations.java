package functions;
public class SimpleIterations implements MathFunction {
    @Override
    public double apply(double x) {
        double tolerance = 1e-6; // Заданная точность
        double initialGuess = x; // Начальное приближение
        double prevResult = initialGuess;
        double currentResult;
        int maxIterations = 100; // Максимальное число итераций
        int iterations = 0;
        do {
            currentResult = (x + prevResult) / 2;
            prevResult = currentResult;
            iterations++;
        } while (Math.abs(currentResult - prevResult) > tolerance && iterations < maxIterations);
        return currentResult;
    }
}
