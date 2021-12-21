package controller;

import entity.Hokhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class tachHK {
    @FXML
    private TextField now_id_chu_ho;
    @FXML
    private TextField id_ho_khau;
    @FXML
    private TextField new_address_ho_khau;
    @FXML
    private TextField new_id_chu_ho;
    @FXML
    private TextField new_id_ho_khau;

    public void tach_hk(Hokhau hk){
        now_id_chu_ho.setText(String.valueOf(hk.getId_chu_ho()));
        id_ho_khau.setText(String.valueOf(hk.getId_ho_khau()));
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void save_button(ActionEvent e){
        close_button(e);
    }
}
