package main.java.labs;

import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.patterns.behavioral.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
//        /* 3.1
        Car car = new Car("car model", 4);
//        /*
        ChainOfResponsibility writerTransport = new WriterRows();
        writerTransport.writeTransport(new Motorbike("moto model", 2));
        writerTransport.setNextChainOfResponsibility(new WriterColumns());
        writerTransport.writeTransport(car);
//         */
//        /* 3.2
        car.setPrintCommand(new CommandColumns());
        car.print(new FileWriter("first_labs/src/main/resources/3.txt"));

        car.setPrintCommand(new CommandRows());
        car.print(new FileWriter("first_labs/src/main/resources/4.txt"));
//         */
//        /* 3.3
        Iterator<Car.Model> iterator = car.iterator();

        while (iterator.hasNext()) {
            Car.Model model = iterator.next();
            System.out.println(model);
        }
//         */
//        /* 3.4
        try {
            System.out.println("Original Car: " + car);
            Car.Memento memento = car.createMemento();
            car.setMark("Honda");
            System.out.println("Modified Car: " + car);

            car.setMemento(memento);
            System.out.println("Restored Car: " + car);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//         */
//        /* 3.5 - 3.8
//        todo
//         */
//        /* 3.9
        Visitor visitor = new PrintVisitor();
        car.accept(visitor);
        new Motorbike("moto", 3).accept(visitor);
//         */
    }
}