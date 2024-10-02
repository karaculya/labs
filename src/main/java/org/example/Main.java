package org.example;

import com.sun.jdi.connect.Transport;
import org.example.labs.Car;
import org.example.labs.TransportUtils;
import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, FileNotFoundException {

        Car car = new Car("Toyota", 0);
        car.addNewModel("Camry", 30.0);
        car.addNewModel("Land Cruiser", 30.0);

        String FILE_PATH = "C:\\Users\\sirar\\IdeaProjects\\labs\\src\\main\\resources\\file.txt";

        TransportUtils.outputTransport(car, new FileOutputStream(FILE_PATH));
        TransportUtils.inputTransport(new FileInputStream(FILE_PATH));
    }
}