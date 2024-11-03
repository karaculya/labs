package main.java.labs.exceptions;

public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException() {
        System.out.println("Specifying a non-existent model name");
    }
}