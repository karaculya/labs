package com.example.observerapp;

import com.example.observerapp.face.FaceModel;
import com.example.observerapp.face.FaceView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class FaceApplication extends Application {
    @Override
    public void start(Stage stage) {
        stage.setScene(new Scene(new FaceView(new FaceModel()), 400, 400));
        stage.setTitle("рожица");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}