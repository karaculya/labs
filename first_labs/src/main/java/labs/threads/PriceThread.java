package main.java.labs.threads;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

//Задание 1
public class PriceThread extends Thread {
    private Transport transport;

    public PriceThread(Transport transport) {
        this.transport = transport;
    }

    public void run() {
        if (!Thread.interrupted()) {
            TransportUtils.printAllModelPrices(transport);
        }
        System.out.println("1. PriceThread is over");
    }
}