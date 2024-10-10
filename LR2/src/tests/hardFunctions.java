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

        @Test
        void testArr(){
            SqrFunction sqr = new SqrFunction();
            SimpleIterations simpleIterations = new SimpleIterations(sqr);
            ArrayTabulatedFunction expected = new ArrayTabulatedFunction(simpleIterations.andThen(sqr), 0, 10, 5);
            ArrayTabulatedFunction actual = new ArrayTabulatedFunction(new double[]{0, 2.5, 5, 7.5, 10}, new double[]{simpleIterations.andThen(sqr).apply(0), simpleIterations.andThen(sqr).apply(2.5), simpleIterations.andThen(sqr).apply(5), simpleIterations.andThen(sqr).apply(7.5), simpleIterations.andThen(sqr).apply(10)});
            Assertions.assertEquals(expected.getX(0), actual.getX(0));
            Assertions.assertEquals(expected.getX(1), actual.getX(1));
            Assertions.assertEquals(expected.getX(2), actual.getX(2));
            Assertions.assertEquals(expected.getX(3), actual.getX(3));
            Assertions.assertEquals(expected.getX(4), actual.getX(4));

            Assertions.assertEquals(expected.getY(0), actual.getY(0));
            Assertions.assertEquals(expected.getY(1), actual.getY(1));
            Assertions.assertEquals(expected.getY(2), actual.getY(2));
            Assertions.assertEquals(expected.getY(3), actual.getY(3));
            Assertions.assertEquals(expected.getY(4), actual.getY(4));
        }
    @Test
    void testLinked(){
        SqrFunction sqr = new SqrFunction();
        SimpleIterations simpleIterations = new SimpleIterations(sqr);
        LinkedListTabulatedFunction expected = new LinkedListTabulatedFunction(simpleIterations.andThen(sqr), 0, 10, 5);
        LinkedListTabulatedFunction actual = new LinkedListTabulatedFunction(new double[]{0, 2.5, 5, 7.5, 10}, new double[]{simpleIterations.andThen(sqr).apply(0), simpleIterations.andThen(sqr).apply(2.5), simpleIterations.andThen(sqr).apply(5), simpleIterations.andThen(sqr).apply(7.5), simpleIterations.andThen(sqr).apply(10)});
        Assertions.assertEquals(expected.getX(0), actual.getX(0));
        Assertions.assertEquals(expected.getX(1), actual.getX(1));
        Assertions.assertEquals(expected.getX(2), actual.getX(2));
        Assertions.assertEquals(expected.getX(3), actual.getX(3));
        Assertions.assertEquals(expected.getX(4), actual.getX(4));

        Assertions.assertEquals(expected.getY(0), actual.getY(0));
        Assertions.assertEquals(expected.getY(1), actual.getY(1));
        Assertions.assertEquals(expected.getY(2), actual.getY(2));
        Assertions.assertEquals(expected.getY(3), actual.getY(3));
        Assertions.assertEquals(expected.getY(4), actual.getY(4));
    }
}
