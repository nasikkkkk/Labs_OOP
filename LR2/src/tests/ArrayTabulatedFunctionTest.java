package tests;

import functions.ArrayTabulatedFunction; // Replace with the actual package and class name
import functions.MathFunction; // Replace with the actual MathFunction interface/package
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConcreteArrayTabulatedFunction extends ArrayTabulatedFunction {
    public ConcreteArrayTabulatedFunction(double[] xValues, double[] yValues) {
        super(xValues, yValues);
    }

    @Override
    protected double interpolate(double x, double x0, double x1, double y0, double y1) {
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    @Override
    public double getY(double index) {
        return 0;
    }

    @Override
    public double getY() {
        return 0;
    }
}

class ArrayTabulatedFunctionTest {
    private ArrayTabulatedFunction function;

    @BeforeEach
    void setUp() {
        double[] xValues = {1.0, 3.0, 5.0};
        double[] yValues = {2.0, 4.0, 6.0};
        function = new ConcreteArrayTabulatedFunction(xValues, yValues);
    }

    // Tests for insert method
    @Test
    void testInsert1() {
        function.insert(2.0, 3.0);
        Assertions.assertEquals(3.0, function.getY(1), 1e-6);
        Assertions.assertEquals(2.0, function.getX(1), 1e-6);
    }

    @Test
    void testInsert2() {
        function.insert(3.0, 5.0);
        Assertions.assertEquals(5.0, function.getY(1), 1e-6);
    }





    // Tests for floorIndexOfX method
    @Test
    void testFloorIndexO() {
        Assertions.assertEquals(0, function.floorIndexOfX(0.5));
    }

    @Test
    void testFloorIndexO0() {
        Assertions.assertEquals(2, function.floorIndexOfX(6.5));
    }

    @Test
    void testFloorIndexOf() {
        Assertions.assertEquals(1, function.floorIndexOfX(4.5));
    }

    // Tests for extrapolateLeft method
    @Test
    void testExtrapolateLeftSinglePoint() {
        ArrayTabulatedFunction singlePointFunction = new ConcreteArrayTabulatedFunction(new double[]{2.0}, new double[]{3.0});
        Assertions.assertEquals(3.0, singlePointFunction.extrapolateLeft(1.5), 1e-6);
    }


    @Test
    void testExtrapolateLeftOutOfBounds() {
        Assertions.assertEquals(2.0, function.extrapolateLeft(1.0), 1e-6);
    }

    // Tests for extrapolateRight method
    @Test
    void testExtrapolateRightSinglePoint() {
        ArrayTabulatedFunction singlePointFunction = new ConcreteArrayTabulatedFunction(new double[]{2.0}, new double[]{3.0});
        Assertions.assertEquals(3.0, singlePointFunction.extrapolateRight(2.5), 1e-6);
    }





    // Tests for getX method
    @Test
    void testGetXValidIndex() {
        Assertions.assertEquals(1.0, function.getX(0), 1e-6);
        Assertions.assertEquals(3.0, function.getX(1), 1e-6);
        Assertions.assertEquals(5.0, function.getX(2), 1e-6);
    }

    @Test
    void testGetXInvalidIndex() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getX(-1));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getX(3));
    }

    // Tests for getY method
    @Test
    void testGetYValidIndex() {
        Assertions.assertEquals(2.0, function.getY(0), 1e-6);
        Assertions.assertEquals(4.0, function.getY(1), 1e-6);
        Assertions.assertEquals(6.0, function.getY(2), 1e-6);
    }

    @Test
    void testGetYInvalidIndex() {
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getY(-1));
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class, () -> function.getY(3));
    }

    // Tests for setY method
    @Test
    void testSetYValidIndex() {
        function.setY(1, 5.5);
        Assertions.assertEquals(5.5, function.getY(1), 1e-6);
    }




    // Tests for indexOfX method
    @Test
    void testIndexOfXFound() {
        Assertions.assertEquals(1, function.indexOfX(3.0));
    }

    @Test
    void testIndexOfXNotFound() {
        Assertions.assertEquals(-1, function.indexOfX(4));
    }

    // Tests for indexOfY method
    @Test
    void testIndexOfYFound() {
        Assertions.assertEquals(2, function.indexOfY(6.0));
    }

    @Test
    void testIndexOfYNotFound() {
        Assertions.assertEquals(-1, function.indexOfY(-10));
    }

    // Tests for leftBound method
    @Test
    void testLeftBound() {
        Assertions.assertEquals(1.0, function.leftBound(), 1e-6);
    }

    // Tests for rightBound method
    @Test
    void testRightBound() {
        Assertions.assertEquals(5.0, function.rightBound(), 1e-6);
    }
}