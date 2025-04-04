package com.example.templatemethodapp;

import com.example.templatemethodapp.figures.BouncingBall;
import com.example.templatemethodapp.figures.BouncingShape;
import com.example.templatemethodapp.figures.BouncingSquare;
import com.example.templatemethodapp.figures.BouncingStar;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BouncingShapesApp extends Application {
    private Pane pane;
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void start(Stage stage) {
        pane = new Pane();
        pane.setPrefSize(800, 600);

        Button startButton = new Button("Пуск");
        Button closeButton = new Button("Закрыть");
        ChoiceBox<String> shapeSelector = new ChoiceBox<>();

        shapeSelector.getItems().addAll("Круг", "Квадрат", "Звезда");
        shapeSelector.setValue("Круг");

        startButton.setLayoutX(10);
        startButton.setLayoutY(10);

        closeButton.setLayoutX(70);
        closeButton.setLayoutY(10);

        shapeSelector.setLayoutX(150);
        shapeSelector.setLayoutY(10);

        startButton.setOnAction(e -> startNewShape(shapeSelector.getValue()));
        closeButton.setOnAction(e -> stage.close());

        pane.getChildren().addAll(startButton, closeButton, shapeSelector);

        Scene scene = new Scene(pane);
        stage.setTitle("Bouncing Shapes");
        stage.setScene(scene);
        stage.show();
    }

    private void startNewShape(String shapeType) {
        BouncingShape shape = switch (shapeType) {
            case "Квадрат" -> new BouncingSquare(pane);
            case "Звезда" -> new BouncingStar(pane);
            default -> new BouncingBall(pane);
        };
        executorService.execute(shape);
    }

    public static void main(String[] args) {
        launch();
    }
}