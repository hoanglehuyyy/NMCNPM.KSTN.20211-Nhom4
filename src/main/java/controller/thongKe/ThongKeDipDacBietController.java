package controller.thongKe;

import entity.DipDacBiet;
import entity.DipHocSinhGioi;
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
import repository.DipDacBietRepository;
import repository.DipDacBietRepositoryImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKeDipDacBietController implements Initializable {

    @FXML
    private Pane dipDacBietMainPane;

    @FXML
    private Pane thongKeChiTietDB;

    @FXML
    private TableView<DipDacBiet> bangDB;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> idDip;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> nam;
    @FXML
    private TableColumn<DipHocSinhGioi, String> ten;
    @FXML
    private TableColumn<DipHocSinhGioi, String> moTa;

    @FXML
    private TextField namTimNoiDung;

    @FXML
    private TextField tenDipNoiDung;

    @FXML
    private Label namLabel;

    @FXML
    private Label tenLabel;

    @FXML
    private Label t05;

    @FXML
    private Label t05Tien;

    @FXML
    private Label t614;

    @FXML
    private Label t614Tien;

    @FXML
    private Label t1517;

    @FXML
    private Label t1517Tien;

    @FXML
    private Label t05Nguoi;

    @FXML
    private Label t614Nguoi;

    @FXML
    private Label t1517Nguoi;

    @FXML
    private Label tongTienDB;

    @FXML
    private Label nguoiDaTraoDB;

    @FXML
    private Label hoDaTraoDB;

    @FXML
    private Label nguoiChuaTraoDB;

    @FXML
    private Label hoChuaTraoDB;

    DipDacBietRepository dipDacBietRepositoryImpl = new DipDacBietRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        thongKeChiTietDB.setVisible(false);

        idDip.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        nam.setCellValueFactory(new PropertyValueFactory<>("nam"));
        ten.setCellValueFactory(new PropertyValueFactory<>("ten"));
        moTa.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.bangDipDacBiet();
        bangDB.setItems(dipDacBiet);
    }

    public void tim() throws SQLException {
        if (namTimNoiDung.getText().isBlank() && tenDipNoiDung.getText().isBlank()) {
            ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.bangDipDacBiet();
            bangDB.setItems(dipDacBiet);
        } else if (namTimNoiDung.getText().isBlank() || tenDipNoiDung.getText().isBlank()){
            if (namTimNoiDung.getText().isBlank()) {
                ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.tenDipDacBiet(tenDipNoiDung.getText());
                bangDB.setItems(dipDacBiet);
            } else {
                try {
                    ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.namDipDacBiet(Integer.valueOf(namTimNoiDung.getText()));
                    bangDB.setItems(dipDacBiet);
                } catch(Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Tìm kiếm dịp đặc biệt");
                    alert.setHeaderText(null);
                    alert.setContentText("Năm học có kiểu là số tự nhiên");
                    alert.showAndWait();
                }
            }
        } else {
            try {
                ObservableList<DipDacBiet> dipDacBiet = dipDacBietRepositoryImpl.namTenDipDacBiet(Integer.valueOf(namTimNoiDung.getText()), tenDipNoiDung.getText());
                bangDB.setItems(dipDacBiet);
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Tìm kiếm dịp đặc biệt");
                alert.setHeaderText(null);
                alert.setContentText("Năm học có kiểu là số tự nhiên");
                alert.showAndWait();
            }
        }
    }

    @SneakyThrows
    public void backClick() {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeChon.fxml"));
        dipDacBietMainPane.getChildren().clear();
        dipDacBietMainPane.getChildren().add(pane);
    }

    @SneakyThrows
    public void xemThongKe(MouseEvent mouseEvent) {
        DipDacBiet dipDacBiet = bangDB.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            if (dipDacBiet != null) {
                thongKeChiTietDB.setVisible(true);
                namLabel.setText(dipDacBiet.getNam().toString());
                tenLabel.setText(dipDacBiet.getTen());
                t05.setText(dipDacBiet.getPhanQua05());
                t614.setText(dipDacBiet.getPhanQua614());
                t1517.setText(dipDacBiet.getPhanQua1517());
                t05Tien.setText(dipDacBiet.getTien05().toString());
                t614Tien.setText(dipDacBiet.getTien614().toString());
                t1517Tien.setText(dipDacBiet.getTien1517().toString());
                t05Nguoi.setText(String.valueOf(dipDacBietRepositoryImpl.t05Nguoi(dipDacBiet.getIdDip())));
                t614Nguoi.setText(String.valueOf(dipDacBietRepositoryImpl.t614Nguoi(dipDacBiet.getIdDip())));
                t1517Nguoi.setText(String.valueOf(dipDacBietRepositoryImpl.t1517Nguoi(dipDacBiet.getIdDip())));

                float tongTienTinh = dipDacBiet.getTien05() * dipDacBietRepositoryImpl.t05Nguoi(dipDacBiet.getIdDip()) +
                        dipDacBiet.getTien614() * dipDacBietRepositoryImpl.t614Nguoi(dipDacBiet.getIdDip()) +
                        dipDacBiet.getTien1517() * dipDacBietRepositoryImpl.t1517Nguoi(dipDacBiet.getIdDip());
                tongTienDB.setText(String.valueOf(tongTienTinh));

                nguoiDaTraoDB.setText(String.valueOf(dipDacBietRepositoryImpl.nguoiDaTrao(dipDacBiet.getIdDip())));
                nguoiChuaTraoDB.setText(String.valueOf(dipDacBietRepositoryImpl.nguoiChuaTrao(dipDacBiet.getIdDip())));
                hoDaTraoDB.setText(String.valueOf(dipDacBietRepositoryImpl.hoDaTrao(dipDacBiet.getIdDip())));
                hoChuaTraoDB.setText(String.valueOf(dipDacBietRepositoryImpl.hoChuaTrao(dipDacBiet.getIdDip())));
            }
        }
    }
}
