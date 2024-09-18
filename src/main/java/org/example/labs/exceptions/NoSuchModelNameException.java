package org.example.labs.exceptions;

import java.io.IOException;

//задания несуществующего имени модели NoSuchModelNameException (объявляемое)
public class NoSuchModelNameException extends IOException {
    public NoSuchModelNameException() {
        System.out.println("Specifying a non-existent model name");
    }
}
