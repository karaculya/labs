package main.java.labs.threads;

//Задание 2
public class PriceRunnable implements Runnable {
    private TransportSynchronizer transportSynchronizer;
    private int modelSize;

    public PriceRunnable(int modelSize, TransportSynchronizer transportSynchronizer) {
        this.modelSize = modelSize;
        this.transportSynchronizer = transportSynchronizer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < modelSize; i++) {
                if (transportSynchronizer.canPrintPrice())
                    transportSynchronizer.printPrice();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}