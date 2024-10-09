package tests;
import functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class LinkedListTabulatedFunctionTest {
    private LinkedListTabulatedFunction function;
    @BeforeEach
    void setUp() {
        // Инициализация функции с начальными значениями
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};
        function = new LinkedListTabulatedFunction(xValues, yValues);
    }
    @Test
    void testInitialValues() {
        assertEquals(3, function.getCount(), "Count should be 3");
        assertEquals(1.0, function.getY(0), "Y value at index 0 should be 1.0");
        assertEquals(4.0, function.getY(1), "Y value at index 1 should be 4.0");
        assertEquals(9.0, function.getY(2), "Y value at index 2 should be 9.0");
    }
    @Test
    void testRemoveFirstElement() {
        function.remove(0);
        assertEquals(2, function.getCount(), "Count should be 2 after removing the first element");
        assertEquals(4.0, function.getY(0), "First element should now be y=4.0");
    }
    @Test
    void testRemoveLastElement() {
        function.remove(2);
        assertEquals(2, function.getCount(), "Count should be 2 after removing the last element");
        assertEquals(4.0, function.getY(1), "Last element should now be y=4.0");
    }
}
