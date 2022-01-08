package repository;

import entity.DipDacBiet;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface DipDacBietRepository {
    public ObservableList<DipDacBiet> bangDipDacBiet();
    public ObservableList<DipDacBiet> namDipDacBiet(Integer nam) throws SQLException;
    public ObservableList<DipDacBiet> tenDipDacBiet(String ten) throws SQLException;
    public ObservableList<DipDacBiet> namTenDipDacBiet(Integer nam, String ten) throws SQLException;

    public int t05Nguoi(Integer idDip);
    public int t614Nguoi(Integer idDip);
    public int t1517Nguoi(Integer idDip);

    public int nguoiDaTrao(Integer idDip);
    public int nguoiChuaTrao(Integer idDip);
    public int hoDaTrao(Integer idDip);
    public int hoChuaTrao(Integer idDip);
}
