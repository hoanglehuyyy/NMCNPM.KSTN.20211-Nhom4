package repository;

import entity.DipHocSinhGioi;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface HocSinhGioiRepository {
    public ObservableList<DipHocSinhGioi> bangDipHocSinhGioi();
    public ObservableList<DipHocSinhGioi> timNam(Integer nam) throws SQLException;
    public int dacBietNguoi(Integer idDip);
    public int gioiNguoi(Integer idDip);
    public int khaNguoi(Integer idDip);
    public int nguoiDaTrao(Integer idDip);
    public int nguoiChuaTrao(Integer idDip);
    public int hoDaTrao(Integer idDip);
    public int hoChuaTrao(Integer idDip);
}
