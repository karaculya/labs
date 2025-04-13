package com.example.stateapp.state;

import com.example.stateapp.human.HumanContext;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class SadState extends HumanState {
    public SadState(HumanContext context) {
        super(context);
    }

    @Override
    public void drawFace(GraphicsContext gc, double x, double y, double width, double height) {
        gc.strokeLine(185, 70, 190, 75);
        gc.strokeLine(205, 70, 210, 75);
        gc.strokeArc(190, 85, 20, 10, 0, 180, ArcType.OPEN);
    }

    @Override
    public void handleHappy() {
        context.setState(new HappyState(context));
    }

    @Override
    public void handleSad() {
    }

    @Override
    public void handleSleeping() {
        context.setState(new SleepingState(context));
    }
}
