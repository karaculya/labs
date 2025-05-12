package main.java.labs.dao;

import main.java.labs.model.Transport;
import main.java.labs.utils.TransportUtils;

import java.io.*;

public class ByteDao implements Dao {
    @Override
    public Transport read(String filename) {
        try {
            Transport transport = TransportUtils.inputTransport(new FileInputStream(filename));
            System.out.println("Transport from bytes : " + transport);
            return transport;
        } catch (FileNotFoundException e) {
            System.out.println("Failed to reading Transport from bytes. Error : " + e.getMessage());
        }
        return null;
    }

    @Override
    public void write(Transport transport, String filename) {
        try {
            TransportUtils.outputTransport(transport, new FileOutputStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
