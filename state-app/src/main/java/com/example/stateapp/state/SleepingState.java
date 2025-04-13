package com.example.stateapp.state;

import com.example.stateapp.human.HumanContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SleepingState extends HumanState {
    public SleepingState(HumanContext context) {
        super(context);
    }

    @Override
    public void drawFace(GraphicsContext gc, double x, double y, double width, double height) {
        gc.setFill(Color.BLACK);
        gc.strokeOval(190, 80, 20, 10);
        gc.strokeLine(185, 70, 195, 70);
        gc.strokeLine(205, 70, 215, 70);
        gc.fillText("Z Z Z", 175, 40);
    }

    @Override
    public void handleHappy() {
        context.setState(new HappyState(context));
    }

    @Override
    public void handleSad() {
        context.setState(new SadState(context));
    }

    @Override
    public void handleSleeping() {
    }
}
