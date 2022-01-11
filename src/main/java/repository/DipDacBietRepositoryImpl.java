package repository;

import entity.DipDacBiet;
import entity.NhanKhauDipDacBiet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.util.ArrayList;

public class DipDacBietRepositoryImpl implements DipDacBietRepository{
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @SneakyThrows
    @Override
    public ObservableList<DipDacBiet> bangDipDacBiet() {
        ObservableList<DipDacBiet> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_BANG_DIP);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipDacBiet dip = new DipDacBiet();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setTen(rs.getString("ten"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQua05(rs.getString("phanQua05"));
            dip.setPhanQua614(rs.getString("phanQua614"));
            dip.setPhanQua1517(rs.getString("phanQua1517"));
            dip.setTien05(rs.getFloat("tien05"));
            dip.setTien614(rs.getFloat("tien614"));
            dip.setTien1517(rs.getFloat("tien1517"));
            dip.setSoNguoiChuaTraoThuong(rs.getInt("soNguoi"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @SneakyThrows
    @Override
    public ObservableList<DipDacBiet> traCuuDipDacBiet(String ten, Integer nam) {
        if (ten == null && nam == null) return bangDipDacBiet();

        ObservableList<DipDacBiet> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();

        if (ten == null) ten = "";

        if (nam != null) {
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_TRA_CUU_DIP);
            pstmt.setString(1, "%" + ten + "%");
            pstmt.setInt(2, nam);
        }
        else {
            String command = SQLCommand.DIP_DAC_BIET_TRA_CUU_DIP.replace("and nam = ?", "");
            pstmt = conn.prepareStatement(command);
            pstmt.setString(1, "%" + ten + "%");
        }
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipDacBiet dip = new DipDacBiet();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setTen(rs.getString("ten"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQua05(rs.getString("phanQua05"));
            dip.setPhanQua614(rs.getString("phanQua614"));
            dip.setPhanQua1517(rs.getString("phanQua1517"));
            dip.setTien05(rs.getFloat("tien05"));
            dip.setTien614(rs.getFloat("tien614"));
            dip.setTien1517(rs.getFloat("tien1517"));
            dip.setSoNguoiChuaTraoThuong(rs.getInt("soNguoi"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @SneakyThrows
    @Override
    public DipDacBiet traCuuDipByTenNam(int nam, String ten) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_TRA_CUU_BY_TEN_NAM);
        pstmt.setString(1, ten);
        pstmt.setInt(2, nam);
        rs = pstmt.executeQuery();
        if (rs.next()) {
            DipDacBiet dip = new DipDacBiet();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setTen(rs.getString("ten"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQua05(rs.getString("phanQua05"));
            dip.setPhanQua614(rs.getString("phanQua614"));
            dip.setPhanQua1517(rs.getString("phanQua1517"));
            dip.setTien05(rs.getFloat("tien05"));
            dip.setTien614(rs.getFloat("tien614"));
            dip.setTien1517(rs.getFloat("tien1517"));
            DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
            return dip;
        }
        return null;
    }

    @SneakyThrows
    @Override
    public void taoDipDacBiet(String ten, int nam, String moTa, String phanQua05, String phanQua614, String phanQua1517, float tien05, float tien614, float tien1517) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_TAO_MOI_DIP);
        pstmt.setString(1, ten);
        pstmt.setInt(2, nam);
        pstmt.setString(3, moTa);
        pstmt.setString(4, phanQua05);
        pstmt.setString(5, phanQua614);
        pstmt.setString(6, phanQua1517);
        pstmt.setFloat(7, tien05);
        pstmt.setFloat(8, tien614);
        pstmt.setFloat(9, tien1517);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);

        ArrayList<Integer> listID = new ArrayList<>();
        ArrayList<Float> listTuoi = new ArrayList<>();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_DUOI_18_TUOI);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            listID.add(rs.getInt("idNhanKhau"));
            listTuoi.add(rs.getFloat("tuoi"));
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);

        DipDacBiet dipDacBiet = traCuuDipByTenNam(nam, ten);
        for (int i = 0; i < listID.size(); i ++) {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_DIP_DAC_BIET_THEM_NHAN_KHAU);
            pstmt.setInt(1, dipDacBiet.getIdDip());
            pstmt.setInt(2, listID.get(i));
            if (listTuoi.get(i) < 6) pstmt.setInt(3, 1);
            else {
                if (listTuoi.get(i) < 15) pstmt.setInt(3, 2);
                else pstmt.setInt(3, 3);
            }
            pstmt.setBoolean(4, false);
            pstmt.execute();
            DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        }
    }

    @SneakyThrows
    @Override
    public void xoaDipDacBiet(int idDip) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_XOA_DIP_TRAO_THUONG);
        pstmt.setInt(1, idDip);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public void chinhSuaThongTinDip(int idDip, String tenDip, String moTa, String phanQua05, String phanQua614, String phanQua1517, float tien05, float tien614, float tien1517) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_CHINH_SUA);
        pstmt.setString(1, tenDip);
        pstmt.setString(2, phanQua05);
        pstmt.setString(3, phanQua614);
        pstmt.setString(4, phanQua1517);
        pstmt.setFloat(5, tien05);
        pstmt.setFloat(6, tien614);
        pstmt.setFloat(7, tien1517);
        pstmt.setString(8, moTa);
        pstmt.setInt(9, idDip);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @SneakyThrows
    @Override
    public ObservableList<NhanKhauDipDacBiet> bangNhanThuong(int idDip) {
        ObservableList<NhanKhauDipDacBiet> nhanKhauDipDacBiet = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_DIP_DAC_BIET_BANG_NHAN_THUONG);
        pstmt.setInt(1, idDip);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            NhanKhauDipDacBiet n = new NhanKhauDipDacBiet();
            n.setIdNhanKhau(rs.getInt("idNhanKhau"));
            n.setTenNhanKhau(rs.getString("hoTen"));
            n.setNhom(NhanKhauDipDacBiet.getNhom(rs.getInt("nhom")));
            n.setKiemTra(rs.getBoolean("kiemtra"));
            nhanKhauDipDacBiet.add(n);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return nhanKhauDipDacBiet;
    }

    @Override
    public ObservableList<NhanKhauDipDacBiet> traCuuNhanThuong(String ten, int nhom) {
        return null;
    }

    @SneakyThrows
    @Override
    public void kiemTraTraoThuong(int idDip, int idNhanKhau, boolean kiemTra) {
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.CHI_TIET_DIP_DAC_BIET_KIEM_TRA);
        pstmt.setBoolean(1, kiemTra);
        pstmt.setInt(2, idDip);
        pstmt.setInt(3, idNhanKhau);
        pstmt.execute();
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
    }

    @Override
    public ObservableList<DipDacBiet> namDipDacBiet(Integer nam) throws SQLException {
        ObservableList<DipDacBiet> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_NAM);
        pstmt.setInt(1, nam);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipDacBiet dip = new DipDacBiet();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setTen(rs.getString("ten"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQua05(rs.getString("phanQua05"));
            dip.setPhanQua614(rs.getString("phanQua614"));
            dip.setPhanQua1517(rs.getString("phanQua1517"));
            dip.setTien05(rs.getFloat("tien05"));
            dip.setTien614(rs.getFloat("tien614"));
            dip.setTien1517(rs.getFloat("tien1517"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @Override
    public ObservableList<DipDacBiet> tenDipDacBiet(String ten) throws SQLException {
        ObservableList<DipDacBiet> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_TEN);
        pstmt.setString(1, ten);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipDacBiet dip = new DipDacBiet();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setTen(rs.getString("ten"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQua05(rs.getString("phanQua05"));
            dip.setPhanQua614(rs.getString("phanQua614"));
            dip.setPhanQua1517(rs.getString("phanQua1517"));
            dip.setTien05(rs.getFloat("tien05"));
            dip.setTien614(rs.getFloat("tien614"));
            dip.setTien1517(rs.getFloat("tien1517"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @Override
    public ObservableList<DipDacBiet> namTenDipDacBiet(Integer nam, String ten) throws SQLException {
        ObservableList<DipDacBiet> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_NAM_TEN);
        pstmt.setInt(1, nam);
        pstmt.setString(2, ten);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            DipDacBiet dip = new DipDacBiet();
            dip.setIdDip(rs.getInt("idDip"));
            dip.setTen(rs.getString("ten"));
            dip.setNam(rs.getInt("nam"));
            dip.setMoTa(rs.getString("moTa"));
            dip.setPhanQua05(rs.getString("phanQua05"));
            dip.setPhanQua614(rs.getString("phanQua614"));
            dip.setPhanQua1517(rs.getString("phanQua1517"));
            dip.setTien05(rs.getFloat("tien05"));
            dip.setTien614(rs.getFloat("tien614"));
            dip.setTien1517(rs.getFloat("tien1517"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @Override
    public int t05Nguoi(Integer idDip) {
        int t05Nguoi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_T05_NGUOI);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                t05Nguoi = rs.getInt(1);
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

        return t05Nguoi;
    }

    @Override
    public int t614Nguoi(Integer idDip) {
        int t614Nguoi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_T614_NGUOI);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                t614Nguoi = rs.getInt(1);
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

        return t614Nguoi;
    }

    @Override
    public int t1517Nguoi(Integer idDip) {
        int t1517Nguoi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_T1517_NGUOI);
            pstmt.setInt(1, idDip);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                t1517Nguoi = rs.getInt(1);
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

        return t1517Nguoi;
    }

    @Override
    public int nguoiDaTrao(Integer idDip) {
        int nguoiDaTrao = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_NGUOI_DA_TRAO);
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
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_NGUOI_CHUA_TRAO);
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
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_HO_DA_TRAO);
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
            pstmt = conn.prepareStatement(SQLCommand.DIP_DAC_BIET_QUERY_HO_CHUA_TRAO);
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
