package controller.thongKe;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class ThongKeController {

    @FXML
    private Pane mainPane;

    public void hoKhauModeClick(MouseEvent mouseEvent) throws IOException {
        Parent hoKhauPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeHoKhau.fxml"));
        mainPane.getChildren().add(hoKhauPane);
    }

    public void nhanKhauModeClick(MouseEvent mouseEvent) throws IOException {
        Pane nhanKhauPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeNhanKhau.fxml"));
        mainPane.getChildren().add(nhanKhauPane);
    }

    public void phanThuongModeClick(MouseEvent mouseEvent) throws IOException {
        Pane phanThuongPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeChon.fxml"));
        mainPane.getChildren().add(phanThuongPane);
    }
}

