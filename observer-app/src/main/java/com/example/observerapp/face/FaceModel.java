package com.example.observerapp.face;

import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class FaceModel implements FaceSubject {
    private List<FaceObserver> observers = new ArrayList<>();
    private FaceState state;

    public FaceModel() {
        this.state = new FaceState(true, true, Color.RED, false);
    }

    @Override
    public void registerObserver(FaceObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(FaceObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (FaceObserver observer : observers) {
            observer.update(state);
        }
    }

    public void toggleLeftEye() {
        boolean isOpen = getState().isLeftEyeOpen();
        state.setLeftEyeOpen(!isOpen);
        notifyObservers();
    }

    public void toggleRightEye() {
        boolean isOpen = getState().isRightEyeOpen();
        state.setRightEyeOpen(!isOpen);
        notifyObservers();
    }

    public void changeNoseColor() {
        if (state.getNoseColor().equals(Color.RED)) {
            state.setNoseColor(Color.ORANGE);
        } else if (state.getNoseColor().equals(Color.ORANGE)) {
            state.setNoseColor(Color.RED);
        } else {
            state.setNoseColor(Color.RED);
        }
        notifyObservers();
    }

    public void toggleSmile() {
        boolean isSmiling = state.isSmiling();
        state.setSmiling(!isSmiling);
        notifyObservers();
    }

    public FaceState getState() {
        return state;
    }
}
