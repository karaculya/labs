module com.example.observerapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.observerapp to javafx.fxml;
    exports com.example.observerapp;
}