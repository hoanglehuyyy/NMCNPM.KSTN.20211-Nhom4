package controller.hoKhau;

import entity.HoKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class XemHoKhauController {
    @FXML
    private Label id_ho_khau_label;
    @FXML
    private Label id_chu_ho_label;
    @FXML
    private Label address_label;
    @FXML
    private Label thanhpho_label;
    @FXML
    private Label quanhuyen_label;
    @FXML
    private Label phuongxa_label;
    @FXML
    private Label ngaytao_label;
    @FXML
    private Label trangthai_label;

    public void show_hk(HoKhau hk){
        id_ho_khau_label.setText(String.valueOf(hk.getIdHoKhau()));
        id_chu_ho_label.setText(String.valueOf(hk.getIdChuHo()));
        address_label.setText(hk.getDiachi());
        thanhpho_label.setText(hk.getTinhThanhPho());
        quanhuyen_label.setText(hk.getQuanHuyen());
        phuongxa_label.setText(hk.getPhuongXa());
        ngaytao_label.setText(String.valueOf(hk.getNgayTao()));
        trangthai_label.setText(hk.getTrangThai());
    }

    public void close_button(ActionEvent e){
        Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

}
