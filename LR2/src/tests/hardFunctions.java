package tests;
import functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
public class hardFunctions {
        @Test
        void testSqrFunction() {
            SqrFunction sqr = new SqrFunction();
            Assertions.assertEquals(Math.sqrt(16), sqr.apply(16), 1e-6);
            Assertions.assertEquals(Math.sqrt(25), sqr.apply(25), 1e-6);
        }
        @Test
        void testSimpleIterations() {
            SqrFunction sqr = new SqrFunction();
            SimpleIterations simpleIterations = new SimpleIterations(sqr, 10);
            Assertions.assertEquals(Math.sqrt(16), simpleIterations.apply(16), 1e-6);
            Assertions.assertEquals(Math.sqrt(25), simpleIterations.apply(25), 1e-6);
        }
        @Test
        void testNewtonMethod() {
            SqrFunction sqr = new SqrFunction();
            MathFunction df = x -> 1.0 / (2 * Math.sqrt(x));
            NewtonMethod newtonMethod = new NewtonMethod(sqr, df);
            Assertions.assertEquals(Math.sqrt(16), newtonMethod.apply(16), 1e-6);
            Assertions.assertEquals(Math.sqrt(25), newtonMethod.apply(25), 1e-6);
        }
}
