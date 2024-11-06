package main.java.labs.threads;

import java.util.concurrent.locks.ReentrantLock;

//Задание 3
public class PriceListRunnable implements Runnable {
    private ReentrantLock reentrantLock;
    private double[] prices;

    public PriceListRunnable(double[] prices, ReentrantLock reentrantLock) {
        this.prices = prices;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();

            for (double price : prices) {
                System.out.println("3. " + price);
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
