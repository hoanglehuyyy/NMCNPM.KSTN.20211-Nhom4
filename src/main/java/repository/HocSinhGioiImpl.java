package repository;

import entity.DipHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.SneakyThrows;
import utility.DbUtil;
import utility.SQLCommand;

import java.sql.*;
import java.time.format.DateTimeFormatterBuilder;

public class HocSinhGioiImpl implements HocSinhGioiRepository{
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
}
