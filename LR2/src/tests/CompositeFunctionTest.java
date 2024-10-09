package tests;
import functions.SimpleIterations;
import functions.IdentityFunction;
import functions.CompositeFunction;
import functions.SqrFunction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class CompositeFunctionTest {
    @Test
    void apply() {
        SimpleIterations fun_1 = new SimpleIterations (new SqrFunction());
        IdentityFunction fun_2 = new IdentityFunction();
        CompositeFunction fun_3 = new CompositeFunction(fun_1, fun_2);
        Assertions.assertEquals(fun_3.apply(3), fun_3.apply(3));
        Assertions.assertEquals(fun_3.apply(5), fun_3.apply(5));
        Assertions.assertEquals(fun_3.apply(6), fun_3.apply(6));
        Assertions.assertEquals(fun_3.apply(9), fun_3.apply(9));
    }
}