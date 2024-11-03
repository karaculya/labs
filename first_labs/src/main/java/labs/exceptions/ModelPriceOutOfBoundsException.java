package main.java.labs.exceptions;

public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException() {
        System.out.println("Was setting the incorrect model price");
    }
}