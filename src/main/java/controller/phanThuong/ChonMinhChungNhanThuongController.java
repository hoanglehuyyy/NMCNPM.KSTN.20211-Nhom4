package controller.phanThuong;

import entity.DipHocSinhGioi;
import entity.NhanKhauHokhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Utility;
import utility.Variable;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ChonMinhChungNhanThuongController implements Initializable {

    @FXML
    public Label nam;
    @FXML
    private TextField searchText;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<NhanKhauHokhau> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhauHokhau, Integer> idnhanKhaucol;
    @FXML
    private TableColumn<NhanKhauHokhau, String> tenNhanKhauCol;
    @FXML
    private TableColumn<NhanKhauHokhau, Date> ngaySinhCol;
    @FXML
    private TableColumn<NhanKhauHokhau, String> diaChiCol;

    private ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.TEN_NHAN_KHAU, Variable.NGAY_SINH, Variable.DIA_CHI);
    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();
    private ObservableList<NhanKhauHokhau> danhSachNhanKhau = null;
    private DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();

    public void setDip(DipHocSinhGioi dip) {
        this.dipHocSinhGioi = dip;
        nam.setText(String.valueOf(dipHocSinhGioi.getNam()));
        danhSachNhanKhau = hocSinhGioiImpl.bangThemMinhChung(dipHocSinhGioi.getIdDip());
        nhanKhauTable.setItems(danhSachNhanKhau);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(truongTraCuu);
        idnhanKhaucol.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        tenNhanKhauCol.setCellValueFactory(new PropertyValueFactory<>("tenNhanKhau"));
        ngaySinhCol.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        diaChiCol.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    }

    public void searchClick(MouseEvent mouseEvent) {

    }

    public void searchEnter(KeyEvent keyEvent) {
    }


    public void truongTraCuu(ActionEvent actionEvent) {

    }

    @SneakyThrows
    public void themMinhChung(MouseEvent mouseEvent) {
        NhanKhauHokhau nhanKhauHokhau = nhanKhauTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/minhChungNhanThuong.fxml"));
        Parent p = loader.load();
        MinhChungNhanThuongController m = loader.getController();
        m.setThongTin(dipHocSinhGioi, nhanKhauHokhau.getIdNhanKhau(), nhanKhauHokhau.getTenNhanKhau());
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            nhanKhauTable.setItems(hocSinhGioiImpl.bangThemMinhChung(dipHocSinhGioi.getIdDip()));
        });
    }

    public void quayLaiClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
