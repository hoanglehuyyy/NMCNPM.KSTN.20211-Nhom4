package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import repository.HoKhauRepository;
import repository.HoKhauRepositoryImpl;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class TrangChuController implements Initializable {
    @FXML
    private Label tongNhanKhauThuongTru;

    @FXML
    private Label tongNhanKhauTamTru;

    @FXML
    private Label tongNhanKhauTamVang;

    @FXML
    private Label tongHoKhauThuongTru;

    NhanKhauRepository nhanKhauRepository = new NhanKhauRepositoryImpl();
    HoKhauRepository hoKhauRepository = new HoKhauRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tongNhanKhauThuongTru.setText(String.valueOf(nhanKhauRepository.tongNhanKhauThuongTru()));
        tongNhanKhauTamTru.setText(String.valueOf(nhanKhauRepository.tongNhanKhauTamTru()));
        tongNhanKhauTamVang.setText(String.valueOf(nhanKhauRepository.tongNhanKhauTamVang()));
        tongHoKhauThuongTru.setText(String.valueOf(hoKhauRepository.tongHoKhauThuongTru()));
    }
}
