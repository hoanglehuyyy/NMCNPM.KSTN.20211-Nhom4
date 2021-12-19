package entity;

import java.util.Date;

public class NhanKhau {
    private int id;
    private String hoTen;
    private String biDanh;
    private String ngaySinh;
    private String noiSinh;
    private String gioiTinh;
    private String nguyenQuan;
    private String danToc;
    private String tonGiao;
    private String quocTich;
    private String ngheNghiep;
    private String noiLamViec;
    private String CMND;
    private Date ngayCap;
    private Date chuyenDenNgay;
    private String noiThuongTruTruoc;
    private String trangThai;


    public NhanKhau(int id, String hoTen,String ngaySinh, String gioiTinh, String CMND, String trangThai) {
        this.id=id;
        this.hoTen=hoTen;
        this.ngaySinh=ngaySinh;
        this.gioiTinh=gioiTinh;
        this.CMND=CMND;
        this.trangThai=trangThai;


    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id=id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen=hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh=ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh=gioiTinh;
    }

    public String getCMND() {
        return CMND;
    }

    public void setCMND(String CMND) {
        this.CMND=CMND;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai=trangThai;
    }


}
