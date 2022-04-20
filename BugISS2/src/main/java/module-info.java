module com.bug.bugiss {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires java.sql;

    opens com.bug.bugiss2 to javafx.fxml;
    exports com.bug.bugiss2;
    exports com.bug.bugiss2.controller;
    opens com.bug.bugiss2.controller to javafx.fxml;
}