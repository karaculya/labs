module com.example.stateapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.stateapp to javafx.fxml;
    exports com.example.stateapp;
}