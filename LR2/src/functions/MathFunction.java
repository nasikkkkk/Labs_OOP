package functions;
public interface MathFunction {
    double apply(double x);
    // Новая фукнция for composite
    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction); // Комбинируем функции
    }
}