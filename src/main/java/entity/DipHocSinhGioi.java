package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DipHocSinhGioi {
    private Integer idDip;
    private Integer nam;
    private String moTa;
    private String phanQuaDacBiet;
    private String phanQuaGioi;
    private String phanQuaKha;
    private Float tienDacBiet;
    private Float tienGioi;
    private Float tienKha;
    private Integer soNguoiChuaTraoThuong;
}
