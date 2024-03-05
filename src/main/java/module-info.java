module app.loancalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.loancalculator to javafx.fxml;
    exports app.loancalculator;
    exports app.loancalculator.controllers;
    opens app.loancalculator.controllers to javafx.fxml;
    opens app.loancalculator.models to javafx.base;
}