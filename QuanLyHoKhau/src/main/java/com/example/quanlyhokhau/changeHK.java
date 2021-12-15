package com.example.quanlyhokhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class changeHK {
    @FXML
    private TextField id_ho_khau_change;
    @FXML
    private TextField id_chu_ho_change;


    public void change_hk(Hokhau hk){
        id_ho_khau_change.setText(String.valueOf(hk.getId_ho_khau()));
        id_chu_ho_change.setText(String.valueOf(hk.getId_chu_ho()));
    }

    public void save_button(ActionEvent e){
        close_button(e);
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
