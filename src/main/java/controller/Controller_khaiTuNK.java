package controller;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller_khaiTuNK {

    @FXML
    Label idLabel;


    @FXML
    Label hoTenLabel;

    public void setKhaiTu(NhanKhau nk){
        idLabel.setText(String.valueOf(nk.getId()));
        hoTenLabel.setText((nk.getHoTen()));
    }
}
