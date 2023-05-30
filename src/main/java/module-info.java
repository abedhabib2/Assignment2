module com.example.assignment2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.assignment2 to javafx.fxml;
    exports com.example.assignment2;
    opens com.example.assignment2.Controller to javafx.fxml;
    exports com.example.assignment2.Controller;
    exports com.example.assignment2.View;
    opens com.example.assignment2.View to javafx.fxml;
    exports com.example.assignment2.Model;
    opens com.example.assignment2.Model to javafx.fxml;

}