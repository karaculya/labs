package main.java.labs.patterns.structural;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Adapter {

    public static void writeStringsToOutputStream(String[] strings, OutputStream outputStream) throws IOException {
        for (String str : strings) {
            if (str != null) {
                outputStream.write(str.length());
                outputStream.write(str.getBytes());
            }
        }
        outputStream.flush();
    }

    public static String[] readStringsFromInputStream(InputStream inputStream) throws IOException {
        List<String> strings = new ArrayList<>();

        int length;
        while ((length = inputStream.read()) != -1) {
            byte[] buffer = new byte[length];
            inputStream.read(buffer);
            strings.add(new String(buffer));
        }

        return strings.toArray(new String[0]);
    }
}
