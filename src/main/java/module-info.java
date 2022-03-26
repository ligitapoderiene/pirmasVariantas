module com.example.knygos2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;



    opens com.example.knygos2 to javafx.fxml;
    exports com.example.knygos2;
    exports com.example.knygos2.controler;
    opens com.example.knygos2.controler to javafx.fxml;
}