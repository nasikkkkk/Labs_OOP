<<<<<<< HEAD
package functions;

public interface MathFunction {
    double apply(double x);
    
    // Новая фукнция for composite
    default MathFunction andThen(MathFunction afterFunction) {
        return x -> afterFunction.apply(apply(x)); // Комбинируем функции
    }
}
=======
package functions;

public interface MathFunction {
    double apply(double x);
    
    // Новая фукнция for composite
    default MathFunction andThen(MathFunction afterFunction) {
        return x -> afterFunction.apply(apply(x)); // Комбинируем функции
    }
}
>>>>>>> 4fe543a0c1767e47e0c43da207535ff7652111da
