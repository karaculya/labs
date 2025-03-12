package main.java.labs.patterns.behavioral;

import main.java.labs.model.Car;

import java.io.Writer;

public interface Command {
    void writeCar(Car car, Writer writer);
}
