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

public class DipDacBietImpl implements DipDacBietRepository{
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
            dip.setSoNguoiChuaTraoThuong(rs.getInt("soNguoi"));
            list.add(dip);
        }
        DbUtil.releaseResource(rs, stmt, pstmt, cstmt, conn);
        return list;
    }
}
