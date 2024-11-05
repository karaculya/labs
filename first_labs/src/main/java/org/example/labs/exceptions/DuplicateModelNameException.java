package org.example.labs.exceptions;

//дублирования названия моделей DuplicateModelNameException (объявляемое)
public class DuplicateModelNameException extends Exception {
    public DuplicateModelNameException() {
        System.out.println("Duplicate model names");
    }
}
