package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDipDacBiet {
    private Integer idDip;
    private Integer idNhanKhau;
    private Integer nhom;
    private Boolean kiemTra;
}
