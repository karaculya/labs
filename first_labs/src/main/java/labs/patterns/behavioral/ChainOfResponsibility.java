package main.java.labs.patterns.behavioral;

import main.java.labs.model.Transport;

public interface ChainOfResponsibility {
    abstract void writeTransport(Transport transport);

    void setNextChainOfResponsibility(ChainOfResponsibility next);
}
