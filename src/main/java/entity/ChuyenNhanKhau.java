package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenNhanKhau {
    private Integer id;
    private Integer idNhanKhau;
    private LocalDate ngayChuyenDi;
    private String noiChuyenDen;
    private String ghiChu;
}
