package repository;

import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.util.HashMap;

public class HoKhauRepositoryImpl implements  HoKhauRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public int tongHoKhauThuongTru() {
        int tongHoKhauThuongTru = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_TONG_THUONG_TRU);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongHoKhauThuongTru = rs.getInt(1);
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

        return tongHoKhauThuongTru;
    }

    @Override
    public int tongHoKhauDaChuyenDi() {
        int tongHoKhauDaChuyenDi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_TONG_DA_CHUYEN_DI);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                tongHoKhauDaChuyenDi = rs.getInt(1);
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

        return tongHoKhauDaChuyenDi;
    }

    @Override
    public HashMap<Integer, Integer> soLuongThanhVien() {
        HashMap<Integer, Integer> soLuongThanhVien = new HashMap<>();

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.HO_KHAU_QUERY_SO_LUONG_THANH_VIEN);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                soLuongThanhVien.put(Integer.valueOf(rs.getInt(1)), Integer.valueOf(rs.getInt(2)));
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

        return soLuongThanhVien;
    }
}
