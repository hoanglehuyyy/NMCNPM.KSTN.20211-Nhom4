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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Message;
import utility.Utility;
import utility.Variable;

import java.net.URL;
import java.util.Optional;
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


    private Integer namHoc;
    private String traCuu = null;

    ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.NAM_HOC);
    HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hsgIDCol.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        hsgNamCol.setCellValueFactory(new PropertyValueFactory<>("nam"));
        hsgMoTaCol.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        hsgSoNguoiCol.setCellValueFactory(new PropertyValueFactory<>("soNguoiChuaTraoThuong"));
        hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
        comboBox.setItems(truongTraCuu);
        comboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            if (item.contains(Variable.NAM_HOC)) {
                                if (namHoc != null) {
                                    System.out.println(item);
                                    setTextFill(Color.RED);
                                }
                            }
                        } else setText(null);
                    }
                };
            }
        });
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/phanThuong/phanThuong.fxml"));
        hsgMainPane.getChildren().clear();
        hsgMainPane.getChildren().add(pane);
    }

    public void truongTraCuu(ActionEvent actionEvent) {
        if (!comboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            searchText.setEditable(true);
            if (traCuu != null && traCuu.equals(Variable.NAM_HOC)) {
                if (searchText.getText().isEmpty()) namHoc = null;
                else namHoc = Integer.parseInt(searchText.getText());
            }
            traCuu = comboBox.getSelectionModel().getSelectedItem();
            if (traCuu.equals(Variable.NAM_HOC)) {
                if (namHoc != null) searchText.setText(String.valueOf(namHoc));
                else searchText.clear();
            }
        }
    }

    public void searchClick(MouseEvent mouseEvent) {
        if (traCuu == Variable.NAM_HOC) {
            if (!searchText.getText().isEmpty() && Utility.isPostive(searchText.getText())) namHoc = Integer.parseInt(searchText.getText());
            else namHoc = null;
        }
        hsgTable.setItems(hocSinhGioiImpl.traCuuDipHocSinhGioi(namHoc));
    }

    public void searchEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (traCuu == Variable.NAM_HOC) {
                if (!searchText.getText().isEmpty() && Utility.isPostive(searchText.getText())) namHoc = Integer.parseInt(searchText.getText());
                else namHoc = null;
            }
            hsgTable.setItems(hocSinhGioiImpl.traCuuDipHocSinhGioi(namHoc));
        }
    }

    @SneakyThrows
    public void createDip(MouseEvent mouseEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/phanThuong/taoDipHocSinhGioi.fxml"));
        Parent p = loader.load();
        TaoDipHocSinhGioiController t = loader.getController();
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
        });
    }

    @SneakyThrows
    public void themMinhChung(ActionEvent actionEvent) {
        DipHocSinhGioi dipHocSinhGioi = hsgTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/chonMinhChungNhanThuong.fxml"));
        Parent p = loader.load();
        ChonMinhChungNhanThuongController c = loader.getController();
        c.setDip(dipHocSinhGioi);
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
        });
    }

    @SneakyThrows
    public void xemChiTietDip(ActionEvent actionEvent) {
        DipHocSinhGioi dipHocSinhGioi = hsgTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/chiTietHocSinhGioi.fxml"));
        Parent p = loader.load();
        ChiTietHocSinhGioiController chiTiet = loader.getController();
        chiTiet.setDipHocSinhGioi(dipHocSinhGioi);
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
        });
        stage.initOwner(((MenuItem) actionEvent.getTarget()).getParentPopup().getOwnerWindow());
    }

    public void xoaDip(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(Message.xacNhanXoaDip);
        alert.setContentText(Message.canhBaoXoaDip);
        Optional<ButtonType> result =  alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            DipHocSinhGioi dipHocSinhGioi = hsgTable.getSelectionModel().getSelectedItem();
            hocSinhGioiImpl.xoaDipHocSinhGioi(dipHocSinhGioi.getIdDip());
            hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
            alert.close();
            Alert newAleart = new Alert(Alert.AlertType.INFORMATION);
            newAleart.setHeaderText(Message.thongBaoXoaDip);
            newAleart.show();
        }
    }

    @SneakyThrows
    public void ChinhSuaThongTinDip(ActionEvent actionEvent) {
        DipHocSinhGioi dipHocSinhGioi = hsgTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/chinhSuaHocSinhGioi.fxml"));
        Parent p = loader.load();
        ChinhSuaHocSinhGioiController c = loader.getController();
        c.setThongTin(dipHocSinhGioi);
        Scene scene = new Scene(p);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setOnHidden(windowEvent -> {
            hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
        });
        stage.show();
    }

    @SneakyThrows
    public void xemDanhSach(ActionEvent actionEvent) {
        DipHocSinhGioi dipHocSinhGioi = hsgTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/hsgDanhSachNhanThuong.fxml"));
        Parent p = loader.load();
        HsgDanhSachNhanThuongController h = loader.getController();
        h.setDanhSach(dipHocSinhGioi);
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            hsgTable.setItems(hocSinhGioiImpl.bangDipHocSinhGioi());
        });
    }
}
