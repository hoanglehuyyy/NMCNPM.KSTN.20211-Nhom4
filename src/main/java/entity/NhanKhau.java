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
    private Integer idNhanKhau;
    private String hoTen;
    private String biDanh;
    private LocalDate ngaySinh;
    private String noiSinh;
    private String gioiTinh;
    private String nguyenQuan;
    private String danToc;
    private String tonGiao;
    private String quocTich;
    private String ngheNghiep;
    private String noiLamViec;
    private int cmnd;
    private LocalDate ngayCap;
    private LocalDate chuyenDenNgay;
    private String noiThuongTruTruoc;
    private String trangThai;
}
