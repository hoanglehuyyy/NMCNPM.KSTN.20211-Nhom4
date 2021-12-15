package com.example.quanlyhokhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class showHK {
    @FXML
    private Label id_label;
    @FXML
    private Label name_label;
    @FXML
    private Label address_label;
    @FXML
    private Label birth_label;
    @FXML
    private Label cmt_label;

    public void show_hk(Hokhau hk){
        id_label.setText(String.valueOf(hk.getId_ho_khau()));
        name_label.setText(hk.getName_ho_khau());
        address_label.setText(hk.getAddress_ho_khau());
        birth_label.setText(hk.getBirth_ho_khau());
        cmt_label.setText(hk.getCmt_ho_khau());
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}
