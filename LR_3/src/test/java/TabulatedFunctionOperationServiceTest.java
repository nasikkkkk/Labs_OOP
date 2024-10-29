import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.ArrayTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.Point;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.SqrFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.operations.TabulatedFunctionOperationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TabulatedFunctionOperationServiceTest {

    @Test
    void asPointsArrayTest() {
        ArrayTabulatedFunction obj = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 5);
        Point[] points = TabulatedFunctionOperationService.asPoints(obj);
        Assertions.assertEquals(points.length, obj.getCount());
        for (int i = 0; i < points.length; ++i) {
            Assertions.assertEquals(points[i].x, obj.getX(i));
            Assertions.assertEquals(points[i].y, obj.getY(i));
        }
    }
    @Test
    void asPointsLinkedTest() {
        LinkedListTabulatedFunction obj = new LinkedListTabulatedFunction(new SqrFunction(), 0, 10, 5);
        Point[] points = TabulatedFunctionOperationService.asPoints(obj);
        Assertions.assertEquals(points.length, obj.getCount());
        for (int i = 0; i < points.length; ++i) {
            Assertions.assertEquals(points[i].x, obj.getX(i));
            Assertions.assertEquals(points[i].y, obj.getY(i));
        }
    }
}