package main.java.labs.patterns;

import main.java.labs.model.Car;
import main.java.labs.model.Transport;

public class AutoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String mark, int modelsSize) {
        return new Car(mark, modelsSize);
    }
}
