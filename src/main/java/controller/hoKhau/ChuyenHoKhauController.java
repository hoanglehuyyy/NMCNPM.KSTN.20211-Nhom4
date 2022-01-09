package controller.hoKhau;

import entity.HoKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.HoKhauRepositoryImpl;
import utility.DbUtil;

import java.sql.*;
import java.time.LocalDate;

public class ChuyenHoKhauController {
    @FXML
    private Label ma_ho_khau_chuyendi;
    @FXML
    private Label ho_ten_chu_ho_chuyendi;
    @FXML
    private Label dia_chi_hien_tai_chuyendi;
    @FXML
    private TextField dia_chi_chuyen_den_cd;
    @FXML
    private TextField tinh_thanh_cd;
    @FXML
    private TextField quan_huyen_cd;
    @FXML
    private TextField phuong_xa_cd;
    @FXML
    private TextArea li_do_cd;
    @FXML
    private DatePicker ngay_chuyen_den_datepicker;

    private String ngay_chuyen_den;

    private String tinh_thanh_string;
    private String quan_huyen_string;
    private String phuong_xa_string;

    public String getTinh_thanh_string() {
        return tinh_thanh_string;
    }

    public void setTinh_thanh_string(String tinh_thanh_string) {
        this.tinh_thanh_string = tinh_thanh_string;
    }

    public String getQuan_huyen_string() {
        return quan_huyen_string;
    }

    public void setQuan_huyen_string(String quan_huyen_string) {
        this.quan_huyen_string = quan_huyen_string;
    }

    public String getPhuong_xa_string() {
        return phuong_xa_string;
    }

    public void setPhuong_xa_string(String phuong_xa_string) {
        this.phuong_xa_string = phuong_xa_string;
    }

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    public String getNgay_chuyen_den() {
        return ngay_chuyen_den;
    }

    public void setNgay_chuyen_den(String ngay_chuyen_den) {
        this.ngay_chuyen_den = ngay_chuyen_den;
    }

    public void ngaychuyenden_datepicker(ActionEvent e){
        LocalDate a = ngay_chuyen_den_datepicker.getValue();
        this.setNgay_chuyen_den(a.toString());
    }

    public void chuyen_hk(HoKhau hk){
        ma_ho_khau_chuyendi.setText(String.valueOf(hk.getIdHoKhau()));
        ho_ten_chu_ho_chuyendi.setText(hk.getHotenChuho());
        dia_chi_hien_tai_chuyendi.setText(hk.getDiachi());
        this.setTinh_thanh_string(hk.getTinhThanhPho());
        this.setQuan_huyen_string(hk.getQuanHuyen());
        this.setPhuong_xa_string(hk.getPhuongXa());
    }

    public void huy_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    private boolean check_field(){
        if(dia_chi_chuyen_den_cd.getText().equals("")) return true;
        if(tinh_thanh_cd.getText().equals("")) return true;
        if(quan_huyen_cd.getText().equals("")) return true;
        if(phuong_xa_cd.getText().equals("")) return true;
        if(this.getNgay_chuyen_den() == null) return true;
        if(this.getNgay_chuyen_den().equals("")) return true;
        return false;
    }

    private void chuyen_ho_khau(){
        int idHoKhau = Integer.parseInt(ma_ho_khau_chuyendi.getText());
        HoKhauRepo.chuyen_ho_khau(idHoKhau,Date.valueOf(this.getNgay_chuyen_den()),dia_chi_chuyen_den_cd.getText(),li_do_cd.getText());
    }

    private boolean check_thuong_tru(){
        if((tinh_thanh_cd.getText().trim().toLowerCase()).equals(this.getTinh_thanh_string().trim().toLowerCase())
                && (quan_huyen_cd.getText().trim().toLowerCase()).equals(this.getQuan_huyen_string().trim().toLowerCase())
                && (phuong_xa_cd.getText().trim().toLowerCase()).equals(this.getPhuong_xa_string().trim().toLowerCase())) return true;
        return  false;
    }

    private void update_trangthai_hokhau(){
        int idHoKhau = Integer.parseInt(ma_ho_khau_chuyendi.getText());
        String trang_thai = "Đã chuyển đi";
        if(check_thuong_tru()){
            trang_thai = "Thường trú";
        }
        HoKhauRepo.update_trangthai_hokhau(dia_chi_chuyen_den_cd.getText(),tinh_thanh_cd.getText(),quan_huyen_cd.getText(),phuong_xa_cd.getText(),trang_thai,idHoKhau);
    }

    private void update_trangthai_nhankhau(){
        int idHoKhau = Integer.parseInt(ma_ho_khau_chuyendi.getText());
        String trang_thai = "Đã chuyển đi";
        if(check_thuong_tru()){
            trang_thai = "Thường trú";
        }
        HoKhauRepo.update_trangthai_nhankhau(trang_thai,idHoKhau);

    }

    private void update_trangthai_chuho(){
        int idHoKhau = Integer.parseInt(ma_ho_khau_chuyendi.getText());
        String trang_thai = "Đã chuyển đi";
        if(check_thuong_tru()){
            trang_thai = "Thường trú";
        }
        HoKhauRepo.update_trangthai_chuho(trang_thai,idHoKhau);
    }

    public void xac_nhan_button(ActionEvent e){
        if(check_field()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Các trường còn trống!");
            m.setContentText("Vui lòng nhập lại!");
            m.show();
            return;
        }

        update_trangthai_hokhau();
        update_trangthai_chuho();
        update_trangthai_nhankhau();
        chuyen_ho_khau();

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Chuyển hộ khẩu thành công!");
        m.show();

        huy_button(e);
    }


}
