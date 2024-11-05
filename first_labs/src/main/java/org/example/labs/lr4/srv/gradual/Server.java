package org.example.labs.lr4.srv.gradual;

import org.example.labs.model.Transport;
import org.example.labs.utils.TransportUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        ObjectInputStream objectInputStream;
        ObjectOutputStream objectOutputStream;

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server start");
        } catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Getting request");
            } catch (IOException e) {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }

            objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            Transport[] transports = (Transport[]) objectInputStream.readObject();

            System.out.println("Getting array : \n");
            for (Transport transport : transports) {
                System.out.println(transport.toString());
            }

            double avg = getAvgFromArray(transports);
            objectOutputStream.writeDouble(avg);

            System.out.println("\nAverage price = " + avg);

            objectOutputStream.flush();
            System.out.println("Send to client...");

            objectInputStream.close();
            objectOutputStream.close();
            clientSocket.close();
        }
    }

    private static double getAvgFromArray(Transport[] transports) {
        double sum = 0;
        int count = 0;
        for (Transport transport : transports) {
            sum += TransportUtils.avg(transport);
            count++;
        }
        return sum/ count;
    }
}