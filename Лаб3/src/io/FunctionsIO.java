package io;
import functions.Point;
import functions.TabulatedFunction;

import java.io.*;
import java.io.PrintWriter;

public class FunctionsIO {
    public FunctionsIO() {
        throw new UnsupportedOperationException();
    }
    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        PrintWriter pw = new PrintWriter(writer);
        pw.println(function.getCount());
        for (Point el : function) {
            pw.printf("%f %f\n", el.x, el.y);
        }
        pw.flush();
    }


}
