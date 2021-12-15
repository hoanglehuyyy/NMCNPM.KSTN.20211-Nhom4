module com.example.quanlyhokhau {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quanlyhokhau to javafx.fxml;
    exports com.example.quanlyhokhau;
}