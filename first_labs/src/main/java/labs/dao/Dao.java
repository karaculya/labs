package main.java.labs.dao;

import main.java.labs.model.Transport;

import java.io.IOException;

public interface Dao {
    Transport read(String filename);
    void write(Transport transport, String filename) throws IOException;
}
