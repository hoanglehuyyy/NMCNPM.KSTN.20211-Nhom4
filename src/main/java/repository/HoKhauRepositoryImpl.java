package repository;

import entity.ChuyenHoKhau;
import entity.HoKhau;
import entity.HoKhauNhanKhau;
import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.util.HashMap;

public class HoKhauRepositoryImpl implements  HoKhauRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public int tongHoKhauThuongTru() {
        int tongHoKhauThuongTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_TONG_THUONG_TRU);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongHoKhauThuongTru = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tongHoKhauThuongTru;
    }

    @Override
    public int tongHoKhauDaChuyenDi() {
        int tongHoKhauDaChuyenDi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_TONG_DA_CHUYEN_DI);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongHoKhauDaChuyenDi = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return tongHoKhauDaChuyenDi;
    }

    @Override
    public HashMap<Integer, Integer> soLuongThanhVien() {
        HashMap<Integer, Integer> soLuongThanhVien = new HashMap<>();

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_SO_LUONG_THANH_VIEN);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                soLuongThanhVien.put(Integer.valueOf(rs.getInt(1)), Integer.valueOf(rs.getInt(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return soLuongThanhVien;
    }

    public void delete_hk(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_DELETE_HK);
            pstmt.setInt(1,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }
    public void update_nk_after_delete(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NK_AFTER_DELETE);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }
    public void update_ch_after_delete(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_CH_AFTER_DELETE);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }
    public ObservableList<HoKhau> loadDataHKController(){
        ObservableList<HoKhau> f = FXCollections.observableArrayList();

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATAHOKHAUCONTROLLER);
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

                f.add(new HoKhau(id_hk,id_chu_ho_hk,hoten,thanhpho_hk,quanhuyen_hk,phuongxa_hk,address_hk,ngaytao_hk,trangthai_hk));

            }
            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return f;
        }
    }

    public boolean check_chu_ho(NhanKhau a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_CHU_HO);
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

    public boolean check_nhan_khau_exist_nk(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK);
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

    public void xac_nhan_button(int a, String b, String c, String d, String e, Date f, String g){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_XAC_NHAN_BUTTON);
            pstmt.setInt(1,a);
            pstmt.setString(2,b);
            pstmt.setString(3,c);
            pstmt.setString(4,d);
            pstmt.setString(5,e);
            pstmt.setDate(6,f);
            pstmt.setString(7,g);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public int idHoKhau_moi_nhat(){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_IDHOKHAU_MOI_NHAT);
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

    public void themNhanKhau(int a, ObservableList<HoKhauNhanKhau> hknk_list){

        for(HoKhauNhanKhau x : hknk_list){
            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_THEMNHANKHAU);
                pstmt.setInt(1,a);
                pstmt.setInt(2,x.getIdNhanKhau());
                pstmt.setString(3,x.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException ee){
                ee.printStackTrace();
            }
        }
    }

    public void update_nk_after_add(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NK_AFTER_ADD);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_ch_after_add(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_CH_AFTER_ADD);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public ObservableList<NhanKhau> loadDataThemHKController(){


        ObservableList<NhanKhau> f = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATATHEMHOKHAUCONTROLLER);
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

                f.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,CMND,danToc,tonGiao,quocTich,trangThai));
            }
            return f;
        } catch (SQLException e) {
            e.printStackTrace();
            return f;
        }
    }

    public String hoten_chu_ho(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_HOTEN_CHU_HO);
            pstmt.setInt(1,a);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String b = rs.getString("hoTen");
                return b;
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public ObservableList<HoKhauNhanKhau> loadDataXemHoKhauController(int x){

        ObservableList<HoKhauNhanKhau> t = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATAXEMHOKHAUCONTROLLER);
            pstmt.setInt(1,x);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int a = rs.getInt("idHoKhau");
                int b = rs.getInt("idNhanKhau");
                String c = rs.getString("quanHeChuHo");
                String d = rs.getString("hoTen");
                Date e = rs.getDate("ngaySinh");
                String f = rs.getString("cmnd");

                HoKhauNhanKhau h = new HoKhauNhanKhau(a,b,c,d,e,f);
                t.add(h);
            }
            return t;
        } catch (SQLException e) {
            e.printStackTrace();
            return t;
        }
    }

    public void change_inf_hk(int a,int b){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHANGE_INF_HK);
            pstmt.setInt(1,b);
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_nk_before_delete(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NK_BEFORE_DELETE);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_nk_after_change(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NK_AFTER_CHANGE);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_ch_before_delete(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_CH_BEFORE_DELETE);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_ch_after_change(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_CH_AFTER_CHANGE);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void change_inf_hknk(int x,ObservableList<HoKhauNhanKhau> list){

        for(HoKhauNhanKhau a : list){

            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHANGE_INF_HKNK);
                pstmt.setInt(1,x);
                pstmt.setInt(2,a.getIdNhanKhau());
                pstmt.setString(3,a.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean check_nhan_khau_exist_nk_1(NhanKhau a,int idHoKhau){


        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_NK_1);
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

    public boolean check_nhan_khau_exist_hk(NhanKhau a,int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHECK_NHAN_KHAU_EXIST_HK);
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

    public ObservableList<HoKhauNhanKhau> loadDataSuaHKController(int idHoKhau){


        ObservableList<HoKhauNhanKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATASUAHKCONTROLLER);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int a = rs.getInt("idHoKhau");
                int b = rs.getInt("idNhanKhau");
                String c = rs.getString("quanHeChuHo");
                String d = rs.getString("hoTen");
                Date e = rs.getDate("ngaySinh");
                String f = rs.getString("cmnd");

                HoKhauNhanKhau h = new HoKhauNhanKhau(a,b,c,d,e,f);
                fx.add(h);
            }
            return fx;
        } catch (SQLException e) {
            e.printStackTrace();
            return fx;
        }
    }

    public ObservableList<NhanKhau> loadNKSuaHKController(){

        ObservableList<NhanKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADNKSUAHKCONTROLLER);
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

                fx.add(new NhanKhau(idNhanKhau,hoTen,ngaySinh,noiSinh,gioiTinh,CMND,danToc,tonGiao,quocTich,trangThai));
            }
            return fx;
        } catch (SQLException e) {
            e.printStackTrace();
            return fx;
        }
    }

    public int hoten_chu_ho_change_int(int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE_INT);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int b = rs.getInt("idNhanKhau");
                return b;
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String hoten_chu_ho_change_string(int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_HOTEN_CHU_HO_CHANGE_STRING);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                String a = rs.getString("hoTen");
                return a;
            }
            return "";
        } catch (SQLException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void clear_hknk(int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CLEAR_HKNK);
            pstmt.setInt(1,idHoKhau);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_nk_hk_hien_tai(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NK_HK_HIEN_TAI);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_ch_hk_hien_tai(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_CH_HK_HIEN_TAI);
            pstmt.setString(1,"");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_id_ch_hien_tai(int a,ObservableList<HoKhauNhanKhau> list_hk_hien_tai){
        int idChuHo = 0;

        for(HoKhauNhanKhau x : list_hk_hien_tai){
            if(x.getQuanHeChuHo().equals("Chủ hộ")){
                idChuHo = x.getIdNhanKhau();
                break;
            }
        }

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_ID_CH_HIEN_TAI);
            pstmt.setInt(1,idChuHo);
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete_all_nk_from_hk_hien_tai(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_DELETE_ALL_NK_FROM_HK_HIEN_TAI);
            pstmt.setInt(1,a);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert_all_nk_to_hk_hien_tai(int a, ObservableList<HoKhauNhanKhau> list_hk_hien_tai){

        for(HoKhauNhanKhau t : list_hk_hien_tai){
            if(t.getQuanHeChuHo().equals("Chủ hộ")){
                continue;
            }
            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_HIEN_TAI);
                pstmt.setInt(1,a);
                pstmt.setInt(2,t.getIdNhanKhau());
                pstmt.setString(3,t.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update_all_nk_from_hk_hien_tai(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_ALL_NK_FROM_HK_HIEN_TAI);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_ch_from_hk_hien_tai(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_CH_FROM_HK_HIEN_TAI);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void create_new_hk(ObservableList<HoKhauNhanKhau> list_hk_moi,String a,String b, String c, String d, Date e){
        int idChuHo = 0;
        for(HoKhauNhanKhau i : list_hk_moi){
            if(i.getQuanHeChuHo().equals("Chủ hộ")){
                idChuHo = i.getIdNhanKhau();
                break;
            }
        }

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CREATE_NEW_HK);
            pstmt.setInt(1,idChuHo);
            pstmt.setString(2,a);
            pstmt.setString(3,b);
            pstmt.setString(4,c);
            pstmt.setString(5,d);
            pstmt.setDate(6,e);
            pstmt.setString(7,"Thường trú");
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public int id_new_HoKhau(){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_ID_NEW_HOKHAU);
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

    public void insert_all_nk_to_hk_moi(int a, ObservableList<HoKhauNhanKhau> list_hk_moi){

        for(HoKhauNhanKhau x : list_hk_moi){
            if(x.getQuanHeChuHo().equals("Chủ hộ")){
                continue;
            }
            try {
                conn = DbUtil.getInstance().getConnection();
                pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_INSERT_ALL_NK_TO_HK_MOI);
                pstmt.setInt(1,a);
                pstmt.setInt(2,x.getIdNhanKhau());
                pstmt.setString(3,x.getQuanHeChuHo());
                pstmt.executeUpdate();
            } catch (SQLException ee){
                ee.printStackTrace();
            }
        }
    }

    public void update_new_ch(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NEW_CH);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public void update_new_nk(int a){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_NEW_NK);
            pstmt.setString(1,"Thường trú");
            pstmt.setInt(2,a);
            pstmt.executeUpdate();
        } catch (SQLException ee){
            ee.printStackTrace();
        }
    }

    public ObservableList<HoKhauNhanKhau> loadDataNKTachHKController(int idHoKhau){


        ObservableList<HoKhauNhanKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATANKTACHHKCONTROLLER);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int m = rs.getInt("idNhanKhau");
                String n = rs.getString("quanHeChuHo");
                String p = rs.getString("hoTen");
                Date q = rs.getDate("ngaySinh");

                HoKhauNhanKhau x = new HoKhauNhanKhau(m,n,p,q);
                fx.add(x);
            }
            return fx;
        } catch (SQLException e) {
            e.printStackTrace();
            return fx;
        }
    }

    public HoKhauNhanKhau loadDataCHTachHKController(int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATACHTACHHKCONTROLLER);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                int m = rs.getInt("idNhanKhau");
                String p = rs.getString("hoTen");
                Date q = rs.getDate("ngaySinh");

                HoKhauNhanKhau x = new HoKhauNhanKhau(m,"Chủ hộ",p,q);
                return x;
            }
            return new HoKhauNhanKhau();
        } catch (SQLException e) {
            e.printStackTrace();
            return new HoKhauNhanKhau();
        }
    }

    public void chuyen_ho_khau(int idHoKhau, Date a, String b, String c){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_CHUYEN_HO_KHAU);
            pstmt.setInt(1,idHoKhau);
            pstmt.setDate(2,a);
            pstmt.setString(3,b);
            pstmt.setString(4,c);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_trangthai_hokhau(String a, String b, String c, String d, String trang_thai, int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_TRANGTHAI_HOKHAU);
            pstmt.setString(1,a);
            pstmt.setString(2,b);
            pstmt.setString(3,c);
            pstmt.setString(4,d);
            pstmt.setString(5,trang_thai);
            pstmt.setInt(6,idHoKhau);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_trangthai_nhankhau(String trang_thai, int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_TRANGTHAI_NHANKHAU);
            pstmt.setString(1,trang_thai);
            pstmt.setInt(2,idHoKhau);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update_trangthai_chuho(String trang_thai, int idHoKhau){

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_UPDATE_TRANGTHAI_CHUHO);
            pstmt.setString(1,trang_thai);
            pstmt.setInt(2,idHoKhau);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ChuyenHoKhau> loadData(int idHoKhau){

        ObservableList<ChuyenHoKhau> fx = FXCollections.observableArrayList();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_LOADDATA);
            pstmt.setInt(1,idHoKhau);
            rs = pstmt.executeQuery();
            while(rs.next()){
                int a = rs.getInt("id");
                int b = rs.getInt("idHoKhau");
                Date c = rs.getDate("ngayChuyenDi");
                String d = rs.getString("noiChuyenDen");
                String e = rs.getString("ghiChu");

                ChuyenHoKhau x = new ChuyenHoKhau(a,b,c,d,e);
                fx.add(x);
            }
            return fx;

        } catch (SQLException e) {
            e.printStackTrace();
            return fx;
        }
    }
}
