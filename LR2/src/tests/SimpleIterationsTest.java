package tests;
import functions.SimpleIterations;
import functions.SqrFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class SimpleIterationsTest {
    @Test
    void testApply() {
        SqrFunction squareFunction = new SqrFunction();
        SimpleIterations solver = new SimpleIterations(squareFunction);
        // Проверка результатов
        Assertions.assertEquals(1.0, solver.apply(3.0), 0.001);
        Assertions.assertEquals(1.0, solver.apply(7.0), 0.001);
        Assertions.assertEquals(1.0, solver.apply(9.0), 0.001);
        Assertions.assertEquals(1.0, solver.apply(99.0), 0.001);
        Assertions.assertEquals(1.0, solver.apply(1.0), 0.001);
        Assertions.assertEquals(1.0, solver.apply(0.5), 0.001);
    }
}
