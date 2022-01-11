package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChiTietDipHocSinhGioi {
    private Integer idDip;
    private Integer idNhanKhau;
    private String truong;
    private String lop;
    private Integer nhom;
    private String minhChung;
    private Boolean kiemTra;
}
