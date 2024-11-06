package main.java.labs.srv.parallel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
            System.out.println("Server start");
        } catch (IOException e) {
            System.out.println("Could not listen on port: 4444");
            System.exit(-1);
        }

        Socket clientSocket = null;

        while (true) {
            try {
                clientSocket = serverSocket.accept();
                System.out.println("Getting request");
            } catch (IOException e) {
                System.out.println("Accept failed: 4444");
                System.exit(-1);
            }

            new Thread(new ProcessThread(clientSocket)).start();
        }
//        ServerSocket serverSocket = null;
//
//        try {
//            serverSocket = new ServerSocket( 4444);
//            System.out.println("Server start");
//        } catch (IOException e) {
//            System.out.println("Could not listen on port: 4444");
//            System.exit(-1);
//        }
//
//        Socket clientSocket = null;
//
//        while (true) {
//            try {
//                clientSocket = serverSocket.accept();
//                System.out.println("Getting request");
//            } catch (IOException e) {
//                System.out.println("Accept failed: 4444");
//                System.exit(-1);
//            }
//
//            System.out.println("Processing start");
//            new Thread(new ProcessThread(clientSocket)).start();
//            System.out.println("Processing over");
//        }
    }
}
