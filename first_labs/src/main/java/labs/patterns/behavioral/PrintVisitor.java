package main.java.labs.patterns.behavioral;

import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.utils.TransportUtils;

import java.io.IOException;
import java.io.PrintWriter;

public class PrintVisitor implements Visitor {
    @Override
    public void visit(Car car) {
        try {
            TransportUtils.writeTransportToRow(car, new PrintWriter(System.out));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void visit(Motorbike motorbike) {
        TransportUtils.writeTransport(motorbike, new PrintWriter(System.out));
    }
}
