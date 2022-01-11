package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utility.Variable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NhanKhauHocSinhGioi {
    private Integer idNhanKhau;
    private String tenNhanKhau;
    private String truongHoc;
    private String lopHoc;
    private String thanhTich;
    private String minhChung;
    private Boolean kiemTra;

    public static String getThanhTich(int nhom) {
        if (nhom == 1) return Variable.DAC_BiET;
        if (nhom == 2) return Variable.GIOI;
        if (nhom == 3) return Variable.KHA;
        return null;
    }

    public static Integer getNhom(String thanhTich) {
        if (thanhTich.equals(Variable.DAC_BiET)) return 1;
        if (thanhTich.equals(Variable.GIOI)) return 2;
        if (thanhTich.equals(Variable.KHA)) return 3;
        return null;
    }
}
