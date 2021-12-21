package controller.nhanKhau;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class KhaiTuController {

    @FXML
    Label idLabel;


    @FXML
    Label hoTenLabel;

    public void setKhaiTu(NhanKhau nk){
        idLabel.setText(String.valueOf(nk.getId()));
        hoTenLabel.setText((nk.getHoTen()));
    }
}
