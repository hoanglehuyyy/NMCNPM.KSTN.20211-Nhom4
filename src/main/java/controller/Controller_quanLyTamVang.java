package controller;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller_quanLyTamVang {
    @FXML
    Label idLabel;


    @FXML
    Label hoTenLabel;

    public void setTamVang(NhanKhau nk){
        idLabel.setText(String.valueOf(nk.getId()));
        hoTenLabel.setText((nk.getHoTen()));
    }
}
