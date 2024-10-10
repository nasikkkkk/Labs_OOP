package functions;
public class SimpleIterations implements MathFunction {
    private final MathFunction func; // Поле для хранения функции
    // Конструктор, принимающий реализацию MathFunction
    public SimpleIterations(MathFunction func) {
        this.func = func;
    }
    @Override
    public double apply(double x) {
        double tolerance = 1e-6; // Заданная точность
        double currentResult = x; // Начальное приближение
        int maxIterations = 100; // Максимальное число итераций
        int iterations = 0;
        do {
            currentResult = func.apply(currentResult); // Используем переданную функцию с текущим результатом
            iterations++;
        } while (Math.abs(func.apply(currentResult) - currentResult) > tolerance && iterations < maxIterations);
        return currentResult;
    }
}
