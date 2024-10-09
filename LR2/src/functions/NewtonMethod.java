package functions;
//Y- метод Ньютона
public class NewtonMethod implements MathFunction {
    private MathFunction fx;
    private MathFunction dfx;
    public NewtonMethod(MathFunction fx, MathFunction dfx){
        this.fx = fx;
        this.dfx = dfx;
    }
    @Override
    public double apply(double x) {
        double x1  = x - fx.apply(x) / dfx.apply(x); // первое приближение
        while (Math.abs(x1 - x) > 0.000001) { // пока не достигнута точность 0.000001
            x = x1;
            x1 = x - fx.apply(x) / dfx.apply(x); // последующие приближения
        }
        return x1;
    }
}
