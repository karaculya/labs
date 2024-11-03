package main.java.labs.threads;

//Задание 4
public class MarkRunnable implements Runnable {
    private String mark;

    public MarkRunnable(String mark) {
        this.mark = mark;
    }

    @Override
    public void run() {
        System.out.println(mark);
    }
}
