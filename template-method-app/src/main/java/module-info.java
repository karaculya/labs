module com.example.templatemethodapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.templatemethodapp to javafx.fxml;
    exports com.example.templatemethodapp;
    exports com.example.templatemethodapp.figures;
    opens com.example.templatemethodapp.figures to javafx.fxml;
}