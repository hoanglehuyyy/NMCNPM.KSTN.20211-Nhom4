package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NhanKhau {
    private int id;
    private String hoTen;
    private String biDanh;
    private Date ngaySinh;
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
    private String bieuDienNgaySinh;



    public NhanKhau(int id, String hoTen, String bieuDienNgaySinh, String gioiTinh, String CMND, String trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.bieuDienNgaySinh = bieuDienNgaySinh;
        this.gioiTinh = gioiTinh;
        this.CMND = CMND;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }


    public String getBieuDienNgaySinh() {
        return bieuDienNgaySinh;
    }


    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
