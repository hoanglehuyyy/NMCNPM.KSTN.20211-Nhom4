package controller.hoKhau;

import entity.HoKhau;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utility.DbUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class ThemHoKhauController implements Initializable {

    @FXML
    private TextField address_ho_khau_text;
    @FXML
    private TextField thanhpho_text;
    @FXML
    private TextField quanhuyen_text;
    @FXML
    private TextField phuongxa_text;
    @FXML
    private TextField ngaytao_text;
    @FXML
    private TextField trangthai_text;
    @FXML
    private TextField hovaten_chuho;
    @FXML
    private TextField ngaysinh_chuho;
    @FXML
    private TextField nguyenquan_chuho;
    @FXML
    private TableView<NhanKhau> nhankhauTab;
    @FXML
    private TableColumn<NhanKhau,String> hovaten_nkCol;
    @FXML
    private TableColumn<NhanKhau,Date> ngaysinh_nkCol;
    @FXML
    private TableColumn<NhanKhau,String> nguyenquan_nkCol;

    private ObservableList<NhanKhau> dsnk = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> search_dsnk = FXCollections.observableArrayList();

    private int id_chu_ho = -1;

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    public int getId_chu_ho() {
        return id_chu_ho;
    }

    public void setId_chu_ho(int id_chu_ho) {
        this.id_chu_ho = id_chu_ho;
    }

    private boolean check_chu_ho(NhanKhau a){
        String qu = "SELECT * FROM `ho_khau`";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idChuHo = rs.getInt("idChuHo");
                if(idChuHo == a.getId()) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return false;

    }

    public void chon_chu_ho_button(ActionEvent e) throws IOException {
        NhanKhau a = nhankhauTab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không nhân khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            this.id_chu_ho = -1;
            return;
        }
        if(check_chu_ho(a)){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Nhân khẩu đã là chủ hộ rồi.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            this.id_chu_ho = -1;
            return;
        }
        if(check_nhan_khau_exist_nk(a.getId())){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Nhân khẩu đã thuộc một hộ khẩu khác");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }

        this.id_chu_ho = a.getId();
        Alert me = new Alert(Alert.AlertType.INFORMATION);
        me.setTitle("Alert!");
        me.setHeaderText("Chọn chủ hộ thành công.");
        me.setContentText("Bạn có thể chọn lại chủ hộ.");
        me.show();
    }

    private boolean check_data(){
        if(address_ho_khau_text.getText().equals("")) return false;
        if(thanhpho_text.getText().equals("")) return false;
        if(quanhuyen_text.getText().equals("")) return false;
        if(phuongxa_text.getText().equals("")) return false;
        if(ngaytao_text.getText().equals("")) return false;
        if(trangthai_text.getText().equals("")) return false;
        if(this.id_chu_ho == -1) return false;
        return true;
    }

    private boolean check_constraint(){
        String test = trangthai_text.getText().trim();
        if(!test.equals("Thường trú") && !test.equals("Đã chuyển đi")) return false;
        try{
            Date date = Date.valueOf(ngaytao_text.getText());
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    private boolean check_nhan_khau_exist_nk(int a){

        String qu = "SELECT * FROM `ho_khau_nhan_khau`";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int x = rs.getInt("idNhanKhau");
                if(a == x) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void xac_nhan_button(ActionEvent e) throws IOException {
        if(!check_data()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Các trường bắt buộc còn trống");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }

        if(!check_constraint()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Các trường không thoả mãn");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }

        String qu = "INSERT INTO ho_khau(idChuHo, tinhThanhPho, quanHuyen, phuongXa, diaChi, ngayTao, trangThai) VALUES (?,?,?,?,?,?,?)";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,id_chu_ho);
            pstmt.setString(2,thanhpho_text.getText());
            pstmt.setString(3,quanhuyen_text.getText());
            pstmt.setString(4,phuongxa_text.getText());
            pstmt.setString(5,address_ho_khau_text.getText());
            pstmt.setDate(6,Date.valueOf(ngaytao_text.getText()));
            pstmt.setString(7,trangthai_text.getText());
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }

    }

    public void tim_button(ActionEvent e) throws IOException {
        search_dsnk.clear();
        String search_hovaten = hovaten_chuho.getText().trim().toLowerCase();
        String search_ngaysinh = ngaysinh_chuho.getText().trim().toLowerCase();
        String search_nguyenquan = nguyenquan_chuho.getText().trim().toLowerCase();
        for(NhanKhau a : dsnk){
            if((a.getHoTen().trim().toLowerCase()).contains(search_hovaten) && (String.valueOf(a.getNgaySinh()).trim().toLowerCase()).contains(search_ngaysinh) && (a.getNguyenQuan().trim().toLowerCase()).contains(search_nguyenquan)){
                NhanKhau b = new NhanKhau(a.getId(),a.getHoTen(),a.getNgaySinh(),a.getNoiSinh(),a.getGioiTinh(),a.getNguyenQuan(),a.getDanToc(),a.getTonGiao(),a.getQuocTich());
                search_dsnk.add(b);
            }

        }
        nhankhauTab.setItems(search_dsnk);
    }

    public void huy_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void initCol(){
        hovaten_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("hoTen"));
        ngaysinh_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,Date>("ngaySinh"));
        nguyenquan_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("nguyenQuan"));

    }
    private void loadData(){
        dsnk.clear();
        String qu = "SELECT * FROM `nhan_khau`";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int idNhanKhau = rs.getInt("idNhanKhau");
                String hoTen = rs.getString("hoTen");
                Date ngaySinh = rs.getDate("ngaySinh");
                String noiSinh = rs.getString("noiSinh");
                String gioiTinh = rs.getString("gioiTinh");
                String nguyenQuan = rs.getString("nguyenQuan");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String quocTich = rs.getString("quocTich");

                dsnk.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,nguyenQuan,danToc,tonGiao,quocTich));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nhankhauTab.setItems(dsnk);
    }
}
