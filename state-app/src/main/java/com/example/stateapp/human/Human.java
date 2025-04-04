package com.example.stateapp.human;

import com.example.stateapp.state.HumanState;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Human extends Canvas {
    public Human() {
        super(300, 300);
    }

    public void draw(HumanState state) {
        GraphicsContext gc = this.getGraphicsContext2D();
        gc.clearRect(0, 0, getWidth(), getHeight());

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(200, 100, 200, 300);

        gc.strokeOval(175, 50, 50, 50);

        gc.strokeLine(200, 150, 150, 200);
        gc.strokeLine(200, 150, 250, 200);

        gc.strokeLine(200, 300, 170, 350);
        gc.strokeLine(200, 300, 230, 350);

        state.drawFace(gc, 100, 50, 100, 100);
    }
}
