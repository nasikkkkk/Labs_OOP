package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = this.xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                xValues[i] = xFrom;
                yValues[i] = source.apply(xFrom);
            }
        } 
        else {
            xValues[0] = xFrom;
            yValues[0] = source.apply(xFrom);
            xValues[count - 1] = xTo;
            yValues[count - 1] = source.apply(xTo);

            double nextPoint = (xTo - xFrom) / (count - 1);
            double curr = nextPoint;

            for (int i = 1; i < count - 1; i++) {
                xValues[i] = curr;
                yValues[i] = source.apply(curr);
                curr += nextPoint;
            }
        }
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = this.xValues.length;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x > xValues[count - 1]) {
            return count - 1;
        }
        if (x < xValues[0]) {
            return 0;
        }
        for (int i = 0; i < count - 1; i++) {
            if (xValues[i] <= x) {
                if (xValues[i + 1] > x) {
                    return i;
                }
            }
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        if (count == 1) {
            return getY(0);
        }
        return interpolate(x, getX(0), getX(1), getY(0), getY(1));
    }

    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) {
            return getY(0);
        }
        return interpolate(x, getX(count - 2), getX(count-1), getY(count - 2), getY(count-1));
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (count == 1) {
            return getY(0);
        }
        return interpolate(x, getX(floorIndex), getX(floorIndex + 1), getY(floorIndex), getY(floorIndex + 1));
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        if (index >= 0 && index < count) {
            yValues[index] = value;
        }
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

}