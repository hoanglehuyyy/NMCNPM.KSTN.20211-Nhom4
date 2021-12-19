package controller;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;

public class NhanKhauController implements Initializable {
    @FXML
    private TableView<NhanKhau>table;
    @FXML
    private TableColumn<NhanKhau,Integer>  idColumn;
    @FXML
    private TableColumn<NhanKhau,String>  hoTenColumn;
    @FXML
    private TableColumn<NhanKhau,String>  ngaySinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  CMNDColumn;
    @FXML
    private TableColumn<NhanKhau,String>  trangThaiColumn;
    @FXML
    private Button themNhanKhau;

    private ObservableList<NhanKhau> nhanKhauList;
    //NhanKhauRepository nhanKhauRepository = new NhanKhauRepositoryImpl();



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //nhanKhauList=nhanKhauRepository.danhSachNhanKhau();
        //print(nhanKhauList);
        nhanKhauList=FXCollections.observableArrayList(
                new NhanKhau(1, "Nguyen Thanh", "31/03/2001", "Nam", "0123456","thuong tru"),
                new NhanKhau(2, "Le Hoang", "31/03/2001", "Nam", "0111156","thuong tru")
        );
        idColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("CMND"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("trangThai"));

        table.setItems(nhanKhauList);
    }



    public void changScenceThemNhanKhau(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_ThemNhanKhau.fxml"));
        Parent nhanKhauView=loader.load();
        Scene scene= new Scene(nhanKhauView);
        stage.setScene(scene);
    }



    public void detailNhanKhau(ActionEvent e) throws IOException {



        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_ChiTietThongTin.fxml"));
        Parent nhanKhauView = loader.load();
        Controller_thongTinNK controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        controller.setNhanKhau(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("THÔNG TIN NHÂN KHẨU");
        Scene scene = new Scene(nhanKhauView);
        stage.setScene(scene);
        stage.show();
    }



    public void chinhSuaNK(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_ChinhSua.fxml"));
        Parent chinhSuaNKView = loader.load();
        Controller_chinhSuaNK controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        controller.setChinhSuaNK(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("CHỈNH SỬA NHÂN KHẨU");
        Scene scene = new Scene(chinhSuaNKView);
        stage.setScene(scene);
        stage.show();
    }



    public void chuyenNK() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_ChuyenDi.fxml"));
        Parent chinhSuaNKView = loader.load();
        Controller_chuyenNK controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        controller.setChuyenNhanKhau(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("CHUYỂN NHÂN KHẨU");
        Scene scene = new Scene(chinhSuaNKView);
        stage.setScene(scene);
        stage.show();
    }

    public void tamVang() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_TamVang.fxml"));
        Parent tamVangNK = loader.load();
        Controller_quanLyTamVang controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        controller.setTamVang(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("QUẢN LÝ TẠM VẮNG");
        Scene scene = new Scene(tamVangNK);
        stage.setScene(scene);
        stage.show();
    }

    public void khaiTu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_KhaiTu.fxml"));
        Parent khaiTuNK = loader.load();
        Controller_khaiTuNK controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        controller.setKhaiTu(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("KHAI TỬ");
        Scene scene = new Scene(khaiTuNK);
        stage.setScene(scene);
        stage.show();
    }

    public void tamTru() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("NK_TamTru.fxml"));
        Parent tamVangNK = loader.load();
        Controller_tamTru controller = loader.getController();
        NhanKhau selected = table.getSelectionModel().getSelectedItem();
        controller.setTamTru(selected);
        Stage stage = new Stage();
//        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("QUẢN LÝ TẠM TRÚ");
        Scene scene = new Scene(tamVangNK);
        stage.setScene(scene);
        stage.show();
    }












}

