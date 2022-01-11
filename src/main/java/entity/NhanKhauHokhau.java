package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NhanKhauHokhau {
    private Integer idNhanKhau;
    private String tenNhanKhau;
    private Date ngaySinh;
    private String diaChi;
}
