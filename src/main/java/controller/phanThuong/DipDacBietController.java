package controller.phanThuong;

import entity.DipDacBiet;
import entity.DipHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.DipDacBietImpl;
import utility.Variable;

import java.net.URL;
import java.util.ResourceBundle;

public class DipDacBietController implements Initializable {

    @FXML
    private Pane ddbMainPane;
    @FXML
    private TextField ddbSearchText;
    @FXML
    private ComboBox<String> ddbComboBox;
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

    ObservableList<String> truongTraCuu = FXCollections.observableArrayList(Variable.ID_DIP,Variable.TEN_DIP ,Variable.NAM_HOC);
    DipDacBietImpl dipDacBietImpl = new DipDacBietImpl();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ddbIDCol.setCellValueFactory(new PropertyValueFactory<>("idDip"));
        ddbTenCol.setCellValueFactory(new PropertyValueFactory<>("ten"));
        ddbNamCol.setCellValueFactory(new PropertyValueFactory<>("nam"));
        ddbMoTaCol.setCellValueFactory(new PropertyValueFactory<>("moTa"));
        ddbSoNguoiCol.setCellValueFactory(new PropertyValueFactory<>("soNguoiChuaTraoThuong"));
        ObservableList<DipDacBiet> dipDacBiet = dipDacBietImpl.bangDipDacBiet();
        ddbTable.setItems(dipDacBiet);

        ddbComboBox.setItems(truongTraCuu);
    }

    @SneakyThrows
    public void backMouseClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/phanThuong/phanThuong.fxml"));
        ddbMainPane.getChildren().clear();
        ddbMainPane.getChildren().add(pane);
    }

    public void ddbSearchClick(MouseEvent mouseEvent) {
    }

    public void ddbSearchEnter(KeyEvent keyEvent) {
    }

    @SneakyThrows
    public void createDip(MouseEvent mouseEvent) {
        ScrollPane sp = FXMLLoader.load(getClass().getResource("/view/phanThuong/taoDipDacBiet.fxml"));
        Scene scene = new Scene(sp);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
