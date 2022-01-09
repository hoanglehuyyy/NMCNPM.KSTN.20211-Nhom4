package repository;

import entity.ChuyenHoKhau;
import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.NhanKhau;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.HashMap;

public interface HoKhauRepository {
    public int tongHoKhauThuongTru();
    public int tongHoKhauDaChuyenDi();
    public HashMap<Integer, Integer> soLuongThanhVien();

    //HoKhauController:
    public void delete_hk(int a);
    public void update_nk_after_delete(int a);
    public void update_ch_after_delete(int a);
    public ObservableList<HoKhau> loadDataHKController();

    //ThemHoKhauController:
    public boolean check_chu_ho(NhanKhau a);
    public boolean check_nhan_khau_exist_nk(int a);
    public void xac_nhan_button(int a, String b, String c, String d, String e, Date f, String g);
    public int idHoKhau_moi_nhat();
    public void themNhanKhau(int a, ObservableList<HoKhauNhanKhau> hknk_list);
    public void update_nk_after_add(int a);
    public void update_ch_after_add(int a);
    public ObservableList<NhanKhau> loadDataThemHKController();

    //XemHoKhauController:
    public String hoten_chu_ho(int a);
    public ObservableList<HoKhauNhanKhau> loadDataXemHoKhauController(int x);

    //SuaHoKhauController:
    public void change_inf_hk(int a,int b);
    public void update_nk_before_delete(int a);
    public void update_nk_after_change(int a);
    public void update_ch_before_delete(int a);
    public void update_ch_after_change(int a);
    public void change_inf_hknk(int x,ObservableList<HoKhauNhanKhau> list);
    public boolean check_nhan_khau_exist_nk_1(NhanKhau a,int idHoKhau);
    public boolean check_nhan_khau_exist_hk(NhanKhau a,int idHoKhau);
    public ObservableList<HoKhauNhanKhau> loadDataSuaHKController(int idHoKhau);
    public ObservableList<NhanKhau> loadNKSuaHKController();
    public int hoten_chu_ho_change_int(int idHoKhau);
    public String hoten_chu_ho_change_string(int idHoKhau);
    public void clear_hknk(int idHoKhau);

    //TachHoKhauController:
    public void update_nk_hk_hien_tai(int a);
    public void update_ch_hk_hien_tai(int a);
    public void update_id_ch_hien_tai(int a,ObservableList<HoKhauNhanKhau> list_hk_hien_tai);
    public void delete_all_nk_from_hk_hien_tai(int a);
    public void insert_all_nk_to_hk_hien_tai(int a,ObservableList<HoKhauNhanKhau> list_hk_hien_tai);
    public void update_all_nk_from_hk_hien_tai(int a);
    public void update_ch_from_hk_hien_tai(int a);
    public void create_new_hk(ObservableList<HoKhauNhanKhau> list_hk_moi,String a,String b, String c, String d, Date e);
    public int id_new_HoKhau();
    public void insert_all_nk_to_hk_moi(int a, ObservableList<HoKhauNhanKhau> list_hk_moi);
    public void update_new_ch(int a);
    public void update_new_nk(int a);
    public ObservableList<HoKhauNhanKhau> loadDataNKTachHKController(int idHoKhau);
    public HoKhauNhanKhau loadDataCHTachHKController(int idHoKhau);

    //ChuyenHoKhauController:
    public void chuyen_ho_khau(int idHoKhau, Date a, String b, String c);
    public void update_trangthai_hokhau(String a, String b, String c, String d, String trang_thai, int idHoKhau);
    public void update_trangthai_nhankhau(String trang_thai, int idHoKhau);
    public void update_trangthai_chuho(String trang_thai, int idHoKhau);

    //LichSuChuyenDiController:
    public ObservableList<ChuyenHoKhau> loadData(int idHoKhau);

}
