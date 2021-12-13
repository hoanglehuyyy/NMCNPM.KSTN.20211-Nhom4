package repository;

import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class NhanKhauRepositoryImpl implements  NhanKhauRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public int tongNhanKhauThuongTru() {
        int tongNhanKhauThuongTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_THUONG_TRU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauThuongTru = rs.getInt(1);
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

        return tongNhanKhauThuongTru;
    }

    @Override
    public int tongNhanKhauTamTru() {
        int tongNhanKhauTamTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_TRU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauTamTru = rs.getInt(1);
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

        return tongNhanKhauTamTru;
    }

    @Override
    public int tongNhanKhauTamVang() {
        int tongNhanKhauTamVang = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_TAM_VANG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauTamVang = rs.getInt(1);
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

        return tongNhanKhauTamVang;
    }
}
