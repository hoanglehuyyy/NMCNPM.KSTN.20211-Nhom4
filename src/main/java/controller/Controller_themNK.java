package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.Main;

import java.io.IOException;

public class Controller_themNK {
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

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("manHinhChinh.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //stage.setTitle("ThemNhanKhau");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
