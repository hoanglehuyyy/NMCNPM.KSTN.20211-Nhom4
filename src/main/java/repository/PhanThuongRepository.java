package repository;

import entity.VO.NamSoDipVO;
import entity.VO.TenDipVO;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public interface PhanThuongRepository {
    public ObservableList<NamSoDipVO> bangNamSoDip();
    public ObservableList<NamSoDipVO> bangNamSoDipTim(Integer nam);
    public ObservableList<TenDipVO> bangTenDipTim(Integer nam) throws SQLException;
    public boolean kiemTraHSG(Integer nam) throws SQLException;
}
