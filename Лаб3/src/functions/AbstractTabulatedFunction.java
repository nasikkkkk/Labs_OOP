package functions;

import exceptions.ArrayIsNotSortedException;
import exceptions.DifferentLengthOfArraysException;

abstract public class AbstractTabulatedFunction implements TabulatedFunction{
    protected int count;

    protected static void checkLengthIsTheSame(double[] xValues, double[] yValues){
        if(xValues.length != yValues.length)
            throw new DifferentLengthOfArraysException();
    }

    protected static void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; ++i) {
            if(xValues[i] > xValues[i+1])
                throw new ArrayIsNotSortedException();
        }
    }

    abstract protected int floorIndexOfX(double x);
    abstract protected double extrapolateLeft(double x);
    abstract protected double extrapolateRight(double x);
    abstract protected double interpolate(double x, int floorIndex);
    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + ((rightY - leftY) / (rightX - leftX)) * (x - leftX);
    }
    @Override
    public int getCount() {return count;}
    @Override
    public double apply(double x) {
        if (x < getX(0)) {
            return extrapolateLeft(x);
        }
        if (x > getX(getCount() - 1)) {
            return extrapolateRight(x);
        }
        int index = indexOfX(x);
        if (index != -1) {
            return getY(index);
        }
        index = floorIndexOfX(x);
        return interpolate(x, index);
    }
    @Override

    public String toString() {
        StringBuilder builder = new StringBuilder();

        // Добавляем имя класса и размер
        builder.append(getClass().getSimpleName())
                .append(" size = ")
                .append(getCount());

        // Добавляем точки в нужном формате
        for (Point point : this) {
            builder.append("\n[")
                    .append(point.x)
                    .append("; ")
                    .append(point.y)
                    .append("]");
        }

        return builder.toString();
    }

}

