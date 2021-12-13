package repository;

import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

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
            while(rs.next()) {
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
}
