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
    private TableColumn<HoKhau, String> hoten_chu_hoCol;

    @FXML
    private TableColumn<HoKhau, Date> ngay_taoCol;

    @FXML
    private TableColumn<HoKhau, String> trang_thaiCol;


    @FXML
    private TextField search_ho_khau;

    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> list_combo_box = FXCollections.observableArrayList("Địa chỉ","Tên chủ hộ","Trạng thái","Ngày tạo");

    private ObservableList<HoKhau> hokhauList = FXCollections.observableArrayList();

    private ObservableList<HoKhau> searchList = FXCollections.observableArrayList();

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;


    //checked//
    public void add(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/themHoKhau.fxml"));
        Parent them_ho_khau = loader.load();
        Scene scene = new Scene(them_ho_khau);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Thêm hộ khẩu");
        stage.setScene(scene);
        stage.show();
    }

    public void xem_lich_su(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/hoKhau/lichSuChuyenDi.fxml"));
        Parent lich_su_scene = loader.load();
        lichSuChuyenDiController controller = loader.getController();
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.lich_su_chuyen_di(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Lịch sử chuyển đi");
        Scene scene = new Scene(lich_su_scene);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void delete(ActionEvent event){
        HoKhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xoá");
        alert.setHeaderText("Bạn có muốn xoá không?");
        alert.setContentText("Yes or No:");

        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonYes,buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Thông báo!");

        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            int idHoKhau = hk.getIdHoKhau();
            update_nk_after_delete(idHoKhau);
            update_ch_after_delete(idHoKhau);
            delete_hk(idHoKhau);
            hokhauList.remove(hk);
            alert1.setContentText("Thành công");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Thất bại");
            alert1.show();
        }

    }

    private void delete_hk(int a){
        String qu = "DELETE FROM `ho_khau` WHERE idHoKhau = ?";
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    private void update_nk_after_delete(int a){
        String qu = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    private void update_ch_after_delete(int a){
        String qu = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
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
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.show_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Xem hộ khẩu");
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
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.change_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Sửa hộ khẩu");
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
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.tach_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Tách hộ khẩu");
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
            m.setTitle("Thông báo!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if (hk.getTrangThai().equals("Đã chuyển đi")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Hộ khẩu không còn ở đây.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.chuyen_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Chuyển hộ khẩu");
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
        id_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("idHoKhau"));
        id_chu_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Integer>("idChuHo"));
        address_ho_khauCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("diachi"));
        hoten_chu_hoCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("hotenChuho"));
        ngay_taoCol.setCellValueFactory(new PropertyValueFactory<HoKhau,Date>("ngayTao"));
        trang_thaiCol.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("trangThai"));
    }

    private void loadData(){
        hokhauList.clear();
        String qu = "SELECT hk.*, nk.hoTen FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuHo = nk.idNhanKhau";

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
                String hoten = rs.getString("hoTen");

                hokhauList.add(new HoKhau(id_hk,id_chu_ho_hk,hoten,thanhpho_hk,quanhuyen_hk,phuongxa_hk,address_hk,ngaytao_hk,trangthai_hk));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        table.setItems(hokhauList);
        comboBox.setItems(list_combo_box);
    }

    public void searchClick(MouseEvent mouseEvent) {
        searchList.clear();
        String search_text = search_ho_khau.getText().trim().toLowerCase(); ;
        String sc = comboBox.getValue();
        try{
            if(sc.equals("Địa chỉ")){
                for(HoKhau a : hokhauList){
                    if((a.getDiachi().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Tên chủ hộ")){
                for(HoKhau a : hokhauList){
                    if((a.getHotenChuho().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Trạng thái")){
                for(HoKhau a : hokhauList){
                    if((a.getTrangThai().toLowerCase()).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
            else if(sc.equals("Ngày tạo")){
                for(HoKhau a : hokhauList){
                    if((String.valueOf(a.getNgayTao())).contains(search_text)){
                        HoKhau clone_hk = new HoKhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }
                table.setItems(searchList);
            }
        }catch(NullPointerException ex){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn trường tìm kiếm!");
            m.setContentText("Mời chọn lại!");
            m.show();
            return;
        }

    }

    public void refresh_button(ActionEvent e){
        loadData();
    }
}
