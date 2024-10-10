package tests;

import functions.MathFunction;
import functions.NewtonMethod;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NewtonMethodTest {

    @Test
    void test1() {
        MathFunction f = x -> 2 * x - 3;
        MathFunction df = x -> 2;
        NewtonMethod newton = new NewtonMethod(f, df);
        double root = newton.apply(5.0);
        Assertions.assertEquals(1.5, root, 0.000001);
    }

    @Test
    void test2() {
        MathFunction f = x -> x * x - 4 * x + 3;
        MathFunction df = x -> 2 * x - 4;
        NewtonMethod newton = new NewtonMethod(f, df);
        double root = newton.apply(5.0);
        Assertions.assertEquals(3, root, 0.000001);
    }

    @Test
    void test3() {
        MathFunction f = Math::sqrt;
        MathFunction df = x -> 1/(2*Math.sqrt(x));
        NewtonMethod newton = new NewtonMethod(f, df);
        double root = newton.apply(5.0);
        Assertions.assertEquals(-5, root, 0.000001);
    }
}