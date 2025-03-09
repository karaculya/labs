module pp.lr2.facadeapp {
    requires javafx.controls;
    requires javafx.fxml;
            
                        requires org.kordamp.bootstrapfx.core;
            
    opens pp.lr2.facadeapp to javafx.fxml;
    exports pp.lr2.facadeapp;
}