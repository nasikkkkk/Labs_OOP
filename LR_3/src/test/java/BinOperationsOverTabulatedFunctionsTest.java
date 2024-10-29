import ru.ssau.tk.nasikkkkk.Labs_OOP.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

public class BinOperationsOverTabulatedFunctionsTest {
    ArrayTabulatedFunction arrayTabFunc = new ArrayTabulatedFunction(new SqrFunction(), 0, 9, 3);

    LinkedListTabulatedFunction linkedListTabFunc = new LinkedListTabulatedFunction(new SqrFunction(), 0, 9, 3);
    TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();



    @Test
    void OperationSub() {
        TabulatedFunction resultOpSub = service.subtract(arrayTabFunc, linkedListTabFunc);
        double[] yValuesSub = {0, 0, 0}; // y = x^2 - x^2
        for (int i = 0; i < resultOpSub.getCount(); ++i) {
            Assertions.assertEquals(yValuesSub[i], resultOpSub.getY(i), 0.0001);
        }
    }
@Test
    void OperationExceptionLengthAreNotTheSame() {
        ArrayTabulatedFunction arrayTabFunc = new ArrayTabulatedFunction(new SqrFunction(), 0, 9, 5);
        LinkedListTabulatedFunction linkedListTabFunc = new LinkedListTabulatedFunction(new SqrFunction(), 0, 9, 3);
        Assertions.assertThrows(InconsistentFunctionsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                TabulatedFunction resultOpSub = service.subtract(arrayTabFunc, linkedListTabFunc);
            }
        });
    }

    @Test
    void OperationExceptionXsAreNotTheSame() {
        ArrayTabulatedFunction arrayTabFunc = new ArrayTabulatedFunction(new SqrFunction(), 0, 90, 3);
        LinkedListTabulatedFunction linkedListTabFunc = new LinkedListTabulatedFunction(new SqrFunction(), 0, 9, 3);
        Assertions.assertThrows(InconsistentFunctionsException.class, new Executable() {
            @Override
            public void execute() throws Throwable {

                TabulatedFunction resultOpSub = service.subtract(arrayTabFunc, linkedListTabFunc);
            }
        });
    }
}
