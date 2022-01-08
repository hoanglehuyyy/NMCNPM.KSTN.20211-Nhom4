package controller.thongKe;

import entity.VO.NamSoDipVO;
import entity.VO.TenDipVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;
import repository.PhanThuongRepository;
import repository.PhanThuongRepositoryImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ThongKePhanThuongController implements Initializable  {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane tenDipPane;

    @FXML
    private TableView<NamSoDipVO> namSoLuong;
    @FXML
    private TableColumn<NamSoDipVO, Integer> namBang;
    @FXML
    private TableColumn<NamSoDipVO, Integer> soDipBang;

    @FXML
    private TextField nam;

    @FXML
    private TableView<TenDipVO> tenDipBang;
    @FXML
    private TableColumn<TenDipVO, Integer> tenDip;

    PhanThuongRepository phanThuongRepositoryImpl = new PhanThuongRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tenDipPane.setVisible(false);

        namBang.setCellValueFactory(new PropertyValueFactory<>("nam"));
        soDipBang.setCellValueFactory(new PropertyValueFactory<>("soDip"));
        ObservableList<NamSoDipVO> namSoDipVO = phanThuongRepositoryImpl.bangNamSoDip();
        namSoLuong.setItems(namSoDipVO);
    }

    public void tim() throws SQLException {
        if (nam.getText().isBlank()) {
            ObservableList<NamSoDipVO> namSoDipVO = phanThuongRepositoryImpl.bangNamSoDip();
            namSoLuong.setItems(namSoDipVO);
        } else {
            try {
                ObservableList<NamSoDipVO> namSoDipVO = phanThuongRepositoryImpl.bangNamSoDipTim(Integer.valueOf(nam.getText()));
                namSoLuong.setItems(namSoDipVO);
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
    public void xemThongKe(MouseEvent mouseEvent) {
       NamSoDipVO namSoDipVO = namSoLuong.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            if (namSoDipVO != null) {
                tenDipPane.setVisible(true);
                tenDip.setCellValueFactory(new PropertyValueFactory<>("tenDip"));
                ObservableList<TenDipVO> tenDipVO = phanThuongRepositoryImpl.bangTenDipTim(namSoDipVO.nam);
                tenDipBang.setItems(tenDipVO);
            }
        }
    }

    public void hocSinhGioiClick() throws IOException {
        Pane hocSinhGioiPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeHocSinhGioi.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(hocSinhGioiPane);
    }

    public void dipDacBietClick() throws IOException {
        Pane dipDacBietPane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKeDipDacBiet.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(dipDacBietPane);
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
