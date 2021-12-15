package com.example.quanlyhokhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class addHK {
    @FXML
    private TextField id_text;
    @FXML
    private TextField name_text;
    @FXML
    private TextField address_text;
    @FXML
    private TextField birth_text;
    @FXML
    private TextField cmt_text;

//    public void save(ActionEvent event){
//        int id_add = Integer.parseInt(id_text.getText());
//        String name_add = name_text.getText();
//        String address_add = address_text.getText();
//    }

    public void save(ActionEvent e) throws IOException {
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
