package repository;

import entity.DipHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class HocSinhGioiRepositoryImpl implements HocSinhGioiRepository {

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
