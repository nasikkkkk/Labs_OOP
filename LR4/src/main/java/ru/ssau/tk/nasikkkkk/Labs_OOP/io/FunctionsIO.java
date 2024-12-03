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
    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) throws IOException {
        PrintWriter printWriter = new PrintWriter(writer);

        printWriter.println(function.getCount());


        for (Point point : function) {
            printWriter.printf("%f %f%n", point.x, point.y);
        }

        // Пробрасываем данные из буфера в поток
        printWriter.flush();
    }
    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function) throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            int count = function.getCount();
            dataOutputStream.writeInt(count);  

            for (Point point : function) {
                writePoint(dataOutputStream, point);  
            }
        }
    }

    // вспомогательный метод для записи точки
    private static void writePoint(DataOutputStream dataOutputStream, Point point) throws IOException {
        dataOutputStream.writeDouble(point.x);
        dataOutputStream.writeDouble(point.y);
    }
   public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory) throws IOException {
        String countLine = reader.readLine();
        int count = Integer.parseInt(countLine);

        double[] xValues = new double[count];
        double[] yValues = new double[count];
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));

        // Чтение точек функции
        for (int i = 0; i < count; i++) {
            String line = reader.readLine();
            String[] parts = line.split(" ");


            try {
                xValues[i] = numberFormat.parse(parts[0]).doubleValue();
                yValues[i] = numberFormat.parse(parts[1]).doubleValue();
            } catch (ParseException e) {
                throw new IOException();
            }
        }


        return factory.create(xValues, yValues);
    }

    private static int readCount(BufferedReader reader) throws IOException {
        String firstLine = reader.readLine();
        return Integer.parseInt(firstLine);  // читаем количество точек
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
    public static void serialize(BufferedOutputStream stream, TabulatedFunction function)
            throws IOException
    {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream))
        {
            objectOutputStream.writeObject(function);
        }
        stream.flush();
    }
    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectInputStream.readObject();
    }
}
