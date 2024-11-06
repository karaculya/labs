package main.java.labs.exceptions;

//задания несуществующего имени модели NoSuchModelNameException (объявляемое)
public class NoSuchModelNameException extends Exception {
    public NoSuchModelNameException() {
        System.out.println("Specifying a non-existent model name");
    }
}
