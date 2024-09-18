package org.example.labs.exceptions;

//задание неверной цены модели ModelPriceOutOfBoundsException (необъявляемое)
public class ModelPriceOutOfBoundsException extends RuntimeException {
    public ModelPriceOutOfBoundsException() {
        System.out.println("Was setting the incorrect model price");
    }
}
