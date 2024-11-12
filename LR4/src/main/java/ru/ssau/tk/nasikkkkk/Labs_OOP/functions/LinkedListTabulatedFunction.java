package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;

import ru.ssau.tk.nasikkkkk.Labs_OOP.exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Класс, представляющий табулированную функцию на основе связного списка
public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removeable, Serializable {

    @Serial
    private static final long serialVersionUID = 84555L; // Уникальный идентификатор для сериализации

    // Вложенный класс, представляющий узел связного списка
    private static class Node implements Serializable {
        @Serial
        private static final long serialVersionUID = 3406965L; // Уникальный идентификатор для сериализации узла
        public Node prev; // Ссылка на предыдущий узел
        public Node next; // Ссылка на следующий узел
        public double x; // Значение x
        public double y; // Значение y
    }

    private Node head; // Голова связного списка

    // Конструктор класса, принимающий массивы значений x и y для создания табулированной функции
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        // Проверка, что массивы содержат как минимум два значения
        if (xValues.length < 2 && yValues.length < 2) {
            throw new IllegalArgumentException(); // Генерация исключения, если условие не выполнено
        }

        // Проверка, что длины массивов x и y совпадают
        checkLengthIsTheSame(xValues, yValues);

        // Проверка, что значения в массиве x отсортированы по возрастанию
        checkSorted(xValues);

        // Проход по всем значениям x и добавление пар (x, y) в связный список
        for (int i = 0; i < xValues.length; ++i) {
            addNode(xValues[i], yValues[i]); // Добавление узла с координатами (x, y)
        }
    }

    // Конструктор класса, принимающий математическую функцию, диапазон x и количество точек
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        // Проверка, что количество точек больше 1
        if (count < 2) {
            throw new IllegalArgumentException(); // Генерация исключения, если условие не выполнено
        }

        // Обмен значений, если xFrom больше xTo, для упрощения дальнейших операций
        if (xFrom > xTo) {
            double temp = xFrom; // Временная переменная для обмена
            xFrom = xTo; // Присваиваем xTo в xFrom
            xTo = temp; // Присваиваем xFrom в xTo
        }

        // Проверка на случай, если границы равны
        if (xFrom == xTo) {
            double y = source.apply(xFrom); // Вычисляем значение функции для xFrom
            // Добавляем узлы с одинаковыми значениями y, count раз
            for (int i = 0; i < count; ++i) {
                addNode(xFrom, y); // Добавление узла с (xFrom, y)
            }
        } else {
            // Добавляем первый узел с xFrom и соответствующим y
            addNode(xFrom, source.apply(xFrom));

            // Вычисляем шаг между значениями
            double step = (xTo - xFrom) / (count - 1);
            double temp = xFrom + step; // Устанавливаем временное значение для следующего узла

            // Добавляем промежуточные узлы
            for (int i = 1; i < count - 1; ++i) {
                addNode(temp, source.apply(temp)); // Добавляем узел с текущим x и вычисленным y
                temp += step; // Увеличиваем временное значение на шаг
            }

            // Добавляем последний узел с xTo и соответствующим y
            addNode(xTo, source.apply(xTo));
        }
    }

    protected void addNode(double x, double y) {
        if (count == 0) {
            head = new Node();
            head.x = x;
            head.y = y;
            head.next = head;
            head.prev = head;
        } else {
            Node newEl = new Node();
            newEl.x = x;
            newEl.y = y;
            Node temp = head;

            while (temp.next != head) temp = temp.next; // Находим последний узел

            temp.next = newEl;
            newEl.prev = temp;
            newEl.next = head;
            head.prev = newEl;
        }
        ++count; // Увеличиваем счетчик узлов в списке
    }

    protected Node getNode(int index) {
        Node temp = head;

        if (index >= 0) {
            for (int i = 0; i < index; ++i) {
                temp = temp.next;
            }
        } else {
            index *= -1;

            for (int i = 0; i < index; ++i) {
                temp = temp.prev;
            }
        }

        return temp; // Возвращаем найденный узел по индексу
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x > getNode(count - 1).x) throw new IllegalArgumentException(); // Проверка на выход за пределы справа
        if (x < getNode(0).x) throw new IllegalArgumentException(); // Проверка на выход за пределы слева

        for (int i = 0; i < count - 1; ++i) {
            if (getNode(i).x <= x) {
                if (getNode(i + 1).x > x) {
                    return i;
                }
            }
        }

        throw new IllegalArgumentException(); // Если подходящий индекс не найден
    }

    protected Node floorNodeOfX(double x) {
        if (x > getNode(count - 1).x) throw new IllegalArgumentException();
        if (x < getNode(0).x) throw new IllegalArgumentException();

        for (int i = 0; i < count - 1; ++i) {
            if (getNode(i).x < x) {
                if (getNode(i + 1).x >= x) {
                    return getNode(i);
                }
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, getX(0), getX(1), getY(0), getY(1)); // Экстраполяция слева от границы данных функции
    }

    @Override
    protected double extrapolateRight(double x) {
        int k = count - 1;

        return interpolate(x, getX(k - 1), getX(k), getY(k - 1), getY(k)); // Экстраполяция справа от границы данных функции
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (floorIndex < 0 || floorIndex >= count - 1) {
            throw new InterpolationException();
        }

        double leftX = getX(floorIndex);
        double leftY = getY(floorIndex);
        double rightX = getX(floorIndex + 1); // Соседняя точка по x
        double rightY = getY(floorIndex + 1); // Соседняя точка по y

        return interpolate(x, leftX, rightX, leftY, rightY); // Интерполяция между двумя соседними точками
    }

    protected double interpolate(double x, Node floorNode) {
        if (count == 1) {
            return getY(0);
        }

        double leftX = floorNode.x;
        double leftY = floorNode.y;
        double rightX = floorNode.next.x; // Соседняя точка по x
        double rightY = floorNode.next.y; // Соседняя точка по y

        return interpolate(x, leftX, rightX, leftY, rightY); // Интерполяция между двумя соседними точками через узел
    }

    @Override
    public double apply(double x) {
        // Если значение меньше левой границы данных функции:
        if (x < getX(0)) {
            return extrapolateLeft(x);
        }
        // Если значение больше правой границы данных функции:
        if (x > getX(getCount() - 1)) {
            return extrapolateRight(x);
        }
        // Проверяем наличие значения в таблице:
        int index = indexOfX(x);
        if (index != -1) {
            return getY(index);
        }
        // Если значения нет в таблице:
        Node nodeEl = floorNodeOfX(x);
        return interpolate(x, nodeEl);
    }

    @Override
    public double getX(int index) {
        if (index >= 0 && index < count) {
            return getNode(index).x;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getY(int index) {
        if (index >= 0 && index < count) {
            return getNode(index).y;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void setY(int index, double value) {
        if (index >= 0 && index < count) {
            getNode(index).y = value;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; ++i) {
            if (getNode(i).x == x)
                return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; ++i) {
            if (getNode(i).y == y)
                return i;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public void insert(double x, double y) {
        if (count == 0) {
            addNode(x, y);
        } else {
            int i = 0;
            while (i < count && getX(i) < x)
                ++i;

            if (i < count && getX(i) == x)
                setY(i, y);
            else if (i == count)
                addNode(x, y);
            else {
                Node newEl = new Node();
                newEl.x = x;
                newEl.y = y;

                Node temp = getNode(i);
                temp.prev.next = newEl;
                newEl.prev = temp.prev;
                newEl.next = temp;
                temp.prev = newEl;

                ++count;
            }
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }

        Node remEl = getNode(index);

        if(count == 0){
            head.prev=null;
            head.next=null;
            head=null;
        }else if(head==remEl){
            head=head.next;
            head.prev.prev.next=head;
            head.prev=head.prev.prev;
            remEl.next=null;
            remEl.prev=null;
        }else{
            remEl.prev.next=remEl.next;
            remEl.next.prev=remEl.prev;
            remEl.next=null;
            remEl.prev=null;
        }

        count--;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private Node node=head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                Point point=new Point(node.x,node.y);
                node=node.next!=head?node.next:null;

                return point;
            }
        };
    }
}