package functions;

abstract public class AbstractTabulatedFunction implements TabulatedFunction{
    protected int count;
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
}
