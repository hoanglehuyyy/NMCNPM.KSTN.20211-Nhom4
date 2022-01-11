package controller.phanThuong;

import entity.DipDacBiet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
import repository.DipDacBietRepository;
import repository.DipDacBietRepositoryImpl;
import utility.Message;
import utility.Utility;
import utility.Variable;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DipDacBietController implements Initializable {

    @FXML
    private Pane ddbMainPane;
    @FXML
    private TextField searchText;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<DipDacBiet> ddbTable;
    @FXML
    private TableColumn<DipDacBiet, Integer> ddbIDCol;
    @FXML
    private TableColumn<DipDacBiet, String> ddbTenCol;
    @FXML
    private TableColumn<DipDacBiet, Integer> ddbNamCol;
    @FXML
    private TableColumn<DipDacBiet, Integer> ddbSoNguoiCol;
    @FXML
    private TableColumn<DipDacBiet, String> ddbMoTaCol;

    private String tenDip;
    private Integer namHoc;
    private String traCuu;

    ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.TEN_DIP, Variable.NAM_HOC);
    DipDacBietRepository dipDacBietImpl = new DipDacBietRepositoryImpl();
    ObservableList<DipDacBiet> danhSachDip = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ddbIDCol.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        ddbTenCol.setCellValueFactory(new PropertyValueFactory<>("ten"));
        ddbNamCol.setCellValueFactory(new PropertyValueFactory<>("nam"));
        ddbMoTaCol.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        ddbSoNguoiCol.setCellValueFactory(new PropertyValueFactory<>("soNguoiChuaTraoThuong"));
        danhSachDip = dipDacBietImpl.bangDipDacBiet();
        ddbTable.setItems(danhSachDip);

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
                                if (namHoc != null) setTextFill(Color.RED);
                                else setTextFill(Color.BLACK);
                            }
                            if (item.contains(Variable.TEN_DIP)) {
                                if (tenDip != null) setTextFill(Color.RED);
                                else setTextFill(Color.BLACK);
                            }
                        }
                    }
                };
            }
        });

    }

    @SneakyThrows
    public void backMouseClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/phanThuong/phanThuong.fxml"));
        ddbMainPane.getChildren().clear();
        ddbMainPane.getChildren().add(pane);
    }

    public void truongTraCuu(ActionEvent actionEvent) {
        if (!comboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            searchText.setEditable(true);
            if (traCuu != null && traCuu.equals(Variable.NAM_HOC)) {
                if (searchText.getText().isEmpty() || !Utility.isPostive(searchText.getText())) namHoc = null;
                else namHoc = Integer.parseInt(searchText.getText());
            }
            if (traCuu != null && traCuu.equals(Variable.TEN_DIP)) {
                if (searchText.getText().isEmpty()) tenDip = null;
                else tenDip = searchText.getText();
            }
            traCuu = comboBox.getSelectionModel().getSelectedItem();
            if (traCuu.equals(Variable.NAM_HOC)) {
                if (namHoc != null) searchText.setText(String.valueOf(namHoc));
                else searchText.clear();
            }
            if (traCuu.equals(Variable.TEN_DIP)) {
                if (tenDip != null) searchText.setText(tenDip);
                else searchText.clear();
            }
        }
    }

    public void ddbSearchClick(MouseEvent mouseEvent) {
        if (traCuu == Variable.NAM_HOC) {
            if (!searchText.getText().isEmpty() && Utility.isPostive(searchText.getText())) namHoc = Integer.parseInt(searchText.getText());
            else namHoc = null;
        }
        if (traCuu == Variable.TEN_DIP) {
            if (!searchText.getText().isEmpty()) tenDip = searchText.getText();
            else tenDip = null;
        }
        ddbTable.setItems(dipDacBietImpl.traCuuDipDacBiet(tenDip, namHoc));
    }

    public void ddbSearchEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (traCuu == Variable.NAM_HOC) {
                if (!searchText.getText().isEmpty() && Utility.isPostive(searchText.getText())) namHoc = Integer.parseInt(searchText.getText());
                else namHoc = null;
                if (!Utility.isPostive(searchText.getText())) searchText.clear();
            }
            if (traCuu == Variable.TEN_DIP) {
                if (!searchText.getText().isEmpty()) tenDip = searchText.getText();
                else tenDip = null;
                }
            ddbTable.setItems(dipDacBietImpl.traCuuDipDacBiet(tenDip, namHoc));
        }
    }

    @SneakyThrows
    public void createDip(MouseEvent mouseEvent) {
        Parent p = FXMLLoader.load(getClass().getResource("/view/phanThuong/taoDipDacBiet.fxml"));
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            ddbTable.setItems(dipDacBietImpl.bangDipDacBiet());
        });
    }

    @SneakyThrows
    public void xemDip(ActionEvent actionEvent) {
        DipDacBiet dip = ddbTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/chiTietDipDacBiet.fxml"));
        Parent p = loader.load();
        ChiTietDipDacBietController c = loader.getController();
        c.setDipDacBiet(dipDacBietImpl.traCuuDipByTenNam(dip.getNam(), dip.getTen()));
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            ddbTable.setItems(dipDacBietImpl.bangDipDacBiet());
        });
    }

    public void xoaDip(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(Message.xacNhanXoaDip);
        alert.setContentText(Message.canhBaoXoaDip);
        Optional<ButtonType> result =  alert.showAndWait();
        if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
            DipDacBiet dipDacBiet = ddbTable.getSelectionModel().getSelectedItem();
            dipDacBietImpl.xoaDipDacBiet(dipDacBiet.getIdDip());
            danhSachDip = dipDacBietImpl.bangDipDacBiet();
            ddbTable.setItems(danhSachDip);
            alert.close();
            Alert newAleart = new Alert(Alert.AlertType.INFORMATION);
            newAleart.setHeaderText(Message.thongBaoXoaDip);
            newAleart.show();
        }
    }

    @SneakyThrows
    public void xemDanhSach(ActionEvent actionEvent) {
        DipDacBiet dip = ddbTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/phanThuong/ddbDanhSachNhanThuong.fxml"));
        Parent p = loader.load();
        DdbDanhSachNhanThuongController d = loader.getController();
        d.setDanhSach(dip);
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            ddbTable.setItems(dipDacBietImpl.bangDipDacBiet());
        });
    }

    @SneakyThrows
    public void chinhSuaThongTin(ActionEvent actionEvent) {
        DipDacBiet dipDacBiet = ddbTable.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/phanThuong/chinhSuaDipDacBiet.fxml"));
        Parent p = loader.load();
        ChinhSuaDipDacBietController c = loader.getController();
        c.setThongTin(dipDacBiet);
        Stage stage = Utility.setStage(p);
        stage.setOnHidden(windowEvent -> {
            ddbTable.setItems(dipDacBietImpl.bangDipDacBiet());
        });
    }
}
