package main.java.labs.srv.gradual;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

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

            double avg = TransportUtils.getAverage(transports);
            objectOutputStream.writeDouble(avg);

            System.out.println("\nAverage price = " + avg);

            objectOutputStream.flush();
            System.out.println("Send to client...");

            objectInputStream.close();
            objectOutputStream.close();
            clientSocket.close();
        }
    }
}