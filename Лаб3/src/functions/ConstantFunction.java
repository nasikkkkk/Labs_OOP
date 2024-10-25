package functions;
public class ConstantFunction implements MathFunction {
    private final double constant; // Приватное и неизменяемое поле для хранения константы
    public ConstantFunction(double constant) {
        this.constant = constant; // Устанавливаем значение константы через конструктор
    }
    @Override
    public double apply(double x) {
        return constant; // Возвращаем константу вне зависимости от x
    }
}