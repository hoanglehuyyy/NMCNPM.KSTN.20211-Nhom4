package controller.hoKhau;

import entity.ChuyenHoKhau;
import entity.HoKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.HoKhauRepositoryImpl;
import utility.DbUtil;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class lichSuChuyenDiController implements Initializable {
    @FXML
    private TableView<ChuyenHoKhau> lich_su_chuyen_di_tab;
    @FXML
    private TableColumn<ChuyenHoKhau,Integer> idHoKhau_chuyendi_col;
    @FXML
    private TableColumn<ChuyenHoKhau,Integer> id_chuyendi_col;
    @FXML
    private TableColumn<ChuyenHoKhau, Date> ngaychuyendi_chuyendi_col;
    @FXML
    private TableColumn<ChuyenHoKhau,String> diachichuyenden_chuyendi_col;
    @FXML
    private TableColumn<ChuyenHoKhau,String> lido_chuyendi_col;
    @FXML
    private Label ma_ho_khau;
    @FXML
    private Label ho_ten_chu_ho;

    private ObservableList<ChuyenHoKhau> list = FXCollections.observableArrayList();

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    public void lich_su_chuyen_di(HoKhau hk){
        ma_ho_khau.setText(String.valueOf(hk.getIdHoKhau()));
        ho_ten_chu_ho.setText(hk.getHotenChuho());
        loadData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
    }

    private void initCol(){
        idHoKhau_chuyendi_col.setCellValueFactory(new PropertyValueFactory<ChuyenHoKhau,Integer>("idHoKhau"));
        id_chuyendi_col.setCellValueFactory(new PropertyValueFactory<ChuyenHoKhau,Integer>("id"));
        ngaychuyendi_chuyendi_col.setCellValueFactory(new PropertyValueFactory<ChuyenHoKhau,Date>("ngayChuyenDi"));
        diachichuyenden_chuyendi_col.setCellValueFactory(new PropertyValueFactory<ChuyenHoKhau,String>("noiChuyenDen"));
        lido_chuyendi_col.setCellValueFactory(new PropertyValueFactory<ChuyenHoKhau,String>("ghiChu"));
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    private void loadData(){
        int idHoKhau = Integer.parseInt(ma_ho_khau.getText());
        list.clear();
        list.addAll(HoKhauRepo.loadData(idHoKhau));
        lich_su_chuyen_di_tab.setItems(list);
    }
}
