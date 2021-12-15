package com.example.quanlyhokhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class changeHK {
    @FXML
    private TextField id_change;
    @FXML
    private TextField name_change;
    @FXML
    private TextField address_change;
    @FXML
    private TextField birth_change;
    @FXML
    private TextField cmt_change;

    public void change_hk(Hokhau hk){
        id_change.setText(String.valueOf(hk.getId_ho_khau()));
        name_change.setText(hk.getName_ho_khau());
        address_change.setText(hk.getAddress_ho_khau());
        birth_change.setText(hk.getBirth_ho_khau());
        cmt_change.setText(hk.getCmt_ho_khau());
    }

    public void save_button(ActionEvent e){
        close_button(e);
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
