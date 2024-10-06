package functions;
public class TabulatedFunction {
    private FunctionPoint[] points;
    public TabulatedFunction(double leftX, double rightX, int pointsCount) {
        if (leftX > rightX) {
            double temp = leftX;
            leftX = rightX;
            rightX = temp;
        }
        points = new FunctionPoint[pointsCount];
        double step = (rightX - leftX) / (pointsCount - 1);
        for (int i = 0; i < pointsCount; i++) {
            points[i] = new FunctionPoint(leftX + i * step, 0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values) {
        points = new FunctionPoint[values.length];
        double step = (rightX - leftX) / (values.length - 1);
        for (int i = 0; i < values.length; i++) {
            points[i] = new FunctionPoint(leftX + i * step, values[i]);
        }
    }
    public double getLeftDomainBorder() {
        return points[0].getX();
    }
    public double getRightDomainBorder() {
        return points[points.length - 1].getX();
    }
    public double getFunctionValue(double x) {
        if (x < getLeftDomainBorder() || x > getRightDomainBorder()) {
            return Double.NaN;
        }
        int left = 0;
        int right = points.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (points[mid].getX() == x) {
                return points[mid].getY();
            } else if (points[mid].getX() < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int index = left - 1;
        double x1 = points[index].getX();
        double x2 = points[index + 1].getX();
        double y1 = points[index].getY();
        double y2 = points[index + 1].getY();
        double y = y1 + (y2 - y1) * (x - x1) / (x2 - x1);
        return y;
    }
    public int getPointsCount() {
        return points.length;
    }
    public FunctionPoint getPoint(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return new FunctionPoint(points[index]);
    }
    public void setPoint(int index, FunctionPoint point) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        // Check if the new point's x value is within the valid range
        if (index > 0 && point.getX() < points[index - 1].getX()) {
            return;
        }
        if (index < points.length - 1 && point.getX() > points[index + 1].getX()) {
            return;
        }
        points[index] = new FunctionPoint(point);
    }
    public double getPointX(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return points[index].getX();
    }
    public void setPointX(int index, double x) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        // Check if the new x value is within the valid range
        if (index > 0 && x < points[index - 1].getX()) {
            return;
        }
        if (index < points.length - 1 && x > points[index + 1].getX()) {
            return;
        }
        points[index].setX(x);
    }
    public double getPointY(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return points[index].getY();
    }
    public void setPointY(int index, double y) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        points[index].setY(y);
    }
    public void deletePoint(int index) {
        if (index < 0 || index >= points.length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        // If this is the last point, just reduce the array size
        if (points.length == 1) {
            points = new FunctionPoint[0];
            return;
        }
        // Create a new array without the deleted point
        FunctionPoint[] newPoints = new FunctionPoint[points.length - 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        System.arraycopy(points, index + 1, newPoints, index, points.length - index - 1);
        points = newPoints;
    }
    public void addPoint(FunctionPoint point) {
        // Find the index where the new point should be inserted
        int index = 0;
        while (index < points.length && points[index].getX() < point.getX()) {
            index++;
        }
        FunctionPoint[] newPoints = new FunctionPoint[points.length + 1];
        System.arraycopy(points, 0, newPoints, 0, index);
        newPoints[index] = new FunctionPoint(point);
        System.arraycopy(points, index, newPoints, index + 1, points.length - index);
        points = newPoints;
    }
}