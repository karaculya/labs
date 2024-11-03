package main.java.labs.threads;

//Задание 2
public class NameRunnable implements Runnable {
    private TransportSynchronizer transportSynchronizer;
    private int modelSize;

    public NameRunnable(int modelSize, TransportSynchronizer transportSynchronizer) {
        this.modelSize = modelSize;
        this.transportSynchronizer = transportSynchronizer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < modelSize; i++) {
                if (transportSynchronizer.canPrintModel())
                    transportSynchronizer.printModel();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}