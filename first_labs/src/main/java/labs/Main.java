package main.java.labs;

import main.java.labs.model.Car;
import main.java.labs.patterns.structural.Adapter;
import main.java.labs.server.ClientHandler;
import main.java.labs.utils.TransportUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        /* Task 1
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
        }
         */
    }
}