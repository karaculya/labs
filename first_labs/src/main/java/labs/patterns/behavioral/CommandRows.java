package main.java.labs.patterns.behavioral;

import main.java.labs.model.Car;
import main.java.labs.utils.TransportUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class CommandRows implements Command {
    @Override
    public void writeCar(Car car, Writer writer) {
        try {
            TransportUtils.writeTransportToRow(car, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
