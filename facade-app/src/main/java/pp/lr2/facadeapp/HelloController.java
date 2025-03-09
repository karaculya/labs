package pp.lr2.facadeapp;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton;

    private GraphicsContext gc;
    private TrafficController trafficController;
    private AnimationTimer timer;

    @FXML
    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        trafficController = new TrafficController();
    }

    @FXML
    protected void onStartButtonClick() {
        startSimulation();
    }

    private void startSimulation() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                trafficController.update();
                drawScene();
            }
        };
        timer.start();
    }

    private void drawScene() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        gc.setFill(Color.DARKGRAY);
        gc.fillRect(0, 200, canvas.getWidth(), 50);

        gc.setFill(Color.GRAY);
        gc.fillRect(250, 50, 50, 150);

        gc.setFill(trafficController.getTrafficLight().getColor());
        gc.fillOval(265, 60, 20, 20);

        double carX = trafficController.getCar().getX();
        gc.setFill(Color.BLUE);
        gc.fillRect(carX, 180, 40, 20);

        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(carX + 5, 185, 10, 10);
        gc.fillRect(carX + 25, 185, 10, 10);

        gc.setFill(Color.BLACK);
        gc.fillOval(carX + 5, 195, 10, 10);
        gc.fillOval(carX + 25, 195, 10, 10);
    }
}