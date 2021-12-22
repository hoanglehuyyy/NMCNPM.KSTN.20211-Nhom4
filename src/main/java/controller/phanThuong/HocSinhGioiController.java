package controller.phanThuong;

import entity.DipHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiImpl;
import utility.Variable;

import java.net.URL;
import java.util.ResourceBundle;

public class HocSinhGioiController implements Initializable {

    @FXML
    private Pane hsgMainPane;
    @FXML
    private TextField searchText;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<DipHocSinhGioi> hsgTable;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> hsgIDCol;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> hsgNamCol;
    @FXML
    private TableColumn<DipHocSinhGioi, Integer> hsgSoNguoiCol;
    @FXML
    private TableColumn<DipHocSinhGioi, String> hsgMoTaCol;

    ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.ID_DIP, Variable.NAM_HOC);
    HocSinhGioiImpl hocSinhGioiImpl = new HocSinhGioiImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hsgIDCol.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        hsgNamCol.setCellValueFactory(new PropertyValueFactory<>("nam"));
        hsgMoTaCol.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        hsgSoNguoiCol.setCellValueFactory(new PropertyValueFactory<>("soNguoiChuaTraoThuong"));
        ObservableList<DipHocSinhGioi> dipHocSinhGioi = hocSinhGioiImpl.bangDipHocSinhGioi();
        hsgTable.setItems(dipHocSinhGioi);
        comboBox.setItems(truongTraCuu);
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/phanThuong/phanThuong.fxml"));
        hsgMainPane.getChildren().clear();
        hsgMainPane.getChildren().add(pane);
    }

    public void searchClick(MouseEvent mouseEvent) {
    }

    public void searchEnter(KeyEvent keyEvent) {
    }

    @SneakyThrows
    public void createDip(MouseEvent mouseEvent) {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/view/phanThuong/taoDipHocSinhGioi.fxml"));
        Scene scene = new Scene(sp);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @SneakyThrows
    public void themMinhChung(ActionEvent actionEvent) {
        Parent p = FXMLLoader.load(getClass().getResource("/view/phanThuong/chonMinhChungNhanThuong.fxml"));
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @SneakyThrows
    public void xemChiTietDip(MouseEvent mouseEvent) {
        DipHocSinhGioi dipHocSinhGioi = hsgTable.getSelectionModel().getSelectedItem();
        if (mouseEvent.getButton() == MouseButton.PRIMARY){
            if (dipHocSinhGioi != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/phanThuong/chiTietHocSinhGioi.fxml"));
                Parent p = loader.load();
                ChiTietHocSinhGioiController chiTiet = loader.getController();
                chiTiet.setDipHocSinhGioi(dipHocSinhGioi);
                Scene scene = new Scene(p, 1100, 600);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setX(10);
                stage.setY(10);
                stage.show();
            }
        }
    }

    public void xoaDip(ActionEvent actionEvent) {
    }
}
