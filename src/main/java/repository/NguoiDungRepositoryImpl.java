package repository;

import entity.NguoiDung;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class NguoiDungRepositoryImpl implements NguoiDungRepository {
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @Override
    public boolean dangNhap(String taiKhoan, String matKhau) {
        Boolean kiemTra;

        NguoiDung nguoiDung = new NguoiDung();
        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NGUOI_DUNG_QUERY_DANG_NHAP);
            pstmt.setString(1, taiKhoan);
            pstmt.setString(2, matKhau);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                nguoiDung.setId(rs.getInt("id"));
                nguoiDung.setTaiKhoan(rs.getString("taiKhoan"));
                nguoiDung.setMatKhau(rs.getString("matKhau"));
                System.out.println(nguoiDung.getId());
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

        if (nguoiDung.getId() != null) {
            kiemTra = true;
        } else {
            kiemTra = false;
        }

        return kiemTra;
    }
}
