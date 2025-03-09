package main.java.labs.patterns;

import main.java.labs.model.Motorbike;
import main.java.labs.model.Transport;

public class MotoFactory implements TransportFactory {
    @Override
    public Transport createInstance(String mark, int modelsSize) {
        return new Motorbike(mark, modelsSize);
    }
}
