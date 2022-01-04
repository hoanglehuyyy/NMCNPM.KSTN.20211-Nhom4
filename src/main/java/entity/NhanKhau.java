package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDate ngayCap;
    private LocalDate chuyenDenNgay;
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

}
