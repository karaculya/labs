package com.example.stateapp.state;

import com.example.stateapp.human.HumanContext;
import javafx.scene.canvas.GraphicsContext;

public abstract class HumanState {
    protected HumanContext context;

    public HumanState(HumanContext context) {
        this.context = context;
    }

    public abstract void drawFace(GraphicsContext gc, double x, double y, double width, double height);
    public abstract void handleHappy();
    public abstract void handleSad();
    public abstract void handleSleeping();
}
