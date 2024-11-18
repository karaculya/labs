package org.example.calculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorApplication extends Application {
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CalculatorApplication.class.getResource("calc.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            scene.setFill(Color.BLACK);
            stage.setTitle("Calculator");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}