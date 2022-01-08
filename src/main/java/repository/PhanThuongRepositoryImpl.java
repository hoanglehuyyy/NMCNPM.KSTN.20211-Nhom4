package repository;

import entity.VO.NamSoDipVO;
import entity.VO.TenDipVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;

public class PhanThuongRepositoryImpl implements PhanThuongRepository {

    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

    @SneakyThrows
    @Override
    public ObservableList<NamSoDipVO> bangNamSoDip() {
        ObservableList<NamSoDipVO> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.PHAN_THUONG_NAM_SO_DIP);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            NamSoDipVO namSoDipVO = new NamSoDipVO();
            namSoDipVO.setNam(rs.getInt("nam"));
            namSoDipVO.setSoDip(rs.getInt("soDip"));
            list.add(namSoDipVO);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @SneakyThrows
    @Override
    public ObservableList<NamSoDipVO> bangNamSoDipTim(Integer nam) {
        ObservableList<NamSoDipVO> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.PHAN_THUONG_NAM_SO_DIP_TIM);
        pstmt.setInt(1, nam);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            NamSoDipVO namSoDipVO = new NamSoDipVO();
            namSoDipVO.setNam(rs.getInt("nam"));
            namSoDipVO.setSoDip(rs.getInt("soDip"));
            list.add(namSoDipVO);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }

    @Override
    public ObservableList<TenDipVO> bangTenDipTim(Integer nam) throws SQLException {
        ObservableList<TenDipVO> list = FXCollections.observableArrayList();
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.PHAN_THUONG_TEN_DIP_NAM);
        pstmt.setInt(1, nam);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            TenDipVO tenDipVO = new TenDipVO();
            tenDipVO.setTenDip(rs.getString(1));
            list.add(tenDipVO);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);

        if (kiemTraHSG(nam)) {
            list.add(new TenDipVO("Học sinh giỏi"));
        }
        return list;
    }

    @Override
    public boolean kiemTraHSG(Integer nam) throws SQLException {
        int ketQua = 0;
        conn = DbUtil.getInstance().getConnection();
        pstmt = conn.prepareStatement(SQLCommand.PHAN_THUONG_KIEM_TRA_HSG);
        pstmt.setInt(1, nam);
        rs = pstmt.executeQuery();
        while (rs.next()) {
            ketQua = rs.getInt(1);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);

        if (ketQua == 0) {
            return false;
        } else {
            return true;
        }
    }
}
