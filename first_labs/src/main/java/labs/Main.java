package main.java.labs;

import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.exceptions.NoSuchModelNameException;
import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;
import main.java.labs.threads.CarCreator;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException, InterruptedException {
        Transport car = new Car("Toyota", 0);
        Transport moto = new Motorbike("BMW", 0);

        for (int i = 0; i < 100; i++) {
            car.addNewModel("model" + i, i + 1);
            moto.addNewModel("model" + i, i + 1);
        }

        /* Task 1
        Thread nameThread = new NameThread(moto);
        nameThread.setPriority(1);

        Thread priceThread = new PriceThread(moto);
        priceThread.setPriority(10);

        nameThread.start();
        priceThread.start();
         */

         /* Task 2
         TransportSynchronizer transportSynchronizer = new TransportSynchronizer(car);
        Thread thread = new Thread(new NameRunnable(car.getSize(), transportSynchronizer));
        thread.start();
        Thread thread1 = new Thread(new PriceRunnable(car.getSize(), transportSynchronizer));
        thread1.start();
          */

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

//        /* Task 5
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
            System.out.println(queue.take());
        }
//         */
    }
}