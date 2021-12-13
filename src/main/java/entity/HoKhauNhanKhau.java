package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HoKhauNhanKhau {
    private Integer idHoKhau;
    private Integer idNhanKhau;
    private String quanHeChuHo;
}
