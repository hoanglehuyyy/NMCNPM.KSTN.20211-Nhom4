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
    public int tongNhanKhauDaChuyenDi() {
        int tongNhanKhauDaChuyenDi = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauDaChuyenDi = rs.getInt(1);
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

        return tongNhanKhauDaChuyenDi;
    }

    @Override
    public int tongNhanKhauDaMat() {
        int tongNhanKhauDaMat = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_DA_MAT);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauDaMat = rs.getInt(1);
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

        return tongNhanKhauDaMat;
    }

    @Override
    public int tongNhanKhauKhongXacDinh() {
        int tongNhanKhauKhongXacDinh = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNhanKhauKhongXacDinh = rs.getInt(1);
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

        return tongNhanKhauKhongXacDinh;
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

    @Override
    public int tongNam() {
        int tongNam = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NAM);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNam = rs.getInt(1);
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

        return tongNam;
    }

    @Override
    public int tongNu() {
        int tongNu = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNu = rs.getInt(1);
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

        return tongNu;
    }

    @Override
    public int tongMamNon() {
        int tongMamNon = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_MAM_NON);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongMamNon = rs.getInt(1);
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

        return tongMamNon;
    }

    @Override
    public int tongCap1() {
        int tongCap1 = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_CAP_1);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongCap1 = rs.getInt(1);
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

        return tongCap1;
    }

    @Override
    public int tongCap2() {
        int tongCap2 = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_CAP_2);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongCap2 = rs.getInt(1);
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

        return tongCap2;
    }

    @Override
    public int tongCap3() {
        int tongCap3 = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_CAP_3);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongCap3 = rs.getInt(1);
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

        return tongCap3;
    }

    @Override
    public int tongDoTuoiLaoDong() {
        int tongDoTuoiLaoDong = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongDoTuoiLaoDong = rs.getInt(1);
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

        return tongDoTuoiLaoDong;
    }

    @Override
    public int tongNghiHuu() {
        int tongNghiHuu = 0;

        try {
            conn = DbUtil.getInstance().getConnection();
            pstmt = conn.prepareStatement(SQLCommand.NHAN_KHAU_QUERY_NGHI_HUU);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                tongNghiHuu = rs.getInt(1);
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

        return tongNghiHuu;
    }
}
