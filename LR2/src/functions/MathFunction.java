package functions;

public interface MathFunction {
    double apply(double x);
    
    // Новая фукнция for composite
    default MathFunction andThen(MathFunction afterFunction) {
        return x -> afterFunction.apply(apply(x)); // Комбинируем функции
    }
}
