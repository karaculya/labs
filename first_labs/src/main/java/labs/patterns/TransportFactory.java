package main.java.labs.patterns;

import main.java.labs.model.Transport;

public interface TransportFactory {
    Transport createInstance(String mark, int modelsSize);
}
