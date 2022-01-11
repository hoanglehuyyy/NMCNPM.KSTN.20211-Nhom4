package repository;

import entity.ChiTietDipHocSinhGioi;
import entity.DipHocSinhGioi;
import entity.lienKet.NhanKhauHocSinhGioi;
import entity.lienKet.NhanKhauHokhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class HocSinhGioiRepositoryImpl implements HocSinhGioiRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @SneakyThrows
    @Override
    public ObservableList<DipHocSinhGioi> bangDipHocSinhGioi() {
        ObservableList<DipHocSinhGioi> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_BANG_DIP);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipHocSinhGioi dip = new DipHocSinhGioi();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQuaDacBiet(rs.getString("phanQuaDacBiet"));
            dip.setTienDacBiet(rs.getFloat("tienDacBiet"));
            dip.setPhanQuaGioi(rs.getString("phanQuaGioi"));
            dip.setTienGioi(rs.getFloat("tienGioi"));
            dip.setPhanQuaKha(rs.getString("phanQuaKha"));
            dip.setTienKha(rs.getFloat("tienKha"));
            dip.setSoNguoiChuaTraoThuong(rs.getInt("soNguoi"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @SneakyThrows
    @Override
    public ObservableList<DipHocSinhGioi> traCuuDipHocSinhGioi(Integer namHoc) {
        if (namHoc == null) return bangDipHocSinhGioi();
        ObservableList<DipHocSinhGioi> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_TRA_CUU_DIP);
        pstmt.setInt(1, namHoc);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipHocSinhGioi dip = new DipHocSinhGioi();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQuaDacBiet(rs.getString("phanQuaDacBiet"));
            dip.setTienDacBiet(rs.getFloat("tienDacBiet"));
            dip.setPhanQuaGioi(rs.getString("phanQuaGioi"));
            dip.setTienGioi(rs.getFloat("tienGioi"));
            dip.setPhanQuaKha(rs.getString("phanQuaKha"));
            dip.setTienKha(rs.getFloat("tienKha"));
            dip.setSoNguoiChuaTraoThuong(rs.getInt("soNguoi"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @SneakyThrows
    @Override
    public void xoaDipHocSinhGioi(int idDip) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_XOA_DIP_TRAO_THUONG);
        pstmt.setInt(1, idDip);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public void taoDipHocSinhGioi(int namHoc, String moTa, String phanQuaDacBiet, String phanQuaGioi, String phanQuaKha, float tienDacBiet, float tienGioi, float tienKha) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_TAO_MOI_DIP);
        pstmt.setInt(1, namHoc);
        pstmt.setString(2, moTa);
        pstmt.setString(3, phanQuaDacBiet);
        pstmt.setString(4, phanQuaGioi);
        pstmt.setString(5, phanQuaKha);
        pstmt.setFloat(6, tienDacBiet);
        pstmt.setFloat(7, tienGioi);
        pstmt.setFloat(8, tienKha);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public ObservableList<NhanKhauHokhau> bangThemMinhChung(int idDip) {
        ObservableList<NhanKhauHokhau> nhanKhauHokhau = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_HOC_SINH_GIOI_BANG_THEM_MINH_CHUNG);
        pstmt.setInt(1, idDip);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            NhanKhauHokhau nh = new NhanKhauHokhau();
            nh.setIdNhanKhau(rs.getInt("idNhanKhau"));
            nh.setTenNhanKhau(rs.getString("hoTen"));
            nh.setNgaySinh(rs.getDate("ngaySinh"));
            nh.setDiaChi(rs.getString("diaChi"));
            nhanKhauHokhau.add(nh);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return nhanKhauHokhau;
    }

    @SneakyThrows
    @Override
    public void themMinhChung(ChiTietDipHocSinhGioi chiTietDipHocSinhGioi) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_HOC_SINH_GIOI_THEM_MINH_CHUNG);
        pstmt.setInt(1, chiTietDipHocSinhGioi.getIdDip());
        pstmt.setInt(2, chiTietDipHocSinhGioi.getIdNhanKhau());
        pstmt.setString(3, chiTietDipHocSinhGioi.getTruong());
        pstmt.setInt(4, Integer.parseInt(chiTietDipHocSinhGioi.getLop()));
        pstmt.setInt(5, chiTietDipHocSinhGioi.getNhom());
        pstmt.setString(6, chiTietDipHocSinhGioi.getMinhChung());
        pstmt.setBoolean(7, chiTietDipHocSinhGioi.getKiemTra());
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public ObservableList<NhanKhauHocSinhGioi> bangNhanThuong(int idDip) {
        ObservableList<NhanKhauHocSinhGioi> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_HOC_SINH_GIOI_BANG_NHAN_THUONG);
        pstmt.setInt(1, idDip);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            NhanKhauHocSinhGioi n = new NhanKhauHocSinhGioi();
            n.setIdNhanKhau(rs.getInt("idNhanKhau"));
            n.setTenNhanKhau(rs.getString("hoTen"));
            n.setTruongHoc(rs.getString("truong"));
            n.setLopHoc(rs.getString("lop"));
            n.setThanhTich(NhanKhauHocSinhGioi.getThanhTich(rs.getInt("nhom")));
            n.setMinhChung(rs.getString("minhChung"));
            n.setKiemTra(rs.getBoolean("kiemtra"));
            list.add(n);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @SneakyThrows
    @Override
    public void kiemTraTraoThuong(int idDip, int idNhanKhau, boolean kiemTra) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_HOC_SINH_GIOI_KIEM_TRA);
        pstmt.setBoolean(1, kiemTra);
        pstmt.setInt(2, idDip);
        pstmt.setInt(3, idNhanKhau);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public void chinhSuaThongTinDip(int idDip, String moTa, String phanQuaDacBiet, String phanQuaGioi, String phanQuaKha, float tienDacBiet, float tienGioi, float tienKha) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_CHINH_SUA);
        pstmt.setString(1, phanQuaDacBiet);
        pstmt.setString(2, phanQuaGioi);
        pstmt.setString(3, phanQuaKha);
        pstmt.setFloat(4, tienDacBiet);
        pstmt.setFloat(5, tienGioi);
        pstmt.setFloat(6, tienKha);
        pstmt.setString(7, moTa);
        pstmt.setInt(8, idDip);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public DipHocSinhGioi traCuuDipByNam(int nam) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_TRA_CUU_BY_NAM);
        pstmt.setInt(1, nam);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();
            dipHocSinhGioi.setIdDip(rs.getInt("idDip"));
            dipHocSinhGioi.setNam(rs.getInt("nam"));
            dipHocSinhGioi.setPhanQuaDacBiet(rs.getString("phanQuaDacBiet"));
            dipHocSinhGioi.setPhanQuaGioi(rs.getString("phanQuaGioi"));
            dipHocSinhGioi.setPhanQuaKha(rs.getString("phanQuaKha"));
            dipHocSinhGioi.setTienDacBiet(rs.getFloat("tienDacBiet"));
            dipHocSinhGioi.setTienGioi(rs.getFloat("tienGioi"));
            dipHocSinhGioi.setTienKha(rs.getFloat("tienKha"));
            dipHocSinhGioi.setMoTa(rs.getString("moTa"));
            return dipHocSinhGioi;
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return null;
    }

    @SneakyThrows
    @Override
    public void chinhSuaMinhChung(int idDip, int idNhanKhau, String truong, String lop, int nhom, String minhChung) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_HOC_SINH_GIOI_SUA_MINH_CHUNG);
        pstmt.setString(1, truong);
        pstmt.setString(2, lop);
        pstmt.setInt(3, nhom);
        pstmt.setString(4, minhChung);
        pstmt.setInt(5, idDip);
        pstmt.setInt(6, idNhanKhau);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public void xoaMinhChung(int idDip, int idNhanKhau) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_HOC_SINH_GIOI_XOA_MINH_CHUNG);
        pstmt.setInt(1, idDip);
        pstmt.setInt(2, idNhanKhau);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @Override
    public ObservableList<DipHocSinhGioi> timNam(Integer nam) throws SQLException {
        ObservableList<DipHocSinhGioi> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_TIM_NAM);
        pstmt.setInt(1, nam);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipHocSinhGioi dip = new DipHocSinhGioi();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQuaDacBiet(rs.getString("phanQuaDacBiet"));
            dip.setTienDacBiet(rs.getFloat("tienDacBiet"));
            dip.setPhanQuaGioi(rs.getString("phanQuaGioi"));
            dip.setTienGioi(rs.getFloat("tienGioi"));
            dip.setPhanQuaKha(rs.getString("phanQuaKha"));
            dip.setTienKha(rs.getFloat("tienKha"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @Override
    public int dacBietNguoi(Integer idDip) {
        int dacBietNguoi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_DAC_BIET_NGUOI);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                dacBietNguoi = rs.getInt(1);
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

        return dacBietNguoi;
    }

    @Override
    public int gioiNguoi(Integer idDip) {
        int gioiNguoi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_GIOI_NGUOI);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                gioiNguoi = rs.getInt(1);
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

        return gioiNguoi;
    }

    @Override
    public int khaNguoi(Integer idDip) {
        int khaNguoi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_KHA_NGUOI);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                khaNguoi = rs.getInt(1);
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

        return khaNguoi;
    }

    @Override
    public int nguoiDaTrao(Integer idDip) {
        int nguoiDaTrao = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_NGUOI_DA_TRAO);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                nguoiDaTrao = rs.getInt(1);
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

        return nguoiDaTrao;
    }

    @Override
    public int nguoiChuaTrao(Integer idDip) {
        int nguoiChuaTrao = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_NGUOI_CHUA_TRAO);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                nguoiChuaTrao = rs.getInt(1);
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

        return nguoiChuaTrao;
    }

    @Override
    public int hoDaTrao(Integer idDip) {
        int hoDaTrao = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_HO_DA_TRAO);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                hoDaTrao = rs.getInt(1);
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

        return hoDaTrao;
    }

    @Override
    public int hoChuaTrao(Integer idDip) {
        int hoChuaTrao = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HOC_SINH_GIOI_QUERY_HO_CHUA_TRAO);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                hoChuaTrao = rs.getInt(1);
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

        return hoChuaTrao;
    }
}
