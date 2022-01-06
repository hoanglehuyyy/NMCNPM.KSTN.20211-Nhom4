package controller.hoKhau;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utility.DbUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SuaHoKhauController implements Initializable {
    @FXML
    private Label id_ho_khau_change;
    @FXML
    private Label id_chu_ho_change;

    private int id_chu_ho_new = 0;

    public int getId_chu_ho_new() {
        return id_chu_ho_new;
    }

    public void setId_chu_ho_new(int id_chu_ho_new) {
        this.id_chu_ho_new = id_chu_ho_new;
    }

    @FXML
    private TextField tinhthanh_change;
    @FXML
    private TextField quanhuyen_change;
    @FXML
    private TextField phuongxa_change;
    @FXML
    private Label hoten_chu_ho_change;

    //nk_table:
    @FXML
    private TableView<HoKhauNhanKhau> nk_table;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hotenNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinhNhanKhau;
    @FXML
    private TableColumn<HoKhauNhanKhau, String> quanheChuHo;

    private ObservableList<HoKhauNhanKhau> list = FXCollections.observableArrayList();

    //nk_table_search:
    @FXML
    private TableView<NhanKhau> nk_table_search;
    @FXML
    private TableColumn<NhanKhau,String> hotenNhanKhau_search;
    @FXML
    private TableColumn<NhanKhau,Date> ngaySinhNhanKhau_search;
    @FXML
    private TableColumn<NhanKhau,String> nguyenQuanNhanKhau_search;
    @FXML
    private TextField hoten_search;
    @FXML
    private TextField ngaysinh_search;
    @FXML
    private TextField nguyenquan_search;
    @FXML
    private TextField quanhe_choose;

    private ObservableList<NhanKhau> list_nk = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> list_nk_search = FXCollections.observableArrayList();

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;
    //function:
    public void change_hk(HoKhau hk){
        id_ho_khau_change.setText(String.valueOf(hk.getIdHoKhau()));
        id_chu_ho_change.setText(String.valueOf(hk.getIdChuHo()));
        tinhthanh_change.setText(hk.getTinhThanhPho());
        quanhuyen_change.setText(hk.getQuanHuyen());
        phuongxa_change.setText(hk.getPhuongXa());

        loadData();
        hoten_chu_ho_change();
    }

    public void save_button(ActionEvent e){
        clear_hknk();
        change_inf_hk();
        change_inf_hknk();
        close_button(e);
    }

    private void change_inf_hk(){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "UPDATE `ho_khau` SET idChuHo = ?, tinhThanhPho = ?, quanHuyen = ?, phuongXa = ? WHERE idHoKhau = ?";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,this.getId_chu_ho_new());
            pstmt.setString(2,tinhthanh_change.getText());
            pstmt.setString(3,quanhuyen_change.getText());
            pstmt.setString(4,phuongxa_change.getText());
            pstmt.setInt(5,idHoKhau);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void change_inf_hknk(){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
        for(HoKhauNhanKhau a : list){
            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(qu);
                pstmt.setInt(1,idHoKhau);
                pstmt.setInt(2,a.getIdNhanKhau());
                pstmt.setString(3,a.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void add_button(ActionEvent e) throws IOException {
        NhanKhau c = nk_table_search.getSelectionModel().getSelectedItem();
        if(c == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        for(HoKhauNhanKhau i : list){
            if(c.sosanh(i)){
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Alert!");
                m.setHeaderText("Nhân khẩu đã tồn tại");
                m.setContentText("Vui lòng chọn lại");
                m.show();
                return;
            }
        }
        if(c.getId() == this.getId_chu_ho_new()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Trùng với chủ hộ");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(check_nhan_khau_exist_hk(c) || check_nhan_khau_exist_nk(c)){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Nhân khẩu đã thuộc một hộ khẩu khác");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(quanhe_choose.getText().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Thiếu trường quan hệ với chủ hộ");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }
        int fake = Integer.parseInt(id_ho_khau_change.getText());
        int f2 = this.getId_chu_ho_new();
        HoKhauNhanKhau new_hknk = new HoKhauNhanKhau(fake,f2,quanhe_choose.getText(),c.getHoTen(),c.getNgaySinh());
        list.add(new_hknk);
        nk_table.setItems(list);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Alert!");
        m.setHeaderText("Thêm thành công nhân khẩu");
        m.show();
        return;
    }

    private boolean check_nhan_khau_exist_nk(NhanKhau a){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "SELECT * FROM `ho_khau_nhan_khau` WHERE idHoKhau != ?";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int x = rs.getInt("idNhanKhau");
                if(a.getId() == x) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    private boolean check_nhan_khau_exist_hk(NhanKhau a){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "SELECT * FROM `ho_khau` WHERE idHoKhau != ?";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int x = rs.getInt("idChuHo");
                if(a.getId() == x) return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void change_button(ActionEvent e) throws IOException {
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không có nhân khẩu nào được chọn");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(quanhe_choose.getText().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Thiếu trường quan hệ với chủ hộ");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }
        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getIdHoKhau(),a.getIdNhanKhau(),quanhe_choose.getText(),a.getHoTen(),a.getNgaySinh());
        list.remove(a);
        list.add(b);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Alert!");
        m.setHeaderText("Sửa thành công nhân khẩu");
        m.show();
        return;
    }

    public void search_button(ActionEvent e) throws IOException {
        list_nk_search.clear();
        String search_hovaten = hoten_search.getText().trim().toLowerCase();
        String search_ngaysinh = ngaysinh_search.getText().trim().toLowerCase();
        String search_nguyenquan = nguyenquan_search.getText().trim().toLowerCase();
        for(NhanKhau a : list_nk){
            if((a.getHoTen().trim().toLowerCase()).contains(search_hovaten) && (String.valueOf(a.getNgaySinh()).trim().toLowerCase()).contains(search_ngaysinh) && (a.getNguyenQuan().trim().toLowerCase()).contains(search_nguyenquan)){
                NhanKhau b = new NhanKhau(a.getId(),a.getHoTen(),a.getNgaySinh(),a.getNoiSinh(),a.getGioiTinh(),a.getNguyenQuan(),a.getDanToc(),a.getTonGiao(),a.getQuocTich());
                list_nk_search.add(b);
            }

        }
        nk_table_search.setItems(list_nk_search);
    }

    public void chon_chu_ho_button(ActionEvent e) throws IOException {
        NhanKhau a = nk_table_search.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(check_nhan_khau_exist_hk(a) || check_nhan_khau_exist_nk(a)){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Nhân khẩu đã tồn tại");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        this.setId_chu_ho_new(a.getId());
        id_chu_ho_change.setText(String.valueOf(this.getId_chu_ho_new()));
        hoten_chu_ho_change.setText(a.getHoTen());
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Alert!");
        m.setHeaderText("Chọn chủ hộ thành công");
        m.show();
        return;
    }

    public void delete_button(ActionEvent e){
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không có nhân khẩu nào được chọn");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        list.remove(a);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Alert!");
        m.setHeaderText("Đã xoá thành công");
        m.show();
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadNK();
    }

    private void initCol(){
        hotenNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinhNhanKhau.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanheChuHo.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
        hotenNhanKhau_search.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("hoTen"));
        ngaySinhNhanKhau_search.setCellValueFactory(new PropertyValueFactory<NhanKhau,Date>("ngaySinh"));
        nguyenQuanNhanKhau_search.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("nguyenQuan"));
    }

    private void loadData(){
        list.clear();
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "SELECT hknk.idHoKhau, hknk.idNhanKhau, hknk.quanHeChuHo, nk.hoTen, nk.ngaySinh FROM `ho_khau_nhan_khau` hknk, `nhan_khau` nk WHERE hknk.idNhanKhau = nk.idNhanKhau and hknk.idHoKhau = ?";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int a = rs.getInt("idHoKhau");
                int b = rs.getInt("idNhanKhau");
                String c = rs.getString("quanHeChuHo");
                String d = rs.getString("hoTen");
                Date e = rs.getDate("ngaySinh");

                HoKhauNhanKhau h = new HoKhauNhanKhau(a,b,c,d,e);
                list.add(h);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nk_table.setItems(list);
    }

    private void loadNK(){
        list_nk.clear();
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

                list_nk.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,nguyenQuan,danToc,tonGiao,quocTich));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nk_table_search.setItems(list_nk);
    }

    private void hoten_chu_ho_change(){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "SELECT nk.hoTen, nk.idNhanKhau FROM `ho_khau` hk, `nhan_khau` nk WHERE hk.idChuho = nk.idNhanKhau and hk.idHoKhau = ?";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String a = rs.getString("hoTen");
                int b = rs.getInt("idNhanKhau");
                hoten_chu_ho_change.setText(a);
                this.setId_chu_ho_new(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clear_hknk(){
        int idHoKhau = Integer.parseInt(id_ho_khau_change.getText());
        String qu = "DELETE FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setInt(1,idHoKhau);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
