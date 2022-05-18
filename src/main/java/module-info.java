module com.example.hm3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.hm3 to javafx.fxml;
    exports com.example.hm3;
}