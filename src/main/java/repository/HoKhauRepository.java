package repository;

import java.util.HashMap;

public interface HoKhauRepository {
    public int tongHoKhauThuongTru();
    public int tongHoKhauDaChuyenDi();

    public HashMap<Integer, Integer> soLuongThanhVien();
}
