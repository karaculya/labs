package main.java.labs;

import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.NoSuchModelNameException;
import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;
import main.java.labs.threads.*;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        /* Task 1
        Properties properties = Singleton.getInstance();
        System.out.println(properties.getProperty("version"));
        System.out.println(properties.getProperty("name"));
        System.out.println(properties.getProperty("date"));
        // another task
        String[] originalStrings = {"Hello", "World", "Java"};

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Adapter.writeStringsToOutputStream(originalStrings, outputStream);
            System.out.println(outputStream);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            String[] readStrings = Adapter.readStringsFromInputStream(inputStream);
            System.out.println(Arrays.toString(readStrings));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
         */

        /* Task 2
        Transport transport = TransportUtils.createInstance("auto", 3);
        System.out.println(transport.getClass().getSimpleName());

        TransportUtils.setTransportFactory(new MotoFactory());
        transport = TransportUtils.createInstance("moto", 2);
        System.out.println(transport.getClass().getSimpleName());
        /* Task 2
        System.out.println(TransportUtils.synchronizedTransport(new Car("lada", 2))
                .getClass().getSimpleName());
         */
        /* Task 4
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Сервер запущен и ожидает подключения...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();

//        /* Task 3
        try {
            Car car = new Car("audi", 2);
            Car clonedCar = car.clone();
            printOriginalAndClone(car, clonedCar);
            clonedCar.setModelName("name1", "model1");
            printOriginalAndClone(car, clonedCar);

            System.out.println("---------------------------------------");

            Motorbike moto = new Motorbike("moto", 3);
            Motorbike clonedMoto = moto.clone();
            printOriginalAndClone(moto, clonedMoto);
            clonedMoto.setModelName("name1", "model1");
            printOriginalAndClone(moto, clonedMoto);
        } catch (DuplicateModelNameException | NoSuchModelNameException | CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
//             */
        /* Task 2.1
        String[] originalStrings = {"Hello", "World", "Java"};

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Adapter.writeStringsToOutputStream(originalStrings, outputStream);
            System.out.println(outputStream);

            ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            String[] readStrings = Adapter.readStringsFromInputStream(inputStream);

            System.out.println(Arrays.toString(readStrings));
        } catch (IOException e) {
            e.printStackTrace();
        }
         */

        /* Task 2.2
        System.out.println(TransportUtils.synchronizedTransport(new Car("lada", 2))
                .getClass().getSimpleName());
         */
        /* Task 2.4
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Сервер запущен и ожидает подключения...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    private static void printOriginalAndClone(Transport transport, Transport clonedTransport) {
        System.out.println("Original: " + transport.getMark() + " " + Arrays.toString(transport.getModels()));
        System.out.println("Clone: " + clonedTransport.getMark() + " " + Arrays.toString(clonedTransport.getModels()));
    }
}