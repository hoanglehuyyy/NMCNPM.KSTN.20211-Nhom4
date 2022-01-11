package repository;

import entity.ChiTietDipHocSinhGioi;
import entity.DipHocSinhGioi;
import entity.lienKet.NhanKhauHocSinhGioi;
import entity.lienKet.NhanKhauHokhau;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface HocSinhGioiRepository {
    ObservableList<DipHocSinhGioi> bangDipHocSinhGioi();
    ObservableList<DipHocSinhGioi> traCuuDipHocSinhGioi(Integer namHoc);
    void xoaDipHocSinhGioi(int idDip);
    void taoDipHocSinhGioi(int namHoc, String moTa, String phanQuaDacBiet, String phanQuaGioi, String phanQuaKha, float tienDacBiet, float tienGioi, float tienKha);
    ObservableList<NhanKhauHokhau> bangThemMinhChung(int idDip);
    void themMinhChung(ChiTietDipHocSinhGioi chiTietDipHocSinhGioi);
    ObservableList<NhanKhauHocSinhGioi> bangNhanThuong(int idDip);
    void kiemTraTraoThuong(int idDip, int idNhanKhau, boolean kiemTra);
    void chinhSuaThongTinDip(int idDip, String moTa, String phanQuaDacBiet, String phanQuaGioi, String phanQuaKha, float tienDacBiet, float tienGioi, float tienKha);
    DipHocSinhGioi traCuuDipByNam(int nam);
    void chinhSuaMinhChung(int idDip, int idNhanKhau, String truong, String lop, int nhom, String minhChung);
    void xoaMinhChung(int idDip, int idNhanKhau);

    ObservableList<DipHocSinhGioi> timNam(Integer nam) throws SQLException;

    int dacBietNguoi(Integer idDip);

    int gioiNguoi(Integer idDip);

    int khaNguoi(Integer idDip);

    int nguoiDaTrao(Integer idDip);

    int nguoiChuaTrao(Integer idDip);

    int hoDaTrao(Integer idDip);

    int hoChuaTrao(Integer idDip);
}
