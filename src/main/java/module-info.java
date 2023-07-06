module com.boschini.demo {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.boschini.demo to javafx.fxml;
    exports com.boschini.demo;
}