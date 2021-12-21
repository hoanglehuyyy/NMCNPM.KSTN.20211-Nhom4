package controller.phanThuong;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TaoDipHocSinhGioiController {

    @FXML
    private TextField namHoc;
    @FXML
    private TextField tienDacBiet;
    @FXML
    private TextField phanThuongDacBiet;
    @FXML
    private TextField phanThuongGioi;
    @FXML
    private TextField tienGioi;
    @FXML
    private TextField phanThuongKha;
    @FXML
    private TextField tienKha;
    @FXML
    private TextField moTa;

    public void xacNhanClick(MouseEvent mouseEvent) {

    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
