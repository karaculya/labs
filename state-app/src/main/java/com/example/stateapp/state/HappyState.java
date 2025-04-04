package com.example.stateapp.state;

import com.example.stateapp.human.HumanContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class HappyState extends HumanState {

    public HappyState(HumanContext context) {
        super(context);
    }

    @Override
    public void drawFace(GraphicsContext gc, double x, double y, double width, double height) {
        gc.fillOval(185, 60, 10, 15);
        gc.fillOval(205, 60, 10, 15);
        gc.strokeArc(190, 80, 20, 10, 180, 180, ArcType.OPEN);
    }

    @Override
    public void handleHappy() {
    }

    @Override
    public void handleSad() {
        context.setState(new SadState(context));
    }

    @Override
    public void handleSleeping() {
        context.setState(new SleepingState(context));
    }
}
