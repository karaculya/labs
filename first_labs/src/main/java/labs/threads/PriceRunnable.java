package main.java.labs.threads;

//Задание 2
public class PriceRunnable implements Runnable {
    private TransportSynchronizer transportSynchronizer;

    public PriceRunnable(TransportSynchronizer transportSynchronizer) {
        this.transportSynchronizer = transportSynchronizer;
    }

    @Override
    public void run() {
        try {
            while (transportSynchronizer.canPrintPrice()) {
                transportSynchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}