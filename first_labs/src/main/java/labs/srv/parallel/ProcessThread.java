package main.java.labs.srv.parallel;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ProcessThread implements Runnable {
    private Socket clientSocket;

    public ProcessThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());

            Transport[] transports = (Transport[]) objectInputStream.readObject();

            System.out.println("Getting array : \n");
            for (Transport transport : transports) {
                System.out.println(transport.toString());
            }

            double avg = TransportUtils.getAverage(transports);
            objectOutputStream.writeDouble(avg);
            System.out.println("\nAverage price = " + avg);

            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}