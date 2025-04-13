package main.java.labs.patterns.behavioral;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterColumns implements ChainOfResponsibility {
    private ChainOfResponsibility next;

    @Override
    public void writeTransport(Transport transport) {
        if (transport.getSize() > 3) {
            try {
                TransportUtils.writeTransport(transport, new BufferedWriter(new FileWriter("first_labs/src/main/resources/2.txt")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (this.next != null) {
            next.writeTransport(transport);
        } else
            System.out.println(this.getClass().getSimpleName() +
                    ": Необходимо сменить исполнителя");
    }

    @Override
    public void setNextChainOfResponsibility(ChainOfResponsibility next) {
        this.next = next;
    }
}
