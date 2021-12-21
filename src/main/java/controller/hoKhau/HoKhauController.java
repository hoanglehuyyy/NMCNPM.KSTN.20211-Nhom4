package controller.hoKhau;

import entity.HoKhau;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.DbUtil;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {
    @FXML
    private TableView<HoKhau> table;

    @FXML
    private TableColumn<HoKhau, Integer> id_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, Integer> id_chu_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> address_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> thanhpho_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> quanhuyen_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> phuongxa_ho_khauCol;

    @FXML
    private TableColumn<HoKhau, String> ngaytao_ho_khauCol;

    @FXML
    private TextField search_ho_khau;

    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> list_combo_box = FXCollections.observableArrayList("All","Mã hộ khẩu","Mã chủ hộ","Địa chỉ");


    private ObservableList<HoKhau> hokhauList = FXCollections.observableArrayList();

    private ObservableList<HoKhau> searchList = FXCollections.observableArrayList();

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    //checked//
    public void search_all(ActionEvent event){
        String sc = comboBox.getValue();
        if(sc.equals("All")){
            table.setItems(hokhauList);
        }
        else{
            return;
        }
    }

    //checked//
    public void search_hk(ActionEvent event){
    }
    //checked//
    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/themHoKhau.fxml"));
        Parent them_ho_khau = loader.load();
        Scene scene = new Scene(them_ho_khau);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.show();
    }
    //checked//
    public void delete(ActionEvent event){
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Do you want to delete?");
        alert.setContentText("Choose your option:");

        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonYes,buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Message");
        alert1.setHeaderText("Notification");

        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            hokhauList.remove(hk);
            alert1.setContentText("Successful");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Failed");
            alert1.show();
        }

    }

    //checked//
    public void show(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/xemHoKhau.fxml"));
        Parent show_ho_khau = loader.load();
        XemHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.show_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Show");
        Scene scene = new Scene(show_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void change(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/suaHoKhau.fxml"));
        Parent change_ho_khau = loader.load();
        SuaHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.change_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Change");
        Scene scene = new Scene(change_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void tach(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/tachHoKhau.fxml"));
        Parent tach_ho_khau = loader.load();
        TachHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.tach_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Tach");
        Scene scene = new Scene(tach_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void chuyen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/chuyenHoKhau.fxml"));
        Parent chuyen_ho_khau = loader.load();
        ChuyenHoKhauController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.chuyen_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Chuyen");
        Scene scene = new Scene(chuyen_ho_khau);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void initCol(){
        id_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("id_ho_khau"));
        id_chu_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("id_chu_ho"));
        address_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("address_ho_khau"));
    }

    private void loadData(){
        hokhauList.clear();
        String qu = "SELECT * FROM `ho_khau`";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int id_hk = rs.getInt("idHoKhau");
                int id_chu_ho_hk = rs.getInt("idChuHo");
                String address_hk = rs.getString("diaChi");
                String thanhpho_hk = rs.getString("tinhThanhPho");
                String quanhuyen_hk = rs.getString("quanHuyen");
                String phuongxa_hk = rs.getString("phuongXa");
                Date ngaytao_hk =  rs.getDate("ngayTao");
                String trangthai_hk = rs.getString("trangThai");

                hokhauList.add(new HoKhau(id_hk,id_chu_ho_hk,address_hk,thanhpho_hk,quanhuyen_hk,phuongxa_hk,ngaytao_hk,trangthai_hk));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(hokhauList);
        comboBox.setItems(list_combo_box);
    }


    public void searchClick(MouseEvent mouseEvent) {
    }
}
