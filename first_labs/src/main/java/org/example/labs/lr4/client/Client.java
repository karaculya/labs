package org.example.labs.lr4.client;

import org.example.labs.model.Car;
import org.example.labs.model.Motorbike;
import org.example.labs.model.Transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        Transport[] transports = {
                new Car("BMW", 2),
                new Car("Audi", 3),
                new Motorbike("MTT", 4),
                new Motorbike("Yamaha", 5)
        };

        Socket socket = null;
        ObjectOutputStream socketOutput = null;
        ObjectInputStream socketInput = null;

        try {
            socket = new Socket("localhost", 4444);
            socketOutput = new ObjectOutputStream(socket.getOutputStream());
            socketInput = new ObjectInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }

        socketOutput.writeObject(transports);

        System.out.println("Array of transports : \n");
        for (Transport transport : transports) {
            System.out.println(transport.toString());
        }

        socketOutput.flush();

        System.out.println("\nAverage price for all transport models: " + socketInput.readDouble());

        socketOutput.close();
        socketInput.close();
        socket.close();
    }
}