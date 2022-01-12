package controller.hoKhau;

import entity.HoKhau;
import entity.HoKhauNhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import repository.HoKhauRepositoryImpl;
import utility.DbUtil;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class TachHoKhauController implements Initializable {

    @FXML
    private Label ma_chu_ho_hien_tai;
    @FXML
    private Label ma_ho_khau_hien_tai;
    @FXML
    private Label ho_ten_chu_ho_hien_tai;
    @FXML
    private TextField dia_chi_moi;
    @FXML
    private TextField tinh_thanh_moi;
    @FXML
    private TextField quan_huyen_moi;
    @FXML
    private TextField phuong_xa_moi;

    private String ngay_tao_moi;

    @FXML
    private DatePicker ngay_tao_moi_datepicker;

    private String NTMgetText(){
        return this.ngay_tao_moi;
    }

    private void NTMsetText(String s){
        this.ngay_tao_moi = s;
    }

    @FXML
    private TextField qhch_hien_tai;
    @FXML
    private TextField qhch_moi;

    @FXML
    private TableView<HoKhauNhanKhau> hk_hien_tai_tab;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hoten_hien_tai_col;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinh_hien_tai_col;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> quanhe_hien_tai_col;

    private ObservableList<HoKhauNhanKhau> list_hk_hien_tai = FXCollections.observableArrayList();

    @FXML
    private TableView<HoKhauNhanKhau> hk_moi_tab;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> hoten_moi_col;
    @FXML
    private TableColumn<HoKhauNhanKhau, Date> ngaysinh_moi_col;
    @FXML
    private TableColumn<HoKhauNhanKhau,String> quanhe_moi_col;

    private ObservableList<HoKhauNhanKhau> list_hk_moi = FXCollections.observableArrayList();

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    //Repo:
    static HoKhauRepositoryImpl HoKhauRepo = new HoKhauRepositoryImpl();

    public void ngaytaomoi_datepicker(ActionEvent e){
        LocalDate a = ngay_tao_moi_datepicker.getValue();
        this.NTMsetText(a.toString());
    }

    public void chon_ch_hien_tai(ActionEvent e){
        HoKhauNhanKhau a = hk_hien_tai_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : list_hk_hien_tai){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),"Chủ hộ",i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
            else if(i.getQuanHeChuHo().equals("Chủ hộ")){
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),"",i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
            else {
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
        }
        list_hk_hien_tai.clear();
        list_hk_hien_tai.addAll(f);
        hk_hien_tai_tab.setItems(list_hk_hien_tai);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Chọn chủ hộ mới thành công.");
        m.setContentText("Vui lòng chọn lại.");
        m.show();
        return;
    }

    public void sua_quanhe_hientai(ActionEvent e){
        HoKhauNhanKhau a = hk_hien_tai_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if(qhch_hien_tai.getText().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Thiếu trường quan hệ với chủ hộ hiện tại.");
            m.setContentText("Vui lòng nhập lại.");
            m.show();
            return;
        }

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : list_hk_hien_tai){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),qhch_hien_tai.getText(),i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
            else{
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
        }

        list_hk_hien_tai.clear();
        list_hk_hien_tai.addAll(f);
        hk_hien_tai_tab.setItems(list_hk_hien_tai);

    }

    public void chon_ch_moi(ActionEvent e){
        HoKhauNhanKhau a = hk_moi_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : list_hk_moi){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),"Chủ hộ",i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
            else if(i.getQuanHeChuHo().equals("Chủ hộ")){
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),"",i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
            else {
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
        }
        list_hk_moi.clear();
        list_hk_moi.addAll(f);
        hk_moi_tab.setItems(list_hk_moi);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Chọn chủ hộ mới thành công.");
        m.setContentText("Vui lòng chọn lại.");
        m.show();
        return;
    }

    public void sua_quanhe_moi(ActionEvent e){
        HoKhauNhanKhau a = hk_moi_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        if(qhch_moi.getText().equals("")){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Thiếu trường quan hệ với chủ hộ mới.");
            m.setContentText("Vui lòng nhập lại.");
            m.show();
            return;
        }

        ObservableList<HoKhauNhanKhau> f = FXCollections.observableArrayList();
        for(HoKhauNhanKhau i : list_hk_moi){
            if(i.getIdNhanKhau() == a.getIdNhanKhau()){
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),qhch_moi.getText(),i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
            else{
                HoKhauNhanKhau t = new HoKhauNhanKhau(i.getIdNhanKhau(),i.getQuanHeChuHo(),i.getHoTen(),i.getNgaySinh());
                f.add(t);
            }
        }

        list_hk_moi.clear();
        list_hk_moi.addAll(f);
        hk_moi_tab.setItems(list_hk_moi);
    }

    public void chon_nk_sang_hk_moi(ActionEvent e){
        HoKhauNhanKhau a = hk_hien_tai_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getIdNhanKhau(),"",a.getHoTen(),a.getNgaySinh());
        list_hk_hien_tai.remove(a);
        list_hk_moi.add(b);
        hk_hien_tai_tab.setItems(list_hk_hien_tai);
        hk_moi_tab.setItems(list_hk_moi);
    }

    public void chon_nk_sang_hk_hien_tai(ActionEvent e){
        HoKhauNhanKhau a = hk_moi_tab.getSelectionModel().getSelectedItem();
        if(a == null){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Chưa chọn nhân khẩu nào.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        HoKhauNhanKhau b = new HoKhauNhanKhau(a.getIdNhanKhau(),"",a.getHoTen(),a.getNgaySinh());
        list_hk_moi.remove(a);
        list_hk_hien_tai.add(b);
        hk_hien_tai_tab.setItems(list_hk_hien_tai);
        hk_moi_tab.setItems(list_hk_moi);
    }

    public void tach_hk(HoKhau a){
        ma_chu_ho_hien_tai.setText(String.valueOf(a.getIdChuHo()));
        ho_ten_chu_ho_hien_tai.setText(a.getHotenChuho());
        ma_ho_khau_hien_tai.setText(String.valueOf(a.getIdHoKhau()));

        loadData();
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void xac_nhan_button(ActionEvent e){
        if(check_empty()){
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Thông báo!");
            m.setHeaderText("Còn trường trống, chưa chọn chủ hộ hoặc chưa thêm quan hệ.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        int idHoKhau = Integer.parseInt(ma_ho_khau_hien_tai.getText());

        update_nk_hk_hien_tai(idHoKhau);
        update_ch_hk_hien_tai(idHoKhau);
        update_id_ch_hien_tai(idHoKhau);
        delete_all_nk_from_hk_hien_tai(idHoKhau);
        insert_all_nk_to_hk_hien_tai(idHoKhau);
        update_all_nk_from_hk_hien_tai(idHoKhau);
        update_ch_from_hk_hien_tai(idHoKhau);

        create_new_hk();
        int idHoKhauNew = id_new_HoKhau();
        insert_all_nk_to_hk_moi(idHoKhauNew);
        update_new_ch(idHoKhauNew);
        update_new_nk(idHoKhauNew);
        update_chuyen_ho_khau(idHoKhauNew);

        Alert m = new Alert(Alert.AlertType.INFORMATION);
        m.setTitle("Thông báo!");
        m.setHeaderText("Tách hộ khẩu thành công.");
        m.show();

        close_button(e);
    }

    //B0: CHeck các trường có trống không:
    private boolean check_empty(){
        if(dia_chi_moi.getText().equals("")) return true;
        if(tinh_thanh_moi.getText().equals("")) return true;
        if(quan_huyen_moi.getText().equals("")) return true;
        if(phuong_xa_moi.getText().equals("")) return true;
        if(this.NTMgetText() == null) return true;
        if(this.NTMgetText().equals("")) return true;
        int count = 0;
        int count1 = 0;
        for(HoKhauNhanKhau i : list_hk_hien_tai){
            if(i.getQuanHeChuHo().equals("Chủ hộ")){
                count = 1;
            }
            if(i.getQuanHeChuHo().equals("")){
                return true;
            }
        }
        for(HoKhauNhanKhau i : list_hk_moi){
            if(i.getQuanHeChuHo().equals("Chủ hộ")){
                count1 = 1;
            }
            if(i.getQuanHeChuHo().equals("")){
                return true;
            }
        }
        if(count == 0) return true;
        if(count1 == 0) return true;
        return false;
    }

    //B1: update trang thai nhan khau trong hk_hien_tai:

    private void update_nk_hk_hien_tai(int a){
        HoKhauRepo.update_nk_hk_hien_tai(a);
    }

    //B2: update trang thai chu ho trong hk_hien_tai:

    private void update_ch_hk_hien_tai(int a){
        HoKhauRepo.update_ch_hk_hien_tai(a);
    }

    //B3: cap nhat chu ho cho hk_hien_tai:

    private void update_id_ch_hien_tai(int a){
        HoKhauRepo.update_id_ch_hien_tai(a,this.list_hk_hien_tai);
    }

    //B4: xoa tat ca thanh vien trong ho khau hien tai:

    private void delete_all_nk_from_hk_hien_tai(int a){
        HoKhauRepo.delete_all_nk_from_hk_hien_tai(a);
    }

    //B5: them thanh vien moi vao ho khau hien tai:
    private void insert_all_nk_to_hk_hien_tai(int a){
        HoKhauRepo.insert_all_nk_to_hk_hien_tai(a,this.list_hk_hien_tai);
    }

    //B6: thay đổi trạng thái thành viên mới:
    private void update_all_nk_from_hk_hien_tai(int a){
        HoKhauRepo.update_all_nk_from_hk_hien_tai(a);
    }

    //B7: thay đổi trạng thái chủ hộ mới:
    private void update_ch_from_hk_hien_tai(int a){
        HoKhauRepo.update_ch_from_hk_hien_tai(a);
    }

    //B8: Tạo hộ khẩu mới:
    private void create_new_hk(){
        HoKhauRepo.create_new_hk(this.list_hk_moi,tinh_thanh_moi.getText(),quan_huyen_moi.getText(),phuong_xa_moi.getText(),dia_chi_moi.getText(),Date.valueOf(this.NTMgetText()));

    }

    //B9: Lấy idHoKhau của hộ khẩu mới:
    private int id_new_HoKhau(){
        return HoKhauRepo.id_new_HoKhau();
    }

    //B10: Thêm nhân khẩu vào hộ khẩu mới:
    private void insert_all_nk_to_hk_moi(int a){
        HoKhauRepo.insert_all_nk_to_hk_moi(a,this.list_hk_moi);
    }

    //B11: Thay đổi trạng thái chủ hộ mới:
    private void update_new_ch(int a){
        HoKhauRepo.update_new_ch(a);
    }
    //B12: Thay đổi trạng thái nhân khẩu mới:
    private void update_new_nk(int a){
        HoKhauRepo.update_new_nk(a);

    }

    private void update_chuyen_ho_khau(int a){
        HoKhauRepo.chuyen_ho_khau(a,Date.valueOf(this.NTMgetText()),dia_chi_moi.getText(),"Tạo hộ khẩu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
    }

    private void initCol(){
        hoten_hien_tai_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinh_hien_tai_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanhe_hien_tai_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
        hoten_moi_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("hoTen"));
        ngaysinh_moi_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,Date>("ngaySinh"));
        quanhe_moi_col.setCellValueFactory(new PropertyValueFactory<HoKhauNhanKhau,String>("quanHeChuHo"));
    }

    private void loadData(){
        list_hk_hien_tai.clear();
        loadDataCH();
        loadDataNK();
    }

    private void loadDataNK(){
        int idHoKhau = Integer.parseInt(ma_ho_khau_hien_tai.getText());
        list_hk_hien_tai.addAll(HoKhauRepo.loadDataNKTachHKController(idHoKhau));
        hk_hien_tai_tab.setItems(list_hk_hien_tai);

    }

    private void loadDataCH(){
        int idHoKhau = Integer.parseInt(ma_ho_khau_hien_tai.getText());
        list_hk_hien_tai.add(HoKhauRepo.loadDataCHTachHKController(idHoKhau));
        hk_hien_tai_tab.setItems(list_hk_hien_tai);
    }

}
