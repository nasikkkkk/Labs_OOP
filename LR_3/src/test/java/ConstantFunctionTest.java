import org.junit.jupiter.api.Test;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ConstantFunction;
import org.junit.jupiter.api.Assertions;
//
class ConstantFunctionTest {
    private final ConstantFunction obj = new ConstantFunction(56);
    @Test
    void getConstant() {
        Assertions.assertEquals(56, obj.getConstant());
    }

    @Test
    void apply() {
        Assertions.assertEquals(56, obj.apply(1));
        Assertions.assertEquals(56, obj.apply(7));
        Assertions.assertEquals(56, obj.apply(4444));

    }
}