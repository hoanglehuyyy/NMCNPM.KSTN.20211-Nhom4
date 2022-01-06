package utility;

public class SQLCommand {
    // nguoi_dung
    public static String NGUOI_DUNG_QUERY_DANG_NHAP = "SELECT * FROM nguoi_dung WHERE taiKhoan = ? AND matKhau = ?";

    // nhan_khau
    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'" + Variable.NHAN_KHAU_THUONG_TRU + "'";
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'" + Variable.NHAN_KHAU_TAM_TRU + "'";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'" + Variable.NHAN_KHAU_TAM_VANG + "'";
    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau ";
    public static String XOA_NHAN_KHAU = "DELETE FROM `student` WHERE id  =";
    // ho_khau
    public static String HO_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'" + Variable.HO_KHAU_THUONG_TRU + "'";

    // ho_khau_nhan_khau

    // chuyen_ho_khau

    // chuyen_nhan_khau

    // tam_tru

    // tam_vang

    // dip_dac_biet

    // dip_hoc_sinh_gioi
    public static String HOC_SINH_GIOI_QUERY_BANG_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_hoc_sinh_gioi d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_hoc_sinh_gioi d LEFT JOIN chi_tiet_dip_hoc_sinh_gioi c\n" +
            " ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            " GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip";

    // chi_tiet_dip_dac_biet
    public static String DIP_DAC_BIET_QUERY_BANG_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_dac_biet d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_dac_biet d LEFT JOIN chi_tiet_dip_dac_biet c\n" +
            " ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            " GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip";

    // chi_tiet_dip_hoc_sinh_gioi
}
