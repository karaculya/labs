package com.example.stateapp;

import com.example.stateapp.human.Human;
import com.example.stateapp.human.HumanContext;
import com.example.stateapp.state.HappyState;
import com.example.stateapp.state.HumanState;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HumanStatesApplication extends Application {
    private HumanContext humanContext;
    private Human humanView;

    @Override
    public void start(Stage stage) {
        humanContext = new HumanContext();
        humanView = new Human();

        HumanState happyState = new HappyState(humanContext);

        humanContext.setState(happyState);
        humanView.draw(happyState);

        Button semesterBtn = new Button("Семестр");
        Button vacationBtn = new Button("Каникулы");
        Button sessionBtn = new Button("Сессия");

        semesterBtn.setOnAction(e -> {
            humanContext.changeToSleeping();
            humanView.draw(humanContext.getState());
        });

        vacationBtn.setOnAction(e -> {
            humanContext.changeToHappy();
            humanView.draw(humanContext.getState());
        });

        sessionBtn.setOnAction(e -> {
            humanContext.changeToSad();
            humanView.draw(humanContext.getState());
        });

        HBox buttons = new HBox(10, semesterBtn, vacationBtn, sessionBtn);
        buttons.setAlignment(Pos.CENTER);
        VBox root = new VBox(10, buttons, humanView);

        Scene scene = new Scene(root, 400, 400);
        stage.setTitle("Human State Pattern App");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}