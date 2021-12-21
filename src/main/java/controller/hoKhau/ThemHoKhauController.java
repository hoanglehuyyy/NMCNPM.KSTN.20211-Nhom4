package controller.hoKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ThemHoKhauController {

    @FXML
    private TextField id_chu_ho_text;
    @FXML
    private TextField address_ho_khau_text;
    @FXML
    private TextField thanhpho_text;
    @FXML
    private TextField quanhuyen_text;
    @FXML
    private TextField phuongxa_text;
    @FXML
    private TextField ngaytao_text;
    @FXML
    private TextField trangthai_text;

    //database//
    private ResultSet rs = null;
    private Statement stmt = null;
    private PreparedStatement pstmt = null;
    private CallableStatement cstmt = null;
    private Connection conn = null;

//    public void save(ActionEvent event){
//        int id_add = Integer.parseInt(id_text.getText());
//        String name_add = name_text.getText();
//        String address_add = address_text.getText();
//    }

    public void save(ActionEvent e) throws IOException {

        close_button(e);
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }
}
