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
public class NhanKhauDipDacBiet {
    private int idNhanKhau;
    private String tenNhanKhau;
    private String nhom;
    private Boolean kiemTra;

    public static Integer getNhom(String nhom) {
        if (nhom.equals(Variable.T05)) return 1;
        if (nhom.equals(Variable.T614)) return 2;
        if (nhom.equals(Variable.T1517)) return 3;
        return null;
    }

    public static String getNhom(int nhom) {
        if (nhom == 1) return Variable.T05;
        if (nhom == 2) return Variable.T614;
        if (nhom == 3) return Variable.T1517;
        return null;
    }

    public static int getNhom1(String nhom) {
        if (Variable.T05.contains(nhom)) return 1;
        else if (Variable.T614.contains(nhom)) return 2;
        else return 3;
    }
}
