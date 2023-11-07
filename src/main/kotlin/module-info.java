module com.example.enterprisedepart {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;
    requires exposed.core;
    requires org.postgresql.jdbc;
    requires java.sql;
    requires exposed.jdbc;
    requires exposed.dao;
    requires exposed.java.time;

    opens com.example.enterprisedepart.models to javafx.base;
    opens com.example.enterprisedepart to javafx.fxml;
    exports com.example.enterprisedepart;
}