package utility;

public class SQLCommand {
    // nguoi_dung
    public static String NGUOI_DUNG_QUERY_DANG_NHAP = "SELECT * FROM nguoi_dung WHERE taiKhoan = ? AND matKhau = ?";

    // nhan_khau
    public static String NHAN_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Thường trú'";
    public static String NHAN_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Đã chuyển đi'";
    public static String NHAN_KHAU_QUERY_TONG_DA_MAT = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N'Đã mất'";
    public static String NHAN_KHAU_QUERY_TONG_KHONG_XAC_DINH = "SELECT COUNT(*) FROM nhan_khau WHERE trangThai = N''";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_tru tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm trú'\n" +
//            "AND tt.denNgay > GETDATE()";
//    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*)\n" +
//            "FROM nhan_khau nk, tam_vang tt\n" +
//            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
//            "AND nk.trangThai = N'Tạm vắng'\n" +
//            "AND tt.denNgay > GETDATE()";    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";
    public static String NHAN_KHAU_QUERY_TONG_TAM_TRU = "SELECT COUNT(*)\n" +
            "FROM nhan_khau nk, tam_tru tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm trú'\n" +
            "AND tt.denNgay > NOW()";
    public static String NHAN_KHAU_QUERY_TONG_TAM_VANG = "SELECT COUNT(*)\n" +
            "FROM nhan_khau nk, tam_vang tt\n" +
            "WHERE nk.idNhanKhau = tt.idNhanKhau\n" +
            "AND nk.trangThai = N'Tạm vắng'\n" +
            "AND tt.denNgay > NOW()";    public static String NHAN_KHAU_QUERY_LAY_THONG_TIN = "SELECT * FROM nhan_khau";

    public static String NHAN_KHAU_QUERY_NAM = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nam'";
    public static String NHAN_KHAU_QUERY_NU = "SELECT COUNT(*) FROM nhan_khau WHERE gioiTinh = N'Nữ'";

//    public static String NHAN_KHAU_QUERY_MAM_NON = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) <= 5";
//    public static String NHAN_KHAU_QUERY_CAP_1 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 6 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 10";
//    public static String NHAN_KHAU_QUERY_CAP_2 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 11 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 14";
//    public static String NHAN_KHAU_QUERY_CAP_3 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 15 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 17";
//    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 18 AND YEAR(GETDATE()) - YEAR(ngaySinh) <= 64";
//    public static String NHAN_KHAU_QUERY_NGHI_HUU = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(GETDATE()) - YEAR(ngaySinh) >= 65";
    public static String NHAN_KHAU_QUERY_MAM_NON = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) <= 5";
    public static String NHAN_KHAU_QUERY_CAP_1 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 6 AND YEAR(NOW()) - YEAR(ngaySinh) <= 10";
    public static String NHAN_KHAU_QUERY_CAP_2 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 11 AND YEAR(NOW()) - YEAR(ngaySinh) <= 14";
    public static String NHAN_KHAU_QUERY_CAP_3 = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 15 AND YEAR(NOW()) - YEAR(ngaySinh) <= 17";
    public static String NHAN_KHAU_QUERY_DO_TUOI_LAO_DONG = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 18 AND YEAR(NOW()) - YEAR(ngaySinh) <= 64";
    public static String NHAN_KHAU_QUERY_NGHI_HUU = "SELECT COUNT(*) FROM nhan_khau WHERE YEAR(NOW()) - YEAR(ngaySinh) >= 65";


    // ho_khau
    public static String HO_KHAU_QUERY_TONG_THUONG_TRU = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'Thường trú'";
    public static String HO_KHAU_QUERY_TONG_DA_CHUYEN_DI = "SELECT COUNT(*) FROM ho_khau WHERE trangThai = N'Đã chuyển đi'";

//    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN = "SELECT soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
//            "FROM (SELECT hk.idHoKhau, ISNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
//            "\t\tFROM ho_khau hk\n" +
//            "\t\tLEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
//            "\t\tGROUP BY(hk.idHoKhau)) temp\n" +
//            "GROUP BY soLuong";
    public static String HO_KHAU_QUERY_SO_LUONG_THANH_VIEN = "SELECT soLuong, COUNT(idHoKhau) AS soHoKhau\n" +
            "FROM (SELECT hk.idHoKhau, IFNULL(COUNT(hknk.idNhanKhau), 0) + 1 AS soLuong\n" +
            "      FROM ho_khau hk\n" +
            "               LEFT JOIN ho_khau_nhan_khau hknk ON hk.idHoKhau = hknk.idHoKhau\n" +
            "      GROUP BY(hk.idHoKhau)) temp\n" +
            "GROUP BY soLuong";

    // ho_khau_nhan_khau

    // chuyen_ho_khau

    // chuyen_nhan_khau

    // tam_tru

    // tam_vang

    // phan thuong
    public static String PHAN_THUONG_NAM_SO_DIP = "select temp.nam, count(distinct ddb.idDip) + count(distinct dhsg.idDip) as soDip\n" +
            "from (select distinct nam\n" +
            "\t\tfrom dip_dac_biet \n" +
            "\t\tunion\n" +
            "\t\tselect distinct nam\n" +
            "\t\tfrom dip_hoc_sinh_gioi) temp\n" +
            "\tleft join dip_dac_biet ddb on temp.nam = ddb.nam\n" +
            "\tleft join dip_hoc_sinh_gioi dhsg on temp.nam = dhsg.nam\n" +
            "group by temp.nam";
    public static String PHAN_THUONG_NAM_SO_DIP_TIM = "select temp.nam, count(distinct ddb.idDip) + count(distinct dhsg.idDip) as soDip\n" +
            "from (select distinct nam\n" +
            "\t\tfrom dip_dac_biet \n" +
            "\t\tunion\n" +
            "\t\tselect distinct nam\n" +
            "\t\tfrom dip_hoc_sinh_gioi) temp\n" +
            "\tleft join dip_dac_biet ddb on temp.nam = ddb.nam\n" +
            "\tleft join dip_hoc_sinh_gioi dhsg on temp.nam = dhsg.nam\n" +
            "where temp.nam = ?\n" +
            "group by temp.nam";
    public static String PHAN_THUONG_TEN_DIP_NAM = "select ten\n" +
            "from dip_dac_biet\n" +
            "where nam = ?";
    public static String PHAN_THUONG_KIEM_TRA_HSG = "select count(idDip)\n" +
            "from dip_hoc_sinh_gioi\n" +
            "where nam = ?";

    // dip_dac_biet
    public static String DIP_DAC_BIET_QUERY_NAM = "select *\n" +
            "from dip_dac_biet\n" +
            "where nam = ?";
    public static String DIP_DAC_BIET_QUERY_TEN = "select *\n" +
            "from dip_dac_biet\n" +
            "where ten = ?";
    public static String DIP_DAC_BIET_QUERY_NAM_TEN = "select *\n" +
            "from dip_dac_biet\n" +
            "where nam = ?\n" +
            "and ten = ?";

    // dip_hoc_sinh_gioi
    public static String HOC_SINH_GIOI_QUERY_BANG_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_hoc_sinh_gioi d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_hoc_sinh_gioi d LEFT JOIN chi_tiet_dip_hoc_sinh_gioi c\n" +
            " ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            " GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip";
    public static String HOC_SINH_GIOI_QUERY_TIM_NAM = "SELECT * FROM dip_hoc_sinh_gioi WHERE nam = ?";


    // chi_tiet_dip_dac_biet
    public static String DIP_DAC_BIET_QUERY_BANG_DIP = "SELECT d.*, a.soNguoi \n" +
            "FROM dip_dac_biet d,\n" +
            "(SELECT d.idDip, COUNT(c.idNhanKhau) soNguoi FROM dip_dac_biet d LEFT JOIN chi_tiet_dip_dac_biet c\n" +
            " ON (d.idDip = c.idDip and c.kiemtra = 0)\n" +
            " GROUP BY d.idDip) a\n" +
            "WHERE d.idDip = a.idDip";
    public static String DIP_DAC_BIET_QUERY_T05_NGUOI = "select count(idNhanKhau) as t05Nguoi\n" +
            "from chi_tiet_dip_dac_biet\n" +
            "where nhom = 1\n" +
            "and idDip = ?";
    public static String DIP_DAC_BIET_QUERY_T614_NGUOI = "select count(idNhanKhau) as t614Nguoi\n" +
            "from chi_tiet_dip_dac_biet\n" +
            "where nhom = 2\n" +
            "and idDip = ?";
    public static String DIP_DAC_BIET_QUERY_T1517_NGUOI = "select count(idNhanKhau) as t1517Nguoi\n" +
            "from chi_tiet_dip_dac_biet\n" +
            "where nhom = 3\n" +
            "and idDip = ?";
    public static String DIP_DAC_BIET_QUERY_NGUOI_DA_TRAO = "select count(ctddb.idNhanKhau) as nguoiDaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 1";
    public static String DIP_DAC_BIET_QUERY_NGUOI_CHUA_TRAO = "select count(ctddb.idNhanKhau) as nguoiChuaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 0";
    public static String DIP_DAC_BIET_QUERY_HO_DA_TRAO = "select count(distinct hknk.idHoKhau) as hoDaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 1";
    public static String DIP_DAC_BIET_QUERY_HO_CHUA_TRAO = "select count(distinct hknk.idHoKhau) as hoChuaTraoDB\n" +
            "from chi_tiet_dip_dac_biet ctddb\n" +
            "left join ho_khau_nhan_khau hknk on ctddb.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctddb.idDip = ?\n" +
            "and ctddb.kiemtra = 0";

    // chi_tiet_dip_hoc_sinh_gioi
    public static String HOC_SINH_GIOI_QUERY_DAC_BIET_NGUOI = "select count(idNhanKhau) as dacBietNguoi\n" +
            "from chi_tiet_dip_hoc_sinh_gioi\n" +
            "where nhom = 1\n" +
            "and idDip = ?";
    public static String HOC_SINH_GIOI_QUERY_GIOI_NGUOI = "select count(idNhanKhau) as dacBietNguoi\n" +
            "from chi_tiet_dip_hoc_sinh_gioi\n" +
            "where nhom = 2\n" +
            "and idDip = ?";
    public static String HOC_SINH_GIOI_QUERY_KHA_NGUOI = "select count(idNhanKhau) as dacBietNguoi\n" +
            "from chi_tiet_dip_hoc_sinh_gioi\n" +
            "where nhom = 3\n" +
            "and idDip = ?";
    public static String HOC_SINH_GIOI_QUERY_NGUOI_DA_TRAO = "select count(ctdhsg.idNhanKhau) as nguoiDaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 1";
    public static String HOC_SINH_GIOI_QUERY_NGUOI_CHUA_TRAO = "select count(ctdhsg.idNhanKhau) as nguoiChuaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 0";
    public static String HOC_SINH_GIOI_QUERY_HO_DA_TRAO = "select count(distinct hknk.idHoKhau) as hoDaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 1";
    public static String HOC_SINH_GIOI_QUERY_HO_CHUA_TRAO = "select count(distinct hknk.idHoKhau) as hoChuaTraoHSG\n" +
            "from chi_tiet_dip_hoc_sinh_gioi ctdhsg\n" +
            "left join ho_khau_nhan_khau hknk on ctdhsg.idNhanKhau = hknk.idNhanKhau\n" +
            "where ctdhsg.idDip = ?\n" +
            "and ctdhsg.kiemtra = 0";
}
