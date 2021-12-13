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
public class HoKhau {
    private Integer idHoKhau;
    private Integer idChuHo;
    private String tinhThanhPho;
    private String quanHuyen;
    private String phuongXa;
    private String diachi;
    private LocalDate ngayTao;
    private String trangThai;
}

