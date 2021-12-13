package utility;

public class SQLCommand {
    // nguoi_dung
    public static String NGUOI_DUNG_QUERY_DANG_NHAP = "SELECT * FROM nguoi_dung WHERE taiKhoan = ? AND matKhau = ?";

    // nhan_khau
    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'" + Variable.NHAN_KHAU_THUONG_TRU + "'";
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'" + Variable.NHAN_KHAU_TAM_TRU + "'";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'" + Variable.NHAN_KHAU_TAM_VANG + "'";

    // ho_khau
    public static String HO_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'" + Variable.HO_KHAU_THUONG_TRU + "'";

    // ho_khau_nhan_khau

    // chuyen_ho_khau

    // chuyen_nhan_khau

    // tam_tru

    // tam_vang

    // dip_dac_biet

    // dip_hoc_sinh_gioi

    // chi_tiet_dip_dac_biet

    // chi_tiet_dip_hoc_sinh_gioi
}
