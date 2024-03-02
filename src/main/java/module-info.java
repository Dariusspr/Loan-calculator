module app.loancalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens app.loancalculator to javafx.fxml;
    exports app.loancalculator;
}