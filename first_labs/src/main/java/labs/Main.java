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
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException, InterruptedException {
        Transport car = new Car("Toyota", 10);
        Transport moto = new Motorbike("BMW", 10);

        /* Task 1
        Thread nameThread = new NameThread(moto);
        nameThread.setPriority(Thread.MAX_PRIORITY);

        Thread priceThread = new PriceThread(moto);
        priceThread.setPriority(Thread.MIN_PRIORITY);

        nameThread.start();
        priceThread.start();
         */

//         /* Task 2
         TransportSynchronizer transportSynchronizer = new TransportSynchronizer(car);
        Thread thread = new Thread(new NameRunnable(transportSynchronizer));
        thread.start();
        Thread thread1 = new Thread(new PriceRunnable(transportSynchronizer));
        thread1.start();
//          */

        /* Task 3
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(new NameListRunnable(car.getModels(), reentrantLock)).start();
        new Thread(new PriceListRunnable(car.getPrices(), reentrantLock)).start();
         */

        /* Task 4
        Transport[] transports = {
                car,
                moto,
                new Car("Mazda", 0),
                new Motorbike("Yamaha", 0)
        };

        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        for (Transport transport : transports)
            threadPool.submit(new MarkRunnable(transport.getMark()));
        threadPool.shutdown();
         */

        /* Task 5
        BlockingQueue queue = new ArrayBlockingQueue(2);
        String[] files = {"first_labs\\src\\main\\resources\\1.txt",
                "first_labs\\src\\main\\resources\\2.txt",
                "first_labs\\src\\main\\resources\\3.txt",
                "first_labs\\src\\main\\resources\\4.txt",
                "first_labs\\src\\main\\resources\\5.txt"};

        for (String fileName : files) {
            new Thread(new CarCreator(fileName, queue)).start();
        }

        Thread.sleep(1000);

        while (!queue.isEmpty()) {
            System.out.println("5. " + queue.take());
        }
         */
    }
}