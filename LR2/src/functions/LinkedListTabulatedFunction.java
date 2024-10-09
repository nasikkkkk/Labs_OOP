package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable {
    @Override
    public void insert(double x, double y) {
        if (count == 0){
            addNode(x, y);
        }
        else{
            int i = 0;
            while(getX(i) < x && i < count) {
                i++;
            }
            if(getX(i) == x){
                setY(i, y);
            }
            else if(i == count){
                addNode(x, y);
            }
            else{
                Node element = new Node();
                element.x = x;
                element.y = y;
                Node temp = getNode(i);
                temp.prev.next = element;
                element.prev = temp.prev;
                element.next = temp;
                temp.prev = element;
                count++;
            }
        }
    }

    @Override
    public void remove(int index) {
        if(count == 0){
            head.prev = null;
            head.next = null;
            head = null;
        }
        else if(head == getNode(index)){
            head = head.next;
            head.prev.prev.next = head;
            head.prev = head.prev.prev;
            count--;
        }
        else{
            Node removing = getNode(index);
            removing.prev.next = removing.next;
            removing.next.prev = removing.prev;
            count--;
        }
    }

    private class Node {
        Node next;
        Node prev;
        double x;
        double y;
    }

    Node head;

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if
        (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        } else {
            addNode(xFrom, source.apply(xFrom));
            double nextPoint = (xTo - xFrom) / (count - 1);
            double curr = nextPoint;
            for (int i = 1; i < count - 1; i++) {
                addNode(curr, source.apply(curr));
                curr += nextPoint;
            }
            addNode(xTo, source.apply(xTo));
        }
    }

    private void addNode(double x, double y) {
        if (count == 0) {
            head = new Node();
            head.x = x;
            head.y = y;
            head.next = head;
            head.prev = head;
        } else {
            Node element = new Node();
            element.x = x;
            element.y = y;
            Node temp = head;
            while (temp.next != head)
                temp = temp.next;
            temp.next = element;
            element.prev = temp;
            element.next = head;
            head.prev = element;
        }
        ++count;
    }
    private Node getNode(int index){
        Node temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
    @Override
    public double leftBound(){ return head.x;}
    @Override
    public double rightBound(){return head.prev.x;}
    @Override
    public double getX(int index){ return getNode(index).x;}
    @Override
    public double getY(int index){ return getNode(index).y;}
    @Override
    public void setY(int index, double value){ getNode(index).y = value;}
    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if(getNode(i).x == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if(getNode(i).y == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int floorIndexOfX(double x){
        if (x > getNode(count - 1).x) return  count - 1;
        if (x < getNode(0).x) return 0;
        for (int i = 0; i < count - 1; i++) {
            if (getNode(i).x <= x) {
                if (getNode(i + 1).x > x) {
                    return i;
                }
            }
        }
        return count - 1;
    }
    @Override
    protected double extrapolateRight(double x) {
        if (count == 1) return getY(0);
        return interpolate(x, getX(0), getX(1), getY(0), getY(1));
    }

    @Override
    protected double extrapolateLeft(double x){
        if (count == 1) return getY(0);
        return interpolate(x, getX(count-2), getX(count-1), getY(count-2), getY(count-1));
    }
    @Override
    protected double interpolate(double x, int floorIndex){
        if (count == 1) return getY(0);
        return interpolate(x, getX(floorIndex-1), getX(floorIndex), getY(floorIndex-1), getY(floorIndex));
    }
}
