import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//
class CompositeFunctionTest {

    @Test
    void testApply3() {
        IdentityFunction dObj = new IdentityFunction();
        SqrFunction pObj = new SqrFunction();

        IdentityFunction gObj = new IdentityFunction();
        CompositeFunction fObj = new CompositeFunction(dObj, pObj);

        CompositeFunction mainObj = new CompositeFunction(gObj, fObj);

        Assertions.assertEquals(Math.pow(Math.sqrt(10) , 1), mainObj.apply(10));
        Assertions.assertEquals(Math.pow(Math.sqrt(30) , 1), mainObj.apply(30));
        Assertions.assertEquals(Math.pow(Math.sqrt(100) , 1), mainObj.apply(100));
    }
}