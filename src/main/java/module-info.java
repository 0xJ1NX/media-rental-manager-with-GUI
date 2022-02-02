module com.example.projectjava2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projectjava2 to javafx.fxml;
    exports com.example.projectjava2;
}