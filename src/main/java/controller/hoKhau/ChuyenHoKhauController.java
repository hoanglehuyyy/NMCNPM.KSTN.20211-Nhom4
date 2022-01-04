package controller.hoKhau;

import entity.HoKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ChuyenHoKhauController {
    @FXML
    private TextField id_ho_khau_chuyen;
    @FXML
    private TextField id_chu_ho_chuyen;
    @FXML
    private TextField address_chuyen;
    @FXML
    private TextField new_address_chuyen;
    @FXML
    private TextField thanhpho_chuyen;
    @FXML
    private TextField quanhuyen_chuyen;
    @FXML
    private TextField phuongxa_chuyen;
    @FXML
    private TextField ngaychuyenden_chuyen;
    @FXML
    private TextArea reason_chuyen;

    public void chuyen_hk(HoKhau hk){
        id_ho_khau_chuyen.setText(String.valueOf(hk.getIdHoKhau()));
        id_chu_ho_chuyen.setText(String.valueOf(hk.getIdChuHo()));
        address_chuyen.setText(hk.getDiachi());
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void save_button(ActionEvent e){
        close_button(e);
    }


}
