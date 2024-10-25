package operations;

import functions.Point;
import functions.TabulatedFunction;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction>{
    private TabulatedFunctionFactory factory;
    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedDifferentialOperator(){
        this.factory = new ArrayTabulatedFunctionFactory();
    }
    public TabulatedFunctionFactory getFactory(){
        return factory;
    }
    public void setFactory(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        int pointCount = points.length;

        double[] xValues = createXValues(points, pointCount);
        double[] yValues = computeYValues(points, pointCount);

        return factory.create(xValues, yValues);
    }

    private double[] createXValues(Point[] points, int count) {
        double[] xValues = new double[count];
        for (int i = 0; i < count; i++) {
            xValues[i] = points[i].x;
        }
        return xValues;
    }

    private double[] computeYValues(Point[] points, int count) {
        double[] yValues = new double[count];

        // Вычисление первых n-1 значений по формуле правой производной
        for (int i = 0; i < count - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (points[i + 1].x - points[i].x);
        }

        // Последнее значение по формуле левой производной
        yValues[count - 1] = (points[count - 1].y - points[count - 2].y) / (points[count - 1].x - points[count - 2].x);

        return yValues;
    }
}