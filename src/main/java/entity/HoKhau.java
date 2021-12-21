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
public class HoKhau {
    private Integer idHoKhau;
    private Integer idChuHo;
    private String tinhThanhPho;
    private String quanHuyen;
    private String phuongXa;
    private String diachi;
    private Date ngayTao;
    private String trangThai;
}

