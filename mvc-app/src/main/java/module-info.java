module com.example.mvcapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.mvcapp to javafx.fxml;
    exports com.example.mvcapp;
    exports com.example.mvcapp.model;
    opens com.example.mvcapp.model to javafx.fxml;
    exports com.example.mvcapp.controller;
    opens com.example.mvcapp.controller to javafx.fxml;
    exports com.example.mvcapp.view;
    opens com.example.mvcapp.view to javafx.fxml;
}