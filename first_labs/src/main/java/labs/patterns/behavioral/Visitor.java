package main.java.labs.patterns.behavioral;

import main.java.labs.model.Car;
import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;

public interface Visitor {
    void visit(Car car);
    void visit(Motorbike motorbike);
}
