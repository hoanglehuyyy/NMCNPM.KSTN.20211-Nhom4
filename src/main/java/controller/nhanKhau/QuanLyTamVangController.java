package controller.nhanKhau;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class QuanLyTamVangController {
    @FXML
    Label idLabel;


    @FXML
    Label hoTenLabel;

    public void setTamVang(NhanKhau nk){
        idLabel.setText(String.valueOf(nk.getId()));
        hoTenLabel.setText((nk.getHoTen()));
    }
}
