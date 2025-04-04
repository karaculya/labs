package com.example.templatemethodapp.figures;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BouncingSquare extends BouncingShape {
    private Rectangle rectangle;

    public BouncingSquare(Pane pane) {
        super(pane);
        rectangle = new Rectangle(40, 40, Color.BLUE);
        rectangle.setLayoutX(pane.getWidth() - 40);
        rectangle.setLayoutY(pane.getHeight() - 40);
        pane.getChildren().add(rectangle);
    }

    @Override
    protected void move() {
        if (rectangle.getLayoutX() <= 0 || rectangle.getLayoutX() >= pane.getWidth() - 40) {
            dx = -dx;
        }
        if (rectangle.getLayoutY() <= 0 || rectangle.getLayoutY() >= pane.getHeight() - 40) {
            dy = -dy;
        }
        rectangle.setLayoutX(rectangle.getLayoutX() + dx);
        rectangle.setLayoutY(rectangle.getLayoutY() + dy);
    }
}
