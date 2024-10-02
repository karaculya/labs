package org.example.labs;

import org.example.labs.exceptions.DuplicateModelNameException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TransportUtils {

    public static double avg(Transport transport) {
        double count = 0;
        for (double price : transport.getAllModelPrices()) {
            count += price;
        }
        return count / transport.getModelsSize();
    }

    public static void printAllModelNames(Transport transport) {
        for (String modelName : transport.getAllModelNames()) {
            System.out.println(modelName);
        }
    }

    public static void printAllModelPrices(Transport transport) {
        for (double modelPrice : transport.getAllModelPrices()) {
            System.out.println(modelPrice);
        }
    }

    //- записи информации о транспортном средстве в байтовый поток (использовать DataOutputStream)
    public static void outputTransport(Transport transport, OutputStream out) {
        try (DataOutputStream dataOut = new DataOutputStream(out)) {
            String className = transport.getClass().getSimpleName();
            dataOut.writeInt(className.length());
            dataOut.write(className.getBytes(StandardCharsets.UTF_8));
            dataOut.writeInt(transport.getMark().length());
            dataOut.write(transport.getMark().getBytes(StandardCharsets.UTF_8));
            dataOut.writeInt(transport.getModelsSize());
            String[] allModels = transport.getAllModelNames();
            for (String modelName : allModels) {
                dataOut.writeInt(modelName.length());
                dataOut.write(modelName.getBytes(StandardCharsets.UTF_8));
            }
            double[] allPrices = transport.getAllModelPrices();
            for (double modelPrice : allPrices) {
                dataOut.writeDouble(modelPrice);
            }
            System.out.println("Success writing");
        } catch (IOException e) {
            System.out.println("Failed writing transport from byte stream. " + e.getMessage());
        }
    }

    //- чтения информации о транспортном средстве из байтового потока (использовать DataInputStream)
    public static Transport inputTransport(InputStream in) {
        DataInputStream dataIn = new DataInputStream(in);
        Transport transport = null;
        try {
            switch (readString(dataIn)) {
                case ("Car") -> transport = new Car();
                case ("Motorbike") -> transport = new Motorbike();
                default -> throw new ClassCastException("Failed casting");
            }

            transport.setMark(readString(dataIn));

            int modelsSize = dataIn.readInt();
            String[] allModelNames = new String[modelsSize];
            for (int i = 0; i < allModelNames.length; i++)
                allModelNames[i] = readString(dataIn);

            double[] allModelPrices = new double[modelsSize];
            for (int i = 0; i < allModelPrices.length; i++)
                allModelPrices[i] = dataIn.readDouble();

            setModels(transport, allModelNames, allModelPrices);

            System.out.println("Transport has class a " + transport.getClass().getSimpleName());
            System.out.println(transport.getMark());
            System.out.println(Arrays.toString(transport.getAllModelPrices()) + " ");
        } catch (IOException e) {
            System.out.println("Failed reading transport to byte stream. " + e.getMessage());
            e.printStackTrace();
        }
        return transport;
    }

    //- записи информации о транспортном средстве в символьный поток (использовать PrintWriter)
    public static void writeTransport(Transport v, Writer out) {
        try (PrintWriter writer = new PrintWriter(out)) {
            writer.println(v.getClass().getSimpleName());
            writer.println(v.getMark());
            writer.println(v.getModelsSize());

            String[] modelNames = v.getAllModelNames();
            for (String modelName : modelNames) {
                writer.println(modelName);
            }

            double[] modelPrices = v.getAllModelPrices();
            for (double modelPrice : modelPrices) {
                writer.println(modelPrice);
            }
            System.out.println("Success writing");
        }
    }

    //- чтения информации о транспортном средстве из символьного потока (использовать BufferedReader или StreamTokenizer)
    public static Transport readTransport(Reader in) {
        Transport transport = null;
        try (BufferedReader reader = new BufferedReader(in)) {
            if (reader.ready()) {
                switch (reader.readLine()) {
                    case ("Car") -> transport = new Car();
                    case ("Motorbike") -> transport = new Motorbike();
                    default -> throw new ClassCastException("Failed casting");
                }

                transport.setMark(reader.readLine());

                int modelsSize = Integer.parseInt(reader.readLine());

                String[] allModelNames = new String[modelsSize];
                for (int i = 0; i < allModelNames.length; i++)
                    allModelNames[i] = reader.readLine();

                double[] allModelPrices = new double[modelsSize];
                for (int i = 0; i < allModelPrices.length; i++)
                    allModelPrices[i] = Double.parseDouble(reader.readLine());

                setModels(transport, allModelNames, allModelPrices);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
            for (int i = 0; i < models.length; i++) {
                transport.addNewModel(models[i], prices[i]);
            }
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
