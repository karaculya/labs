package org.example.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class OperationsDB {
    private static Connection connection;

    public OperationsDB() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("jdbc/src/main/resources/application.properties")) {
            properties.load(inputStream);
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to database has been established");
        } catch (SQLException e) {
            System.out.println("Failed to connecting with database, error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Failed to getting database properties, error: " + e.getMessage());
        }
    }

    public void sendRequest(String request) {
        try {
            Statement statement = connection.createStatement();
            statement.execute(request);
//            System.out.println("Request " + request + " success");
        } catch (SQLException e) {
            System.out.println("Failed to processing request, error: " + e.getMessage());
        }
    }

    public void groupByHaving() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(RequestConstants.GROUP_BY_HAVING);

            while (resultSet.next()) {
                String albumName = resultSet.getString("album");
                String compName = resultSet.getString("composition");
                Time duration = resultSet.getTime("duration");
                System.out.println(albumName + " | " + compName + " | " + duration);
            }
        } catch (SQLException e) {
            System.out.println("Failed to processing request, error: " + e.getMessage());
        }
    }

    public void createArtist(String name) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("INSERT INTO artists (name) VALUES ('" + name + "')");
            System.out.println("Artist added successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to creating artist, error: " + e.getMessage());
        }
    }

    public void updateArtist(String name, int id_artist) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("UPDATE artists SET name = '"
                    + name + "' WHERE id = " + id_artist);
            System.out.println("Artist updated successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to updating artist, error: " + e.getMessage());
        }
    }

    public void deleteArtist(int id_artist) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("DELETE FROM artists WHERE id = " + id_artist);

            System.out.println("Artist deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Failed to deleting artist, error: " + e.getMessage());
        }
    }
}
