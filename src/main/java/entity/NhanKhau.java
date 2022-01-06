package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

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


    public NhanKhau(int id,String hoTen, Date ngaySinh, String noiSinh, String gioiTinh, String nguyenQuan, String danToc, String tonGiao, String quocTich) {
        this.id = id;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.noiSinh = noiSinh;
        this.gioiTinh = gioiTinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.tonGiao = tonGiao;
        this.quocTich = quocTich;
    }

    public boolean sosanh(HoKhauNhanKhau a){
        if(this.id == a.getIdNhanKhau()) return true;
        return false;
    }
}
