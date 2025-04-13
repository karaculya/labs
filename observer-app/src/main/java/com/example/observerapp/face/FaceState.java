package com.example.observerapp.face;

import javafx.scene.paint.Color;

public class FaceState {
    private boolean leftEyeOpen;
    private boolean rightEyeOpen;
    private Color noseColor;
    private boolean isSmiling;

    public FaceState(boolean leftEyeOpen, boolean rightEyeOpen, Color noseColor, boolean isSmiling) {
        this.leftEyeOpen = leftEyeOpen;
        this.rightEyeOpen = rightEyeOpen;
        this.noseColor = noseColor;
        this.isSmiling = isSmiling;
    }

    public boolean isLeftEyeOpen() {
        return leftEyeOpen;
    }

    public void setLeftEyeOpen(boolean leftEyeOpen) {
        this.leftEyeOpen = leftEyeOpen;
    }

    public boolean isRightEyeOpen() {
        return rightEyeOpen;
    }

    public void setRightEyeOpen(boolean rightEyeOpen) {
        this.rightEyeOpen = rightEyeOpen;
    }

    public Color getNoseColor() {
        return noseColor;
    }

    public void setNoseColor(Color noseColor) {
        this.noseColor = noseColor;
    }

    public boolean isSmiling() {
        return isSmiling;
    }

    public void setSmiling(boolean smiling) {
        isSmiling = smiling;
    }
}
