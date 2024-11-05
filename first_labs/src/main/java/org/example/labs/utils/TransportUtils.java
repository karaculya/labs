package org.example.labs.utils;

import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;
import org.example.labs.model.Car;
import org.example.labs.model.Motorbike;
import org.example.labs.model.Transport;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class TransportUtils {

    public static double avg(Transport transport) {
        double count = 0;
        double[] allModelPrices = transport.getAllModelPrices();
        for (double price : allModelPrices) {
            count += price;
        }
        return count / transport.getModelsSize();
    }

    public static void printAllModelNames(Transport transport) {
        String[] allModelNames = transport.getAllModelNames();
        for (String modelName : allModelNames) {
            System.out.println(modelName);
        }
    }

    public static void printAllModelPrices(Transport transport) {
        double[] allModelPrices = transport.getAllModelPrices();
        for (double modelPrice : allModelPrices) {
            System.out.println(modelPrice);
        }
    }

    //- записи информации о транспортном средстве в байтовый поток (использовать DataOutputStream)
    public static void outputTransport(Transport transport, OutputStream out) {
        try {
            DataOutputStream dataOut = new DataOutputStream(out);
            String className = transport.getClass().getSimpleName();
            dataOut.writeInt(className.length());
            dataOut.write(className.getBytes(StandardCharsets.UTF_8));
            dataOut.writeInt(transport.getMark().length());
            dataOut.write(transport.getMark().getBytes(StandardCharsets.UTF_8));
            dataOut.writeInt(transport.getModelsSize());
            if (transport.getModelsSize() > 0) {
                String[] allModels = transport.getAllModelNames();
                for (String modelName : allModels) {
                    dataOut.writeInt(modelName.length());
                    dataOut.write(modelName.getBytes(StandardCharsets.UTF_8));
                }
                double[] allPrices = transport.getAllModelPrices();
                for (double modelPrice : allPrices) {
                    dataOut.writeDouble(modelPrice);
                }
            }
            System.out.println("Success byte writing object");
            dataOut.flush();
        } catch (IOException e) {
            System.out.println("Failed byte writing transport from byte stream. " + e.getMessage());
        }
    }

    //- чтения информации о транспортном средстве из байтового потока (использовать DataInputStream)
    public static Transport inputTransport(InputStream in) {
        DataInputStream dataIn = new DataInputStream(in);
        Transport transport = null;
        try {
            String className = readString(dataIn);
            if (className.isEmpty()) throw new ClassCastException("Failed casting");

            String mark = readString(dataIn);

            switch (className.toLowerCase()) {
                case ("car") -> transport = new Car(mark, 0);
                case ("motorbike") -> transport = new Motorbike(mark, 0);
            }

            int modelsSize;
            if ((modelsSize = dataIn.readInt()) > 0) {
                String[] allModelNames = new String[modelsSize];
                for (int i = 0; i < allModelNames.length; i++)
                    allModelNames[i] = readString(dataIn);

                double[] allModelPrices = new double[modelsSize];
                for (int i = 0; i < allModelPrices.length; i++)
                    allModelPrices[i] = dataIn.readDouble();

                setModels(transport, allModelNames, allModelPrices);
            }
            System.out.println("Success byte reading object");
        } catch (IOException | DuplicateModelNameException e) {
            System.out.println("Failed byte reading transport from byte stream. " + e.getMessage());
        }
        return transport;
    }

    //- записи информации о транспортном средстве в символьный поток (использовать PrintWriter)
    public static void writeTransport(Transport v, Writer out) {
        PrintWriter writer = new PrintWriter(out);
        writer.println(v.getClass().getSimpleName());
        writer.println(v.getMark());
        writer.println(v.getModelsSize());

        if (v.getModelsSize() > 0) {
            String[] modelNames = v.getAllModelNames();
            for (String modelName : modelNames)
                writer.println(modelName);

            double[] modelPrices = v.getAllModelPrices();
            for (double modelPrice : modelPrices)
                writer.println(modelPrice);
        }
        System.out.println("Success symbol writing object");
        writer.flush();
    }

    //- чтения информации о транспортном средстве из символьного потока (использовать BufferedReader или StreamTokenizer)
    public static Transport readTransport(Reader in) {
        Transport transport = null;
        try {
            BufferedReader reader = new BufferedReader(in);
            String className = reader.readLine();

            String mark = reader.readLine();
            switch (className.toLowerCase()) {
                case ("car") -> transport = new Car(mark, 0);
                case ("motorbike") -> transport = new Motorbike(mark, 0);
            }

            int modelsSize;
            if ((modelsSize = Integer.parseInt(reader.readLine())) > 0) {
                String[] allModelNames = new String[modelsSize];
                for (int i = 0; i < allModelNames.length; i++)
                    allModelNames[i] = reader.readLine();

                double[] allModelPrices = new double[modelsSize];
                for (int i = 0; i < allModelPrices.length; i++)
                    allModelPrices[i] = Double.parseDouble(reader.readLine());

                setModels(transport, allModelNames, allModelPrices);
            }
            System.out.println("Success symbol reading object");
        } catch (IOException | DuplicateModelNameException e) {
            System.out.println("Failed symbol reading transport from byte stream. " + e.getMessage());
        }
        return transport;
    }

    private static String readString(DataInputStream dataIn) throws IOException {
        int length = dataIn.readInt();
        byte[] bytes = dataIn.readNBytes(length);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private static void setModels(Transport transport, String[] models, double[] prices) throws DuplicateModelNameException {
        if (models.length == prices.length && models.length != 0) {
            for (int i = 0; i < models.length; i++)
                transport.addNewModel(models[i], prices[i]);
        }
    }

/*
В обоих случаях нужно записать
марку транспортного средства,
количество моделей,
а затем список моделей
и цен моделей.
При записи строки в байтовый поток использовать метод getBytes() для перевода строки в массив байт. Перед строкой нужно записать её длину.
Проверить возможности методов (в методе main), в качестве реальных потоков используя файловые потоки (FileInputStream, FileOutputStream, FileReader и FileWriter), а также потоки System.in и System.out.
 */
}
