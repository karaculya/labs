package org.example;

import org.example.labs.Car;
import org.example.labs.Motorbike;
import org.example.labs.TransportUtils;
import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException {

        Car car = new Car("Toyota", 0);
        car.addNewModel("Camry", 30.0);
        car.addNewModel("Land Cruiser", 30.0);

        System.out.println(car.getPriceByModelName("Camry"));
        car.setPriceByModelName("Camry", 35.0);
        System.out.println(car.getPriceByModelName("Camry"));
        System.out.println(car.getMark());
        car.setMark("Toyota 2.0");
        System.out.println(car.getMark());
        for (int i = 0; i < car.getAllModelNames().length; i++) {
            System.out.println(
                    "Model name: " + car.getAllModelNames()[i] +
                            " price: " + car.getAllModelPrices()[i]);
        }
        System.out.println("Models size is " + car.getModelsSize());
        System.out.println("All model names:");
        TransportUtils.printAllModelNames(car);
        TransportUtils.printAllModelPrices(car);
        System.out.println("Average price is " + TransportUtils.avg(car));

        car.removeModel("Camry");
        System.out.println("All model names:");
        TransportUtils.printAllModelNames(car);

        Motorbike motorbike = new Motorbike("BMW", 0);
        motorbike.addNewModel("i5 sedan", 32.0);
        motorbike.addNewModel("m5 universal", 33.0);

        System.out.println(motorbike.getPriceByModelName("i5 sedan"));
        motorbike.setPriceByModelName("i5 sedan", 35.0);
        System.out.println(motorbike.getPriceByModelName("i5 sedan"));
        System.out.println(motorbike.getMark());
        motorbike.setMark("BMW 2.0");
        System.out.println(motorbike.getMark());
        for (int i = 0; i < motorbike.getAllModelNames().length; i++) {
            System.out.println(
                    "Model name: " + motorbike.getAllModelNames()[i] +
                            " price: " + motorbike.getAllModelPrices()[i]);
        }
        System.out.println("Models size is " + motorbike.getModelsSize());
        System.out.println("All model names:");
        TransportUtils.printAllModelNames(motorbike);
        TransportUtils.printAllModelPrices(motorbike);
        System.out.println(TransportUtils.avg(motorbike));

        motorbike.removeModel("i5 sedan");
        System.out.println("Models size is " + motorbike.getModelsSize());
        System.out.println("All model names after removing i5 sedan: ");
        TransportUtils.printAllModelNames(motorbike);
    }
}