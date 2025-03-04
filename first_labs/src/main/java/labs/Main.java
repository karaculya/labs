package main.java.labs;

import main.java.labs.exceptions.DuplicateModelNameException;
import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;
import main.java.labs.patterns.MotoFactory;
import main.java.labs.utils.TransportUtils;
import main.java.labs.patterns.Singleton;

import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        /* Task 1
        Properties properties = Singleton.getInstance();
        System.out.println(properties.getProperty("version"));
        System.out.println(properties.getProperty("name"));
        System.out.println(properties.getProperty("date"));
         */

        /* Task 2
        Transport transport = TransportUtils.createInstance("auto", 3);
        System.out.println(transport.getClass().getSimpleName());

        TransportUtils.setTransportFactory(new MotoFactory());
        transport = TransportUtils.createInstance("moto", 2);
        System.out.println(transport.getClass().getSimpleName());
         */

        /* Task 3

         */
        try {
            /* Car
            Car car = new Car("auto", 2);
            Car clonedCar = (Car) car.clone();
            System.out.println("Оригинальный автомобиль: " + car);
            System.out.println("Клонированный автомобиль: " + clonedCar);

            clonedCar.addNewModel("Niva", 50);
            System.out.println("Модели оригинального автомобиля: ");
            TransportUtils.printAllModelNames(car);
            System.out.println("Модели клонированного автомобиля: ");
            TransportUtils.printAllModelNames(clonedCar);
             */
//            /* Moto
            Motorbike moto = new Motorbike("moto", 3);
            Motorbike clonedMoto = (Motorbike) moto.clone();
            System.out.println("Оригинальный мотоцикл: " + moto);
            System.out.println("Клонированный мотоцикл: " + clonedMoto);
            clonedMoto.addNewModel("Niva", 50);
            System.out.println("Модели оригинального мотоцикла: ");
            TransportUtils.printAllModelNames(moto);
            System.out.println("Модели клонированного мотоцикла: ");
            TransportUtils.printAllModelNames(clonedMoto);
//             */
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        } catch (DuplicateModelNameException e) {
            throw new RuntimeException(e);
        }
    }
}