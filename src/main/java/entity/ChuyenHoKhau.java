package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChuyenHoKhau {
    private Integer id;
    private Integer idHoKhau;
    private Date ngayChuyenDi;
    private String noiChuyenDen;
    private String ghiChu;
}
