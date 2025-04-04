package com.example.observerapp.face;

public class FaceController {
    private FaceModel model;
    private FaceView view;

    public FaceController(FaceModel model, FaceView view) {
        this.model = model;
        this.view = view;
    }

    public void handleClick(int x, int y) {
        if (view.isInLeftEye(x, y)) {
            model.toggleLeftEye();
        } else if (view.isInRightEye(x, y)) {
            model.toggleRightEye();
        } else if (view.isInNose(x, y)) {
            model.changeNoseColor();
        } else if (view.isInMouth(x, y)) {
            model.toggleSmile();
        }
    }
}
