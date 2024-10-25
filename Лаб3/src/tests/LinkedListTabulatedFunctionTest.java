package tests;
import functions.LinkedListTabulatedFunction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LinkedListTabulatedFunctionTest {
    private LinkedListTabulatedFunction function;
    @BeforeEach
    void setUp() {
        // Инициализация функции с начальными значениями
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};
        function = new LinkedListTabulatedFunction(xValues, yValues);
    }
    @Test
    void testRemoveFirstElement() {
        function.remove(0);
        assertEquals(2, function.getCount(), "Count should be 2 after removing the first element");
        assertEquals(4.0, function.getY(0), "First element should now be y=4.0");
    }
    @Test
    void insert() {
        function.insert(1,2);
        assertEquals(function.getX(function.indexOfX(1)), function.getX(0));
        assertEquals(function.getX(function.indexOfY(2)), function.getX(0));
    }

    @Test
    void remove() {
        function.remove(2);
        assertEquals(2, function.getCount(), "Count should be 2 after removing the last element");
        assertEquals(4.0, function.getY(1), "Last element should now be y=4.0");
    }

    @Test
    void getCount(){
        assertEquals(3, function.getCount(), "Count should be 3");
    }
    @Test
    void leftBound() {
        assertEquals(1,function.leftBound());
    }

    @Test
    void rightBound() {
        assertEquals(3,function.rightBound());
    }

    @Test
    void getX() {
        assertEquals(1.0, function.getX(0), "X value at index 0 should be 1.0");
        assertEquals(2.0, function.getX(1), "X value at index 1 should be 2.0");
        assertEquals(3.0, function.getX(2), "X value at index 2 should be 3.0");
    }

    @Test
    void getY() {
        assertEquals(1.0, function.getY(0), "Y value at index 0 should be 1.0");
        assertEquals(4.0, function.getY(1), "Y value at index 1 should be 4.0");
        assertEquals(9.0, function.getY(2), "Y value at index 2 should be 9.0");
    }

    @Test
    void setY() {
        function.setY(0, 11);
        function.setY(1, 22);
        function.setY(2, 33);
        assertEquals(11, function.getY(0), "Y value at index 0 should be 11");
        assertEquals(22, function.getY(1), "Y value at index 1 should be 22");
        assertEquals(33, function.getY(2), "Y value at index 2 should be 33");
    }

    @Test
    void indexOfX() {
        assertEquals(0, function.indexOfX(1));
        assertEquals(1, function.indexOfX(2));
        assertEquals(2, function.indexOfX(3));
        assertEquals(-1, function.indexOfX(111));
    }

    @Test
    void indexOfY() {
        assertEquals(0, function.indexOfY(1));
        assertEquals(1, function.indexOfY(4));
        assertEquals(2, function.indexOfY(9));
        assertEquals(-1, function.indexOfY(111));
    }

    }

