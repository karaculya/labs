package main.java.labs.patterns.behavioral;

import main.java.labs.model.Car;
import main.java.labs.utils.TransportUtils;

import java.io.Writer;

public class CommandColumns implements Command {
    @Override
    public void writeCar(Car car, Writer writer) {
        TransportUtils.writeTransport(car, writer);
    }
}
