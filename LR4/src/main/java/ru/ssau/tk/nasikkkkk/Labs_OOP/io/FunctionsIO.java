package ru.ssau.tk.nasikkkkk.Labs_OOP.io;

import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.Point;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.TabulatedFunction;
import ru.ssau.tk.nasikkkkk.Labs_OOP.functions.factory.TabulatedFunctionFactory;


import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

final public class FunctionsIO {
    private FunctionsIO(){
        throw new UnsupportedOperationException();
    }
    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function){
         // Создаем PrintWriter, оборачивая переданный BufferedWriter
        PrintWriter printWriter = new PrintWriter(writer);
        // Получаем количество точек в функции
        int count = function.getCount();
        // Записываем количество точек в первой строке
        printWriter.println(count);
        // Перебираем точки функции и записываем каждую из них
        for (Point point : function) {  // Предполагается, что TabulatedFunction реализует Iterable<Point>
            double x = point.x; // Получаем x
            double y = point.y; // Получаем y
            printWriter.printf("%f %f%n", x, y); // Записываем в формате "x y"
        }
        // Пробрасываем данные из буфера
        printWriter.flush();
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            int count = function.getCount();
            dataOutputStream.writeInt(count);  // Записываем количество точек

            for (Point point : function) {
                writePoint(dataOutputStream, point);  // Записываем каждую точку
            }
        }
    }

    // Вспомогательный метод для записи точки
    private static void writePoint(DataOutputStream dataOutputStream, Point point) throws IOException {
        dataOutputStream.writeDouble(point.x);
        dataOutputStream.writeDouble(point.y);
    }
    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        int count = readCount(reader);
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormatter = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

        for (int i = 0; i < count; i++) {
            String line = reader.readLine();
            parseLine(line, numberFormatter, xValues, yValues, i);
        }

        return factory.create(xValues, yValues);
    }

    private static int readCount(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        return Integer.parseInt(firstLine);  // Читаем количество точек
    }

    private static void parseLine(String line, NumberFormat numberFormatter, double[] xValues, double[] yValues, int index) throws IOException {
        String[] parts = line.split(" ");
        try {
            xValues[index] = numberFormatter.parse(parts[0]).doubleValue();
            yValues[index] = numberFormatter.parse(parts[1]).doubleValue();
        } catch (ParseException e) {
            throw new IOException();
        }
    }
    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory) throws IOException {
        DataInputStream stream = new DataInputStream(inputStream);
        int length = stream.readInt();
        double[] xValues = new double[length];
        double[] yValues = new double[length];
        for (int i = 0; i < length; ++i) {
            xValues[i] = stream.readDouble();
            yValues[i] = stream.readDouble();
        }
        return factory.create(xValues, yValues);
    }
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)) {
            objectOutputStream.writeObject(function); // Сериализация объекта функции
            objectOutputStream.flush(); // Пробрасываем данные из буфера
        } catch (IOException e) {
            throw new IOException(); // Обработка исключения
        }
    }
    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
    }
}
