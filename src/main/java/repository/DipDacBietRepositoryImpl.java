package repository;

import entity.DipDacBiet;
import entity.DipHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.time.format.DateTimeFormatterBuilder;

public class DipDacBietRepositoryImpl implements DipDacBietRepository {

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
