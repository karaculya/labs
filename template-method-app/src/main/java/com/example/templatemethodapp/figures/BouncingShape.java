package com.example.templatemethodapp.figures;

import javafx.application.Platform;
import javafx.scene.layout.Pane;

public abstract class BouncingShape implements Runnable {
    protected Pane pane;
    protected double dx = 3;
    protected double dy = 3;

    public BouncingShape(Pane pane) {
        this.pane = pane;
    }

    protected abstract void move();

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Platform.runLater(this::move);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
