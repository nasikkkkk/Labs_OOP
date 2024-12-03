package ru.ssau.tk.nasikkkkk.Labs_OOP.functions;

import ru.ssau.tk.nasikkkkk.Labs_OOP.exceptions.InterpolationException;

import java.io.Serial;
import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removeable, Serializable {

    private static final long serialVersionUID = 84555L;


    private static class Node implements Serializable {
        @Serial
        private static final long serialVersionUID = 3406965L;
        public Node prev;
        public Node next;
        public double x;
        public double y;
    }

    private Node head; 

  
    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
       
        if (xValues.length < 2 && yValues.length < 2) {
            throw new IllegalArgumentException(); 
        }

    
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);

  
        for (int i = 0; i < xValues.length; ++i) {
            addNode(xValues[i], yValues[i]);
        }
    }

 
    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
  
        if (count < 2) {
            throw new IllegalArgumentException();
        }

    
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo; 
            xTo = temp; 
        }

        // Проверка на случай, если границы равны
        if (xFrom == xTo) {
            double y = source.apply(xFrom); 
         
            for (int i = 0; i < count; ++i) {
                addNode(xFrom, y);
            }
        } else {
         
            addNode(xFrom, source.apply(xFrom));

            // Вычисляем шаг между значениями
            double step = (xTo - xFrom) / (count - 1);
            double temp = xFrom + step;

            // Добавляем промежуточные узлы
            for (int i = 1; i < count - 1; ++i) {
                addNode(temp, source.apply(temp)); // 
                temp += step; //
            }
            
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
        if (x < getNode(0).x) throw new IllegalArgumentException();

        for (int i = 0; i < count - 1; ++i) {
            if (getNode(i).x <= x) {
                if (getNode(i + 1).x > x) {
                    return i;
                }
            }
        }

        throw new IllegalArgumentException();
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
        double rightX = getX(floorIndex + 1);
        double rightY = getY(floorIndex + 1);

        return interpolate(x, leftX, rightX, leftY, rightY);
    }

    protected double interpolate(double x, Node floorNode) {
        if (count == 1) {
            return getY(0);
        }

        double leftX = floorNode.x;
        double leftY = floorNode.y;
        double rightX = floorNode.next.x; 
        double rightY = floorNode.next.y; 

        return interpolate(x, leftX, rightX, leftY, rightY);
    }

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
