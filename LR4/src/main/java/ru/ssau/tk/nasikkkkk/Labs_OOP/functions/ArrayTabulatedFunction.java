package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;

import ru.ssau.tk.nasikkkkk.Labs_OOP.exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.Arrays;
import java.util.Iterator;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removeable, Serializable {
    @Serial
    private static final long serialVersionUID = 3154741463685093949L;
    private double[] xValues;
    private double[] yValues;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2 && yValues.length < 2) throw new IllegalArgumentException();
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        count = this.xValues.length;
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) throw new IllegalArgumentException();
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (xFrom == xTo) {
            double y = source.apply(xFrom);
            for (int i = 0; i < count; ++i) {
                xValues[i] = xFrom;
                yValues[i] = y;
            }
        } else {
            xValues[0] = xFrom;
            yValues[0] = source.apply(xFrom);
            xValues[count - 1] = xTo;
            yValues[count - 1] = source.apply(xTo);

            double step = (xTo - xFrom) / (count - 1);
            double temp = xFrom + step;

            for (int i = 1; i < count - 1; ++i) {
                xValues[i] = temp;
                yValues[i] = source.apply(temp);
                temp += step;
            }
        }
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
        this.count = this.xValues.length;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x > xValues[count - 1]) throw new IllegalArgumentException();
        if (x < xValues[0]) throw new IllegalArgumentException();
        for (int i = 0; i < count - 1; ++i) {
            if (xValues[i] <= x) {
                if (xValues[i + 1] > x) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, getX(0), getX(1), getY(0), getY(1));
    }

    @Override
    protected double extrapolateRight(double x) {
        int k = count - 1;
        return interpolate(x, getX(k - 1), getX(k), getY(k - 1), getY(k));
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex < 0 || floorIndex >= count - 1) {
            throw new InterpolationException();
        }

        double leftX = getX(floorIndex);
        double leftY = getY(floorIndex);
        double rightX = getX(floorIndex + 1);//Соседняя точка по x
        double rightY = getY(floorIndex + 1);//Соседняя точка по y

        return interpolate(x, leftX, rightX, leftY, rightY);
    }

    @Override
    public double getX(int index) {
        if(index >= 0 && index < count){
            return xValues[index];
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getY(int index) {
        if(index >= 0 && index < count){
            return yValues[index];
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setY(int index, double value) {
        if (index >= 0 && index < count) {
            yValues[index] = value;
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; ++i) {
            if (xValues[i] == x) return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; ++i) {
            if (yValues[i] == y) return i;
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

    @Override
    public void insert(double x, double y) {
        int i = 0;
        while(i < count && getX(i) < x) ++i;
        if(i < count && getX(i) == x) setY(i, y);
        else if (i < count){
            double[] xTempFull = new double[count+1];
            double[] yTempFull = new double[count+1];
            System.arraycopy(xValues, 0, xTempFull, 0, i);
            xTempFull[i] = x;
            System.arraycopy(xValues, i, xTempFull, i + 1, count - i);
            xValues = new double[count+1];
            System.arraycopy(xTempFull, 0, xValues, 0, count + 1);

            System.arraycopy(yValues, 0, yTempFull, 0, i);
            yTempFull[i] = y;
            System.arraycopy(yValues, i, yTempFull, i + 1, count - i);
            yValues = new double[count+1];
            System.arraycopy(yTempFull, 0, yValues, 0, count + 1);
            ++count;
        }
        else{
            double[] xTempFull = new double[count+1];
            double[] yTempFull = new double[count+1];
            System.arraycopy(xValues, 0, xTempFull, 0, i);
            xTempFull[i] = x;
            xValues = new double[count+1];
            System.arraycopy(xTempFull, 0, xValues, 0, count + 1);

            System.arraycopy(yValues, 0, yTempFull, 0, i);
            yTempFull[i] = y;
            yValues = new double[count+1];
            System.arraycopy(yTempFull, 0, yValues, 0, count + 1);
            ++count;
        }
    }

    @Override
    public void remove(int index) {
        // Проверка на допустимость индекса
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }

        // Если удаляем последний элемент, просто уменьшаем счетчик
        if (index == count - 1) {
            count--;
            return;
        }

        // Создаем новый массив размером на один элемент меньше
        double[] updatedXValues = new double[count - 1];
        double[] updatedYValues = new double[count - 1];

        // Копируем элементы перед удаляемым индексом
        for (int i = 0; i < index; i++) {
            updatedXValues[i] = xValues[i];
            updatedYValues[i] = yValues[i];
        }

        // Копируем элементы после удаляемого индекса
        for (int i = index + 1; i < count; i++) {
            updatedXValues[i - 1] = xValues[i];  // Сдвигаем на один влево
            updatedYValues[i - 1] = yValues[i];  // Сдвигаем на один влево
        }

        // Обновляем оригинальные массивы с новыми значениями
        xValues = updatedXValues;
        yValues = updatedYValues;

        // Уменьшаем общий счетчик элементов
        count--;
    }
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int index = 0;   //индекс для обхода массива

            @Override
            public boolean hasNext() {
                return index < count;  // проверяем, есть ли еще элементы для обхода
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();  //  при отсутствии элементов
                }
                //  новый объект Point на основе текущих значений x и y
                Point currentPoint = new Point(xValues[index], yValues[index]);
                index++;

                return currentPoint;  // созданный объект Point
            }
        };
    }
}
