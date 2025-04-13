package com.example.observerapp.face;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class FaceView extends Pane implements FaceObserver {
    private FaceModel model;
    private FaceController controller;
    private Canvas canvas;
    private final int faceX = 100, faceY = 100, faceWidth = 200, faceHeight = 200;
    private final int leftEyeX = 140, rightEyeX = 220, eyeY = 150, eyeWidth = 30, eyeHeight = 30;
    private final int noseX = 180, noseY = 180, noseWidth = 40, noseHeight = 40;
    private final int mouthX = 150, mouthY = 230, mouthWidth = 100, mouthHeight = 50;
    public FaceView(FaceModel model) {
        this.model = model;
        this.controller = new FaceController(model, this);
        model.registerObserver(this);

        canvas = new Canvas(400, 400);
        getChildren().add(canvas);

        canvas.setOnMouseClicked(this::handleMouseClick);

        drawFace();
    }

    private void drawFace() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        FaceState state = model.getState();

        gc.setFill(Color.BEIGE);
        gc.fillOval(faceX, faceY, faceWidth, faceHeight);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(faceX, faceY, faceWidth, faceHeight);

        drawEye(gc, leftEyeX, eyeY, state.isLeftEyeOpen());
        drawEye(gc, rightEyeX, eyeY, state.isRightEyeOpen());

        gc.setFill(state.getNoseColor());
        gc.fillOval(noseX, noseY, noseWidth, noseHeight);
        gc.setStroke(Color.BLACK);
        gc.strokeOval(noseX, noseY, noseWidth, noseHeight);

        if (state.isSmiling()) {
            gc.strokeArc(mouthX, mouthY - 20, mouthWidth, mouthHeight, 180, 180, ArcType.OPEN);
        } else {
            gc.strokeArc(mouthX, mouthY, mouthWidth, mouthHeight, 0, 180, ArcType.OPEN);
        }
    }

    private void drawEye(GraphicsContext gc, int x, int y, boolean isOpen) {
        if (isOpen) {
            gc.setFill(Color.BLACK);
            gc.fillOval(x, y, eyeWidth, eyeHeight);
            gc.setStroke(Color.BLACK);
            gc.strokeOval(x, y, eyeWidth, eyeHeight);
            gc.fillOval(x + 10, y + 10, 10, 10);
        } else {
            gc.setStroke(Color.BLACK);
            gc.strokeLine(x, y + eyeHeight/2, x + eyeWidth, y + eyeHeight/2);
        }
    }

    private void handleMouseClick(MouseEvent event) {
        controller.handleClick((int)event.getX(), (int)event.getY());
    }

    @Override
    public void update(FaceState state) {
        drawFace();
    }

    public boolean isInLeftEye(int x, int y) {
        return x >= leftEyeX && x <= leftEyeX + eyeWidth &&
                y >= eyeY && y <= eyeY + eyeHeight;
    }

    public boolean isInRightEye(int x, int y) {
        return x >= rightEyeX && x <= rightEyeX + eyeWidth &&
                y >= eyeY && y <= eyeY + eyeHeight;
    }

    public boolean isInNose(int x, int y) {
        return x >= noseX && x <= noseX + noseWidth &&
                y >= noseY && y <= noseY + noseHeight;
    }

    public boolean isInMouth(int x, int y) {
        return x >= mouthX && x <= mouthX + mouthWidth &&
                y >= mouthY - (model.getState().isSmiling() ? 20 : 0) &&
                y <= mouthY + mouthHeight;
    }
}
