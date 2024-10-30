package org.example;

import org.example.labs.Car;
import org.example.labs.Motorbike;
import org.example.labs.Transport;
import org.example.labs.TransportUtils;
import org.example.labs.exceptions.DuplicateModelNameException;
import org.example.labs.exceptions.NoSuchModelNameException;

import java.io.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws DuplicateModelNameException, NoSuchModelNameException, IOException, ClassNotFoundException {
        String path = "C:\\Users\\sirar\\IdeaProjects\\labs\\src\\main\\resources\\file.txt";
        Transport car = new Car("Toyota", 3);
        Transport motorbike = new Motorbike("BMW", 2);

//        /* byte streams
        FileOutputStream fos = new FileOutputStream(path);
        TransportUtils.outputTransport(car, fos);
        fos.close();

        FileInputStream fis = new FileInputStream(path);
        Transport transport = TransportUtils.inputTransport(fis);
        fis.close();
        System.out.println("Was reading transport: " + transport.toString());

        if (car.equals(transport)) System.out.println("Objects getting from byte stream equals");
//         */

//        /* symbol streams
        FileWriter fw = new FileWriter(path);
        TransportUtils.writeTransport(motorbike, fw);
        fw.close();

        FileReader fr = new FileReader(path);
        transport = TransportUtils.readTransport(fr);
        fr.close();
        System.out.println("Was reading transport: " + transport.toString());
//         */

//        /* static system variable System.out
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        TransportUtils.writeTransport(motorbike, outputStreamWriter);
//        */

//        /* static system variable System.out
        System.out.println("Writeln transport's type: Car or Motorbike, and enter " +
                        "transport's mark, " +
                        "model size, " +
                        "transport's all model names " +
                        "and transport's all model prices"
                );

        transport = TransportUtils.readTransport(new InputStreamReader(System.in));
        System.out.println("Was reading transport: " + transport.toString());
//         */

//        /* serialization
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));

        objectOutputStream.writeObject(car);
        System.out.println(objectInputStream.readObject().toString());

        objectOutputStream.writeObject(motorbike);
        Transport moto = (Transport) objectInputStream.readObject();
        System.out.println(moto);
//         */
    }
}