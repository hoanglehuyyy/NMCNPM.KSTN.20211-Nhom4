package controller.phanThuong;

import entity.ChiTietDipHocSinhGioi;
import entity.DipHocSinhGioi;
import entity.NhanKhauHocSinhGioi;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Message;
import utility.Utility;
import utility.Variable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HsgDanhSachNhanThuongController implements Initializable {

    @FXML
    private Label tieuDe;
    @FXML
    private TableView<NhanKhauHocSinhGioi> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhauHocSinhGioi, Integer> idCol;
    @FXML
    private TableColumn<NhanKhauHocSinhGioi, String> tenCol;
    @FXML
    private TableColumn<NhanKhauHocSinhGioi, String> thanhTichCol;
    @FXML
    private TableColumn<NhanKhauHocSinhGioi, String> truongCol;
    @FXML
    private TableColumn<NhanKhauHocSinhGioi, Integer> lopCol;
    @FXML
    private TableColumn<NhanKhauHocSinhGioi, Boolean> kiemTraCol;
    @FXML
    private TextField searchText;
    @FXML
    private ComboBox comboBox;

    private ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.ID_NHAN_KHAU, Variable.TEN_NHAN_KHAU, Variable.NAM_HOC);

    private DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();
    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();

    public void setDanhSach(DipHocSinhGioi dip) {
        dipHocSinhGioi = dip;
        tieuDe.setText(tieuDe.getText() + dip.getNam());
        nhanKhauTable.setItems(hocSinhGioiImpl.bangNhanThuong(dip.getIdDip()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBox.setItems(truongTraCuu);
        idCol.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        tenCol.setCellValueFactory(new PropertyValueFactory<>("tenNhanKhau"));
        truongCol.setCellValueFactory(new PropertyValueFactory<>("truongHoc"));
        lopCol.setCellValueFactory(new PropertyValueFactory<>("lopHoc"));
        thanhTichCol.setCellValueFactory(new PropertyValueFactory<>("thanhTich"));
        kiemTraCol.setCellFactory(param -> {
            CheckBoxTableCell<NhanKhauHocSinhGioi, Boolean> cell = new CheckBoxTableCell<>();
            return cell;
        });
        kiemTraCol.setCellValueFactory(param -> {
            NhanKhauHocSinhGioi nkhsg = param.getValue();
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(nkhsg.getKiemTra());

            booleanProperty.addListener((observableValue, aBoolean, t1) -> {
                nkhsg.setKiemTra(t1);
                hocSinhGioiImpl.kiemTraTraoThuong(dipHocSinhGioi.getIdDip(), nkhsg.getIdNhanKhau(), t1);
            });
            return booleanProperty;
        });
    }

    public void searchClick(MouseEvent mouseEvent) {
    }

    public void quayLaiClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void searchEnter(KeyEvent keyEvent) {
    }

    @SneakyThrows
    public void suaMinhChung(ActionEvent actionEvent) {
        NhanKhauHocSinhGioi n = nhanKhauTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/suaMinhChungNhanThuong.fxml"));
        Parent p = loader.load();
        SuaMinhChungNhanThuongController s = loader.getController();
        ChiTietDipHocSinhGioi chiTietDipHocSinhGioi = new ChiTietDipHocSinhGioi();
        chiTietDipHocSinhGioi.setIdDip(dipHocSinhGioi.getIdDip());
        chiTietDipHocSinhGioi.setIdNhanKhau(n.getIdNhanKhau());
        chiTietDipHocSinhGioi.setTruong(n.getTruongHoc());
        chiTietDipHocSinhGioi.setLop(n.getLopHoc());
        chiTietDipHocSinhGioi.setNhom(NhanKhauHocSinhGioi.getNhom(n.getThanhTich()));
        chiTietDipHocSinhGioi.setMinhChung(n.getMinhChung());
        chiTietDipHocSinhGioi.setKiemTra(n.getKiemTra());
        s.setThongTin(dipHocSinhGioi.getNam(), chiTietDipHocSinhGioi, n.getTenNhanKhau());
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            nhanKhauTable.setItems(hocSinhGioiImpl.bangNhanThuong(dipHocSinhGioi.getIdDip()));
        });
    }

    public void xoaMinhChung(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(Message.xacNhanXoaMinhChung);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            NhanKhauHocSinhGioi nkhsg = nhanKhauTable.getSelectionModel().getSelectedItem();
            hocSinhGioiImpl.xoaMinhChung(dipHocSinhGioi.getIdDip(), nkhsg.getIdNhanKhau());
            nhanKhauTable.setItems(hocSinhGioiImpl.bangNhanThuong(dipHocSinhGioi.getIdDip()));
        }
    }
}
