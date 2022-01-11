package controller.phanThuong;

import entity.DipDacBiet;
import entity.NhanKhauDipDacBiet;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import repository.DipDacBietRepository;
import repository.DipDacBietRepositoryImpl;
import utility.Variable;

import java.net.URL;
import java.util.ResourceBundle;

public class DdbDanhSachNhanThuongController implements Initializable {

    @FXML
    private Label tieuDe;
    @FXML
    private TextField searchText;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<NhanKhauDipDacBiet> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhauDipDacBiet, Integer> idCol;
    @FXML
    private TableColumn<NhanKhauDipDacBiet, String> tenCol;
    @FXML
    private TableColumn<NhanKhauDipDacBiet, String> nhomCol;
    @FXML
    private TableColumn<NhanKhauDipDacBiet, Boolean> kiemTraCol;

    private DipDacBiet dipDacBiet = new DipDacBiet();
    private DipDacBietRepository dipDacBietRepository = new DipDacBietRepositoryImpl();

    private String tenNhanKhau;
    private String nhom;
    private String traCuu;

    public void setDanhSach(DipDacBiet dip) {
        dipDacBiet = dip;
        tieuDe.setText(tieuDe.getText() + dip.getTen());
        nhanKhauTable.setItems(dipDacBietRepository.bangNhanThuong(dip.getIdDip()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idCol.setCellValueFactory(new PropertyValueFactory<>("idNhanKhau"));
        tenCol.setCellValueFactory(new PropertyValueFactory<>("tenNhanKhau"));
        nhomCol.setCellValueFactory(new PropertyValueFactory<>("nhom"));
        kiemTraCol.setCellFactory(params -> {
            CheckBoxTableCell<NhanKhauDipDacBiet, Boolean> cell = new CheckBoxTableCell<>();
            return cell;
        });
        kiemTraCol.setCellValueFactory(param -> {
            NhanKhauDipDacBiet nk = param.getValue();
            SimpleBooleanProperty booleanProperty = new SimpleBooleanProperty(nk.getKiemTra());
            
            booleanProperty.addListener(((observableValue, aBoolean, t1) -> {
                nk.setKiemTra(t1);
                dipDacBietRepository.kiemTraTraoThuong(dipDacBiet.getIdDip(), nk.getIdNhanKhau(), t1);
            }));
            return booleanProperty;
        });

        comboBox.setItems(FXCollections.observableArrayList(Variable.TEN_NHAN_KHAU, Variable.NHOM));
        comboBox.setCellFactory(new Callback<>() {
            @Override
            public ListCell<String> call(ListView<String> stringListView) {
                return new ListCell<>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item);
                            if (item.contains(Variable.TEN_NHAN_KHAU)) {
                                if (tenNhanKhau != null) setTextFill(Color.RED);
                                else setTextFill(Color.BLACK);
                            }
                            if (item.contains(Variable.NHOM)) {
                                if (nhom != null) setTextFill(Color.RED);
                                else setTextFill(Color.BLACK);
                            }
                        }
                    }
                };
            }
        });
    }

    public void truongTraCuu(ActionEvent actionEvent) {
        if (!comboBox.getSelectionModel().getSelectedItem().isEmpty()) {
            searchText.setEditable(true);
            if (traCuu != null && traCuu.equals(Variable.TEN_NHAN_KHAU)) {
                if (searchText.getText().isEmpty()) tenNhanKhau = null;
                else tenNhanKhau = searchText.getText();
            }
            if (traCuu != null && traCuu.equals(Variable.NHOM)) {
                if (searchText.getText().isEmpty()) nhom = null;
                else nhom = searchText.getText();
            }
            traCuu = comboBox.getSelectionModel().getSelectedItem();
            if (traCuu.equals(Variable.TEN_NHAN_KHAU)) {
                if (tenNhanKhau != null) searchText.setText(tenNhanKhau);
                else searchText.clear();
            }
            if (traCuu.equals(Variable.NHOM)) {
                if (nhom != null) searchText.setText(nhom);
                else searchText.clear();
            }
        }
    }

    public void searchClick(MouseEvent mouseEvent) {
        if (traCuu == Variable.TEN_NHAN_KHAU) {
            if (!searchText.getText().isEmpty()) tenNhanKhau = searchText.getText();
            else tenNhanKhau = null;
        }
        if (traCuu == Variable.NHOM) {
            if (!searchText.getText().isEmpty()) nhom = searchText.getText();
            else nhom = null;
        }
        nhanKhauTable.setItems(dipDacBietRepository.traCuuNhanThuong(tenNhanKhau, NhanKhauDipDacBiet.getNhom1(nhom)));
    }

    public void searchEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (traCuu == Variable.TEN_NHAN_KHAU) {
                if (!searchText.getText().isEmpty()) tenNhanKhau = searchText.getText();
                else tenNhanKhau = null;
            }
            if (traCuu == Variable.NHOM) {
                if (!searchText.getText().isEmpty()) nhom = searchText.getText();
                else nhom = null;
            }
            nhanKhauTable.setItems(dipDacBietRepository.traCuuNhanThuong(tenNhanKhau, NhanKhauDipDacBiet.getNhom1(nhom)));
        }
    }

    public void quayLaiClick(MouseEvent mouseEvent) {
        ((Stage) ((Node) mouseEvent.getSource()).getScene().getWindow()).close();
    }
}
