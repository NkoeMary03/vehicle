module com.example.vehiclesytem {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires java.desktop;

    opens com.example.vehiclesytem to javafx.fxml;
    exports com.example.vehiclesytem;
    exports com.example.vehiclesystem;
    opens com.example.vehiclesystem to javafx.fxml;
}