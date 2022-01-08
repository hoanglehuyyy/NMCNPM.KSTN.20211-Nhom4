package controller.thongKe;

import entity.DipHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepository;
import repository.HocSinhGioiRepositoryImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKeHocSinhGioiController implements Initializable {

    @FXML
    private Pane hocSinhGioiMainPane;

    @FXML
    private Pane thongKeChiTietHSG;

    @FXML
    private TableView<DipHocSinhGioi> bangHSG;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> idDip;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> nam;
    @FXML
    private TableColumn<DipHocSinhGioi, String> moTa;

    @FXML
    private TextField namTimNoiDung;

    @FXML
    private Label namHoc;

    @FXML
    private Label dacBiet;

    @FXML
    private Label dacBietTien;

    @FXML
    private Label gioi;

    @FXML
    private Label gioiTien;

    @FXML
    private Label kha;

    @FXML
    private Label khaTien;

    @FXML
    private Label dacBietNguoi;

    @FXML
    private Label gioiNguoi;

    @FXML
    private Label khaNguoi;

    @FXML
    private Label tongTienHSG;

    @FXML
    private Label nguoiDaTraoHSG;

    @FXML
    private Label hoDaTraoHSG;

    @FXML
    private Label nguoiChuaTraoHSG;

    @FXML
    private Label hoChuaTraoHSG;

    HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thongKeChiTietHSG.setVisible(false);

        idDip.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        nam.setCellValueFactory(new PropertyValueFactory<>("nam"));
        moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        ObservableList<DipHocSinhGioi> dipHocSinhGioi = hocSinhGioiImpl.bangDipHocSinhGioi();
        bangHSG.setItems(dipHocSinhGioi);
    }

    public void namTim() throws SQLException {
        if (namTimNoiDung.getText().isBlank()) {
            ObservableList<DipHocSinhGioi> dipHocSinhGioi = hocSinhGioiImpl.bangDipHocSinhGioi();
            bangHSG.setItems(dipHocSinhGioi);
        } else {
            try {
                ObservableList<DipHocSinhGioi> dipHocSinhGioi = hocSinhGioiImpl.timNam(Integer.valueOf(namTimNoiDung.getText()));
                bangHSG.setItems(dipHocSinhGioi);
            } catch(Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tìm kiếm năm học");
                alert.setHeaderText(null);
                alert.setContentText("Năm học có kiểu là số tự nhiên");
                alert.showAndWait();
            }
        }
    }

    @SneakyThrows
    public void backClick() {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeChon.fxml"));
        hocSinhGioiMainPane.getChildren().clear();
        hocSinhGioiMainPane.getChildren().add(pane);
    }

    @SneakyThrows
    public void xemThongKe(MouseEvent mouseEvent) {
        DipHocSinhGioi dipHocSinhGioi = bangHSG.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            if (dipHocSinhGioi != null) {
                thongKeChiTietHSG.setVisible(true);
                namHoc.setText(dipHocSinhGioi.getNam().toString());
                dacBiet.setText(dipHocSinhGioi.getPhanQuaDacBiet());
                dacBietTien.setText(dipHocSinhGioi.getTienDacBiet().toString());
                gioi.setText(dipHocSinhGioi.getPhanQuaGioi());
                gioiTien.setText(dipHocSinhGioi.getTienGioi().toString());
                kha.setText(dipHocSinhGioi.getPhanQuaKha());
                khaTien.setText(dipHocSinhGioi.getTienKha().toString());
                dacBietNguoi.setText(String.valueOf(hocSinhGioiImpl.dacBietNguoi(dipHocSinhGioi.getIdDip())));
                gioiNguoi.setText(String.valueOf(hocSinhGioiImpl.gioiNguoi(dipHocSinhGioi.getIdDip())));
                khaNguoi.setText(String.valueOf(hocSinhGioiImpl.khaNguoi(dipHocSinhGioi.getIdDip())));

                float tongTienTinh = dipHocSinhGioi.getTienDacBiet() * hocSinhGioiImpl.dacBietNguoi(dipHocSinhGioi.getIdDip()) +
                        dipHocSinhGioi.getTienGioi() * hocSinhGioiImpl.gioiNguoi(dipHocSinhGioi.getIdDip()) +
                        dipHocSinhGioi.getTienKha() * hocSinhGioiImpl.khaNguoi(dipHocSinhGioi.getIdDip());
                tongTienHSG.setText(String.valueOf(tongTienTinh));

                nguoiDaTraoHSG.setText(String.valueOf(hocSinhGioiImpl.nguoiDaTrao(dipHocSinhGioi.getIdDip())));
                nguoiChuaTraoHSG.setText(String.valueOf(hocSinhGioiImpl.nguoiChuaTrao(dipHocSinhGioi.getIdDip())));
                hoDaTraoHSG.setText(String.valueOf(hocSinhGioiImpl.hoDaTrao(dipHocSinhGioi.getIdDip())));
                hoChuaTraoHSG.setText(String.valueOf(hocSinhGioiImpl.hoChuaTrao(dipHocSinhGioi.getIdDip())));
            }
        }
    }
}
