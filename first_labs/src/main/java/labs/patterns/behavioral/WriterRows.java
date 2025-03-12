package main.java.labs.patterns.behavioral;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.FileWriter;
import java.io.IOException;

public class WriterRows implements ChainOfResponsibility {
    private ChainOfResponsibility next;

    @Override
    public void writeTransport(Transport transport) {
        if (transport.getSize() <= 3) {
            try (FileWriter writer = new FileWriter("first_labs/src/main/resources/1.txt")) {
                TransportUtils.writeTransportToRow(transport, writer);
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
