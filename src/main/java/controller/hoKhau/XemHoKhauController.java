package controller.hoKhau;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
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

public class XemHoKhauController implements Initializable {
    @FXML
    private Label id_ho_khau_label;
    @FXML
    private Label id_chu_ho_label;
    @FXML
    private Label hoten_chu_ho_label;
    @FXML
    private Label address_label;
    @FXML
    private Label thanhpho_label;
    @FXML
    private Label quanhuyen_label;
    @FXML
    private Label phuongxa_label;
    @FXML
    private Label ngaytao_label;
    @FXML
    private Label trangthai_label;
    @FXML
    private TableView<HoKhauNhanKhau> nk_table;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hotenNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinhNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, String> quanheChuHo;
    @FXML
    private TableColumn<HoKhauNhanKhau, Integer> idNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, String> cmndNhanKhau;

    private ObservableList<HoKhauNhanKhau> list = FXCollections.observableArrayList();

    private String hoten_chu_ho_hold;

    public String getHoten_chu_ho_hold() {
        return hoten_chu_ho_hold;
    }

    public void setHoten_chu_ho_hold(String hoten_chu_ho_hold) {
        this.hoten_chu_ho_hold = hoten_chu_ho_hold;
    }

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    public void show_hk(HoKhau hk){
        id_ho_khau_label.setText(String.valueOf(hk.getIdHoKhau()));
        id_chu_ho_label.setText(String.valueOf(hk.getIdChuHo()));
        address_label.setText(hk.getDiachi());
        thanhpho_label.setText(hk.getTinhThanhPho());
        quanhuyen_label.setText(hk.getQuanHuyen());
        phuongxa_label.setText(hk.getPhuongXa());
        ngaytao_label.setText(String.valueOf(hk.getNgayTao()));
        trangthai_label.setText(hk.getTrangThai());
        loadData();
        hoten_chu_ho(Integer.parseInt(id_ho_khau_label.getText()));
        hoten_chu_ho_label.setText(this.getHoten_chu_ho_hold());
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
    }

    private void initCol(){
        hotenNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinhNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanheChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
        idNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Integer>("idNhanKhau"));
        cmndNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("cmnd"));
    }

    private void loadData(){
        list.clear();
        int idHoKhau = Integer.parseInt(id_ho_khau_label.getText());
        list.addAll(HoKhauRepo.loadDataXemHoKhauController(idHoKhau));
        nk_table.setItems(list);
    }

    private void hoten_chu_ho(int a){
        this.setHoten_chu_ho_hold(HoKhauRepo.hoten_chu_ho(a));
    }
}
