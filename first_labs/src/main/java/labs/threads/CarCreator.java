package main.java.labs.threads;

import main.java.labs.model.Car;
import main.java.labs.model.Transport;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

//Задание 5
public class CarCreator implements Runnable {
    private String fileName;
    private BlockingQueue queue;

    public CarCreator(String fileName, BlockingQueue queue) {
        this.fileName = fileName;
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            while (reader.ready()) {
                String mark = reader.readLine();
                queue.add(new Car(mark, 0));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
