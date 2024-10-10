package tests;
import functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class hardFunctions {
        @Test
        void testSimpleIterations() {
            SqrFunction sqr = new SqrFunction();
            SimpleIterations simpleIterations = new SimpleIterations(sqr);
            Assertions.assertEquals(1.000001, simpleIterations.andThen(sqr).apply(16), 1e-6);
            Assertions.assertEquals(1.000001, simpleIterations.andThen(sqr).apply(25), 1e-6);
        }
        @Test
        void testNewtonMethod() {
            SqrFunction sqr = new SqrFunction();
            SimpleIterations simple = new SimpleIterations(sqr);
            MathFunction df = x -> 1.0 / (2 * Math.sqrt(x));
            NewtonMethod newtonMethod = new NewtonMethod(sqr, df);
            Assertions.assertEquals(Math.sqrt(16), newtonMethod.andThen(simple).apply(16), 1e-6);
            Assertions.assertEquals(Math.sqrt(25), newtonMethod.andThen(simple).apply(25), 1e-6);
        }
}
