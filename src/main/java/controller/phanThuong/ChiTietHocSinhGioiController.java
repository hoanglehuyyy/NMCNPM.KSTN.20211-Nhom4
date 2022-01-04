package controller.phanThuong;

import entity.DipHocSinhGioi;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utility.Variable;

import java.net.URL;
import java.util.ResourceBundle;

public class ChiTietHocSinhGioiController implements Initializable {

    @FXML
    private Label tieuDe;
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
    @FXML
    private Button xacNhanButton;
    @FXML
    private Button huyButton;
    @FXML
    private TextField searchText;
    @FXML
    private ComboBox comboBox;

    ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.ID_NHAN_KHAU, Variable.TEN_NHAN_KHAU, Variable.NAM_HOC);

    private DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();
    public void setDipHocSinhGioi(DipHocSinhGioi dipHocSinhGioi) {
        this.dipHocSinhGioi = dipHocSinhGioi;
        tieuDe.setText(tieuDe.getText() + " " + dipHocSinhGioi.getNam());
        namHoc.setText(String.valueOf(dipHocSinhGioi.getNam()));
        tienDacBiet.setText(String.valueOf(dipHocSinhGioi.getTienDacBiet()));
        phanThuongDacBiet.setText(String.valueOf(dipHocSinhGioi.getPhanQuaDacBiet()));
        phanThuongGioi.setText(String.valueOf(dipHocSinhGioi.getPhanQuaGioi()));
        tienGioi.setText(String.valueOf(dipHocSinhGioi.getTienGioi()));
        phanThuongKha.setText(String.valueOf(dipHocSinhGioi.getPhanQuaKha()));
        tienKha.setText(String.valueOf(dipHocSinhGioi.getTienKha()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(truongTraCuu);
    }

    public void searchClick(MouseEvent mouseEvent) {
    }

    public void xacNhanClick(MouseEvent mouseEvent) {
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void searchEnter(KeyEvent keyEvent) {
    }

}
