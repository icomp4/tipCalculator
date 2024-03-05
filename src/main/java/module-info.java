module org.example.tipcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tipcalculator to javafx.fxml;
    exports org.example.tipcalculator;
}