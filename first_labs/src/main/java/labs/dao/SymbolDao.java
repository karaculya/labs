package main.java.labs.dao;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.*;

public class SymbolDao implements Dao {
    @Override
    public Transport read(String filename) {
        try {
            Transport transport = TransportUtils.readTransport(new BufferedReader(new FileReader(filename)));
            System.out.println("Transport from symbols : " + transport);
            return transport;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to reading Transport from symbols. Error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void write(Transport transport, String filename) {
        try {
            TransportUtils.writeTransport(transport, new BufferedWriter(new FileWriter(filename)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
