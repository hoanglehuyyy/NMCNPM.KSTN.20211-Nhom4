package controller.nhanKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;

public class ThemNhanKhauController {
//    public void goBack(ActionEvent e) throws IOException{
//        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
//        FXMLLoader loader= new FXMLLoader();
//        loader.setLocation(getClass().getResource("nhanKhau.fxml"));
//        Parent sampleParent=loader.load();
//        Scene scene= new Scene(sampleParent);
//        stage.setScene(scene);
//    }
    public void goBack(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
