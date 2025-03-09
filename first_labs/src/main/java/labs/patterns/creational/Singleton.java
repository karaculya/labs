package main.java.labs.patterns.creational;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Singleton {
    private static Properties INSTANCE;
    private static final String path = "first_labs/src/main/resources/config.properties";

    private Singleton() {
    }

    public static Properties getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Properties();
            try {
                INSTANCE.load(new FileInputStream(path));
            } catch (IOException e) {
                System.out.println("Failed to loading properties, error: " + e.getMessage());
                throw new RuntimeException(e);
            }
        }
        return INSTANCE;
    }

    public static void setInstance(Properties properties) {
        INSTANCE = properties;
    }
}
