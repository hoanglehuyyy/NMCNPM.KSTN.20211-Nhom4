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
public class TamVang {
    private Integer id;
    private Integer idNhanKhau;
    private String noiTamTru;
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private String lyDo;
}
