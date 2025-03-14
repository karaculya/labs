package main.java.labs.threads;

//Задание 2
public class NameRunnable implements Runnable {
    private TransportSynchronizer transportSynchronizer;

    public NameRunnable(TransportSynchronizer transportSynchronizer) {
        this.transportSynchronizer = transportSynchronizer;
    }

    @Override
    public void run() {
        try {
            while (transportSynchronizer.canPrintModel())
                transportSynchronizer.printModel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}