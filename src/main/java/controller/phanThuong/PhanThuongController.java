package controller.phanThuong;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class PhanThuongController {

    @FXML
    private Pane phanThuongPane;

    public void hsgClick(MouseEvent mouseEvent) throws IOException {
        ScrollPane hsgPane = FXMLLoader.load(getClass().getResource("/view/phanThuong/hocSinhGioi.fxml"));
        phanThuongPane.getChildren().clear();
        phanThuongPane.getChildren().add(hsgPane);
    }

    public void dipDacBietOnAction(MouseEvent mouseEvent) throws IOException {
        Parent dipDacBietPane = FXMLLoader.load(getClass().getResource("/view/phanThuong/dipDacBiet.fxml"));
        phanThuongPane.getChildren().clear();
        phanThuongPane.getChildren().add(dipDacBietPane);
    }
}
