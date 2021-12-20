package controller;

import entity.Hokhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class showHK {
    @FXML
    private Label id_ho_khau_label;
    @FXML
    private Label id_chu_ho_label;
    @FXML
    private Label address_label;
    @FXML
    private Label thanhpho_label;
    @FXML
    private Label quanhuyen_label;
    @FXML
    private Label phuongxa_label;
    @FXML
    private Label ngaytao_label;
    @FXML
    private Label trangthai_label;

    public void show_hk(Hokhau hk){
        id_ho_khau_label.setText(String.valueOf(hk.getId_ho_khau()));
        id_chu_ho_label.setText(String.valueOf(hk.getId_chu_ho()));
        address_label.setText(hk.getAddress_ho_khau());
        thanhpho_label.setText(hk.getThanhpho_ho_khau());
        quanhuyen_label.setText(hk.getQuanhuyen_ho_khau());
        phuongxa_label.setText(hk.getPhuongxa_ho_khau());
        ngaytao_label.setText(String.valueOf(hk.getNgaytao_ho_khau()));
        trangthai_label.setText(hk.getTrangthai_ho_khau());
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}
