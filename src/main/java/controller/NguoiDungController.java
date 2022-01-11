package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.NguoiDungRepository;
import repository.NguoiDungRepositoryImpl;
import utility.Message;
import view.Main;

import java.io.IOException;

public class NguoiDungController {

    @FXML
    private Button huyButton;

    @FXML
    private Button dangNhapButton;

    @FXML
    private TextField taiKhoan;

    @FXML
    private PasswordField matKhau;

    @FXML
    private Label dangNhapThatBaiMessage;

    static NguoiDungRepository nguoiDungRepository = new NguoiDungRepositoryImpl();

    public void huyButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) huyButton.getScene().getWindow();
        stage.close();
    }

    public void dangNhapButtonOnAction(ActionEvent event) throws IOException {
        if (taiKhoan.getText().isBlank() == false && matKhau.getText().isBlank() == false) {
            if (nguoiDungRepository.dangNhap(taiKhoan.getText(), matKhau.getText())) {
                Stage stage = (Stage) dangNhapButton.getScene().getWindow();

                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("manHinhChinh.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                stage.setTitle("Quản lý khu phố");
                stage.setScene(scene);
//                stage.setMaximized(true);
                stage.setWidth(1400);
                stage.setHeight(780);
                stage.setX(10);
                stage.setY(10);
                stage.show();
            } else {
                dangNhapThatBaiMessage.setText(Message.dangNhapThatBai);
            }
        } else {
            dangNhapThatBaiMessage.setText(Message.dangNhapTrong);
        }
    }
}

