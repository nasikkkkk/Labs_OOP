<<<<<<< HEAD
package functions;

public class ZeroFunction implements MathFunction{
    @Override
    public double apply(double x) {
        return 0;
    }
=======
package functions;

public class ZeroFunction extends ConstantFunction {

    public ZeroFunction() {
        super(0); 
    }
>>>>>>> 4fe543a0c1767e47e0c43da207535ff7652111da
}