module com.example.nmcnpm_team4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires lombok;

    opens view to javafx.fxml;
    opens controller to javafx.fxml;
    opens entity to javafx.fxml;

    exports view;
    exports controller;
    exports entity;
}