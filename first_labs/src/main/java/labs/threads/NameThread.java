package main.java.labs.threads;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

//Задание 1
public class NameThread extends Thread {
    private Transport transport;

    public NameThread(Transport transport) {
        this.transport = transport;
    }

    public void run() {
        if (!Thread.interrupted()) {
            TransportUtils.printAllModelNames(transport);
        }
        System.out.println("NameThread is over");
    }
}