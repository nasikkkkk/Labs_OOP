package operations;
import exceptions.InconsistentFunctionsException;
import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.Point;
import functions.TabulatedFunction;
public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public void set(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedFunctionFactory get(){
        return factory;
    }
    public static Point[] asPoints(TabulatedFunction tabulatedFunction){
        Point[] points = new Point[tabulatedFunction.getCount()];
        int index = 0;
        for(Point point : tabulatedFunction){
            points[index] = point;
            index++;
        }
        return points;
    }
    private TabulatedFunction  doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation){
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException();
        }
        int length = a.getCount();
        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);

        double[] xValues = new double[length];
        double[] yValues = new double[length];

        for (int i = 0; i < length; i++) {
            xValues[i] = pointsA[i].x;
            if (xValues[i] != pointsB[i].x) {
                throw new InconsistentFunctionsException();
            }
            yValues[i] = operation.apply(pointsA[i].y, pointsB[i].y);
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return  doOperation(a, b, (u, v) -> u + v); // Сложение
    }

    public TabulatedFunction sub(TabulatedFunction a, TabulatedFunction b) {
        return  doOperation(a, b, (u, v) -> u - v); // Вычитание
    }

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory){
        this.factory = factory;
    }
    public TabulatedFunctionOperationService(){
        this.factory = new ArrayTabulatedFunctionFactory();
    }
    private interface BiOperation{
        double apply(double u, double v);
    }

    public TabulatedFunction mul(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction div(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }
}