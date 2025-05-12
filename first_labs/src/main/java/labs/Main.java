package main.java.labs;

import main.java.labs.dao.ByteDao;
import main.java.labs.dao.Dao;
import main.java.labs.dao.SymbolDao;
import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
//        /* 4.2
        Transport car = new Car("car model", 4);
        Transport motorbike = new Motorbike("moto model", 3);
        String FILENAME_1 = "C:\\Users\\sirar\\IdeaProjects\\labs\\first_labs\\src\\main\\resources\\1.txt";
        String FILENAME_2 = "C:\\Users\\sirar\\IdeaProjects\\labs\\first_labs\\src\\main\\resources\\2.txt";

        Dao dao = new SymbolDao();
        dao = new ByteDao();
        dao.write(car, FILENAME_1);
        dao.read(FILENAME_1);
        dao.write(motorbike, FILENAME_2);
        dao.read(FILENAME_2);
//         */
    }
}