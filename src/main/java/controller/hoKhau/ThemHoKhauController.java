package controller.hoKhau;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utility.DbUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
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
    private String ngaytao_text;
    @FXML
    private TextField hovaten_chuho;
    @FXML
    private TextField ngaysinh_chuho;
    @FXML
    private TextField CMND_chuho;
    @FXML
    private TextField quanhe_chuho;
    @FXML
    private TableView<NhanKhau> nhankhauTab;
    @FXML
    private TableColumn<NhanKhau,String> hovaten_nkCol;
    @FXML
    private TableColumn<NhanKhau,Date> ngaysinh_nkCol;
    @FXML
    private TableColumn<NhanKhau,String> CMND_nkCol;

    private ObservableList<NhanKhau> dsnk = FXCollections.observableArrayList();

    private ObservableList<NhanKhau> search_dsnk = FXCollections.observableArrayList();

    private int id_chu_ho = -1;

    private void NTsetText(String a){
        this.ngaytao_text = a;
    }

    private String NTgetText(){
        return this.ngaytao_text;
    }

    //Datepicker:

    @FXML
    private DatePicker ngay_tao_datepicker;

    //Nhan Khau:
    @FXML
    private TableView<HoKhauNhanKhau> nk_table;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hoten_nk_Col;
    @FXML
    private TableColumn<HoKhauNhanKhau,Date> ngaysinh_nk_Col;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> quanhe_nk_Col;

    private ObservableList<HoKhauNhanKhau> hknk_list = FXCollections.observableArrayList();

    @FXML
    private Label ma_chu_ho_duoc_chon;
    @FXML
    private Label ho_ten_chu_ho_duoc_chon;

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

    public void ngaytao_datepicker(ActionEvent e){
        LocalDate a = ngay_tao_datepicker.getValue();
        this.NTsetText(a.toString());
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
        if(a.getTrangThai() == null || !a.getTrangThai().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu không hợp lệ");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        this.id_chu_ho = a.getId();
        ma_chu_ho_duoc_chon.setText(String.valueOf(a.getId()));
        ho_ten_chu_ho_duoc_chon.setText(a.getHoTen());
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
        if(this.NTgetText() == null) return false;
        if(this.NTgetText().equals("")) return false;
        if(this.id_chu_ho == -1) return false;
        return true;
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
            m.setTitle("Thông báo!");
            m.setHeaderText("Các trường bắt buộc còn trống");
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
            pstmt.setDate(6,Date.valueOf(this.NTgetText()));
            pstmt.setString(7,"Thường trú");
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }

        int idHoKhau = idHoKhau_moi_nhat();
        themNhanKhau(idHoKhau);
        update_nk_after_add(idHoKhau);
        update_ch_after_add(idHoKhau);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Thêm hộ khẩu mới thành công");
        m.show();

        huy_button(e);
    }

    private int idHoKhau_moi_nhat(){
        String qu = "SELECT idHoKhau FROM `ho_khau` ORDER BY idHoKhau DESC";

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            rs = pstmt.executeQuery();
            while (rs.next()){
                int a = rs.getInt("idHoKhau");
                return a;
            }
        } catch (SQLException ee){
            ee.printStackTrace();
            return 0;
        }
        return 0;
    }

    private void themNhanKhau(int a){
        String qu = "INSERT INTO `ho_khau_nhan_khau` VALUES (?,?,?)";
        for(HoKhauNhanKhau x : hknk_list){
            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(qu);
                pstmt.setInt(1,a);
                pstmt.setInt(2,x.getIdNhanKhau());
                pstmt.setString(3,x.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException ee){
                ee.printStackTrace();
            }
        }

    }

    private void update_nk_after_add(int a){
        String qu = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idNhanKhau FROM `ho_khau_nhan_khau` WHERE idHoKhau = ?)";
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    private void update_ch_after_add(int a){
        String qu = "UPDATE `nhan_khau` SET trangThai = ? WHERE idNhanKhau IN (SELECT idChuHo FROM `ho_khau` WHERE idHoKhau = ?)";
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(qu);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void tim_button(ActionEvent e) throws IOException {
        search_dsnk.clear();
        String search_hovaten = hovaten_chuho.getText().trim().toLowerCase();
        String search_ngaysinh = ngaysinh_chuho.getText().trim().toLowerCase();
        String search_CMND = CMND_chuho.getText().trim().toLowerCase();
        for(NhanKhau a : dsnk){
            if(a.getCMND() == null){
                a.setCMND("");
            }
            if((a.getHoTen().trim().toLowerCase()).contains(search_hovaten) && (String.valueOf(a.getNgaySinh()).trim().toLowerCase()).contains(search_ngaysinh) && (a.getCMND().trim().toLowerCase()).contains(search_CMND)){
                NhanKhau b = new NhanKhau(a.getId(),a.getHoTen(),a.getNgaySinh(),a.getNoiSinh(),a.getGioiTinh(),a.getCMND(),a.getDanToc(),a.getTonGiao(),a.getQuocTich(),a.getTrangThai());
                search_dsnk.add(b);
            }

        }
        nhankhauTab.setItems(search_dsnk);
    }

    public void huy_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void them_button(ActionEvent e){
        NhanKhau a = nhankhauTab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(a.getId() == this.getId_chu_ho()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu trùng với chủ hộ");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(check_nhan_khau_exist_nk(a.getId()) || check_chu_ho(a)){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu đã thuộc hộ khẩu khác");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(a.getTrangThai() == null || !a.getTrangThai().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Nhân khẩu không hợp lệ");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(quanhe_chuho.getText().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Thiếu trường quan hệ với chủ hộ");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }
        for(HoKhauNhanKhau t : hknk_list){
            if(a.getId() == t.getIdNhanKhau()){
                Alert m = new Alert(Alert.AlertType.INFORMATION);
                m.setTitle("Thông báo!");
                m.setHeaderText("Nhân khẩu đã được thêm");
                m.setContentText("Vui lòng chọn lại");
                m.show();
                return;
            }
        }

        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getId(),quanhe_chuho.getText(),a.getHoTen(),a.getNgaySinh());
        hknk_list.add(b);
        nk_table.setItems(hknk_list);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Thêm nhân khẩu thành công");
        m.show();
        return;
    }

    public void sua_button(ActionEvent e){
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        if(quanhe_chuho.getText().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Thiếu trường quan hệ với chủ hộ");
            m.setContentText("Vui lòng nhập lại");
            m.show();
            return;
        }

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : hknk_list){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau b = new HoKhauNhanKhau(i.getIdNhanKhau(),quanhe_chuho.getText(),i.getHoTen(),i.getNgaySinh());
                f.add(b);
            }
            else {
                HoKhauNhanKhau b = new HoKhauNhanKhau(i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh());
                f.add(b);
            }
        }

        hknk_list.clear();
        hknk_list.addAll(f);
        nk_table.setItems(hknk_list);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Sửa nhân khẩu thành công");
        m.show();
        return;
    }

    public void xoa_button(ActionEvent e){
        HoKhauNhanKhau a = nk_table.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu");
            m.setContentText("Vui lòng chọn lại");
            m.show();
            return;
        }
        hknk_list.remove(a);
        nk_table.setItems(hknk_list);
        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Xoá nhân khẩu thành công");
        m.show();
        return;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void initCol(){
        hovaten_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("hoTen"));
        ngaysinh_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,Date>("ngaySinh"));
        CMND_nkCol.setCellValueFactory(new PropertyValueFactory<NhanKhau,String>("CMND"));
        hoten_nk_Col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinh_nk_Col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanhe_nk_Col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
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
                String CMND = rs.getString("cmnd");
                String danToc = rs.getString("danToc");
                String tonGiao = rs.getString("tonGiao");
                String quocTich = rs.getString("quocTich");
                String trangThai = rs.getString("trangThai");

                dsnk.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,CMND,danToc,tonGiao,quocTich,trangThai));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        nhankhauTab.setItems(dsnk);
    }
}
