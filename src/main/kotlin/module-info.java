module com.example.enterprisedepart {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.example.enterprisedepart to javafx.fxml;
    exports com.example.enterprisedepart;
}