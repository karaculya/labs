package org.example.labs.exceptions;

import java.io.IOException;

//дублирования названия моделей DuplicateModelNameException (объявляемое)
public class DuplicateModelNameException extends IOException {
    public DuplicateModelNameException() {
        System.out.println("Duplicate model names");
    }
}
