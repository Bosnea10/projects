module GUI{
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens GUI to javafx.fxml;
    exports GUI;

    exports domain;
    opens domain to javafx.base;
}