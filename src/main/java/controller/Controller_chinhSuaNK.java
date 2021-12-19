package controller;

import entity.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;

public class Controller_chinhSuaNK {
    @FXML
    TextField hoTenLabel;


    public void setChinhSuaNK(NhanKhau nk){
        hoTenLabel.setText(nk.getHoTen());

    }
    public void goBack_chinhSuaNK(ActionEvent e) throws IOException {
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(Main.class.getResource("nhanKhau.fxml"));
        Parent sampleParent=loader.load();
        Scene scene= new Scene(sampleParent);
        stage.setScene(scene);
    }
}
