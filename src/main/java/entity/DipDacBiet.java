package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DipDacBiet {
    private Integer idDip;
    private String ten;
    private Integer nam;
    private String moTa;
    private String phanQua05;
    private String phanQua614;
    private String phanQua1517;
    private Float tien05;
    private Float tien614;
    private Float tien1517;
    private Integer soNguoiChuaTraoThuong;
}
