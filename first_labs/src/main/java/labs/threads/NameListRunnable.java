package main.java.labs.threads;

import java.util.concurrent.locks.ReentrantLock;

//Задание 3
public class NameListRunnable implements Runnable {
    private ReentrantLock reentrantLock;
    private String[] names;

    public NameListRunnable(String[] names, ReentrantLock reentrantLock) {
        this.names = names;
        this.reentrantLock = reentrantLock;
    }

    @Override
    public void run() {
        try {
            reentrantLock.lock();

            for (String name : names) {
                System.out.println("3. " + name);
            }
        } finally {
            reentrantLock.unlock();
        }
    }
}
