<<<<<<< HEAD

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

    public double getConstant() {
        return constant; // Геттер для получения значения константы
    }
}

package functions;

public class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0); // Вызываем конструктор родительского класса с аргументом 0
    }
}

public class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1); // Вызываем конструктор родительского класса с аргументом 1
    }
=======

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

    public double getConstant() {
        return constant; // Геттер для получения значения константы
    }
}

package functions;

public class ZeroFunction extends ConstantFunction {
    public ZeroFunction() {
        super(0); // Вызываем конструктор родительского класса с аргументом 0
    }
}

public class UnitFunction extends ConstantFunction {
    public UnitFunction() {
        super(1); // Вызываем конструктор родительского класса с аргументом 1
    }
>>>>>>> 4fe543a0c1767e47e0c43da207535ff7652111da
}