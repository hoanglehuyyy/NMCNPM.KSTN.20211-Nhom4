package controller.thongKe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;

public class ThongKeNhanKhauController {

    @FXML
    private Pane mainPane;

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
