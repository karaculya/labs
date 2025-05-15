package com.example.mvcapp;

import com.example.mvcapp.controller.Controller;
import com.example.mvcapp.model.Model;
import com.example.mvcapp.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

public class MvcApp extends Application {
    @Override
    public void start(Stage stage) {
        View view = new View(stage);
        Model model = new Model();
        Controller controller = new Controller(model, view);
        view.show();
    }

    public static void main(String[] args) {
        launch();
    }
}