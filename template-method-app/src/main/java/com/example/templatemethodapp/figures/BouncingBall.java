package com.example.templatemethodapp.figures;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BouncingBall extends BouncingShape {
    private Circle circle;

    public BouncingBall(Pane pane) {
        super(pane);
        circle = new Circle(20, Color.RED);
        circle.setLayoutX(pane.getWidth() - 20);
        circle.setLayoutY(pane.getHeight() - 20);
        pane.getChildren().add(circle);
    }

    @Override
    protected void move() {
        if (circle.getLayoutX() <= 20 || circle.getLayoutX() >= pane.getWidth() - 20) {
            dx = -dx;
        }
        if (circle.getLayoutY() <= 20 || circle.getLayoutY() >= pane.getHeight() - 20) {
            dy = -dy;
        }
        circle.setLayoutX(circle.getLayoutX() + dx);
        circle.setLayoutY(circle.getLayoutY() + dy);
    }
}