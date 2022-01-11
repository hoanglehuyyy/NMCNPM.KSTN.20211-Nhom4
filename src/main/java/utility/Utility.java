package utility;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Utility {
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }
    public static boolean isPostive(String str) {
        return str.matches("\\d+(\\.\\d+)?");
    }
    public static boolean isPostiveInteger(String str) {
        return str.matches("\\d+");
    }

    public static Stage setStage(Parent p) {
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setX(20);
        stage.setY(20);
        stage.setResizable(false);
        stage.show();
        return stage;
    }

    public static Stage setStage(Parent p, float x, float y) {
        Scene scene = new Scene(p, x, y);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setX(10);
        stage.setY(10);
        stage.setResizable(false);
        stage.show();
        return stage;
    }
}
