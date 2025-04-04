package main.java.labs;

import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.patterns.behavioral.*;

import java.io.*;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        /* 3.1
        Car car = new Car("car model", 4);
//        /*
        ChainOfResponsibility writerTransport = new WriterRows();
        writerTransport.writeTransport(new Motorbike("moto model", 2));
        writerTransport.setNextChainOfResponsibility(new WriterColumns());
        writerTransport.writeTransport(car);
         */
        /* 3.2
        car.setPrintCommand(new CommandColumns());
        car.print(new FileWriter("first_labs/src/main/resources/3.txt"));

        car.setPrintCommand(new CommandRows());
        car.print(new FileWriter("first_labs/src/main/resources/4.txt"));
         */
        /* 3.3
        Iterator<Car.Model> iterator = car.iterator();

        while (iterator.hasNext()) {
            Car.Model model = iterator.next();
            System.out.println(model);
        }
         */
        /* 3.4
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
        /* 3.7

//        int[] array = {1, 2, 3, 4, 2, 3, 1, 2};
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("first_labs/src/main/resources/5.txt"))) {
//            oos.writeObject(array);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        if (args.length == 0) {
            System.out.println("args don't exist file path");
        } else {
            String fileName = args[0];
            int[] array = loadArrayFromFile(fileName);

            if (array == null) {
                System.out.println("failed loading array from file");
                return;
            }

            Strategy strategy = new CountIteratorStrategy();
            long startTime = System.currentTimeMillis();
            Map<Integer, Integer> result = strategy.count(array);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("Result: " + result + " for time: " + duration);

            strategy = new CountStreamStrategy();
            startTime = System.currentTimeMillis();
            result = strategy.count(array);
            endTime = System.currentTimeMillis();
            duration = endTime - startTime;
            System.out.println("Result: " + result + " for time: " + duration);
        }
         */
        /* 3.9
        Visitor visitor = new PrintVisitor();
        car.accept(visitor);
        new Motorbike("moto", 3).accept(visitor);
         */
    }

    private static int[] loadArrayFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (int[]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}