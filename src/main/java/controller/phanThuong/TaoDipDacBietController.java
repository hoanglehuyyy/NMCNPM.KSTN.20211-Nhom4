package controller.phanThuong;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TaoDipDacBietController {

    @FXML
    private TextField nam;
    @FXML
    private TextField tenDip;
    @FXML
    private TextField tien05;
    @FXML
    private TextField phanThuong05;
    @FXML
    private TextField phanThuong614;
    @FXML
    private TextField tien614;
    @FXML
    private TextField phanThuong1517;
    @FXML
    private TextField tien1517;
    @FXML
    private TextField moTa;

    public void xacNhanClick(MouseEvent mouseEvent) {
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
