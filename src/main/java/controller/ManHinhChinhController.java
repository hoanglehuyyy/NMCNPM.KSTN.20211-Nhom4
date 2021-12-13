package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HoKhauRepository;
import repository.HoKhauRepositoryImpl;
import repository.NhanKhauRepository;
import repository.NhanKhauRepositoryImpl;
import view.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManHinhChinhController implements Initializable {
    @FXML
    private Button trangChuButton;

    @FXML
    private Button nhanKhauButton;

    @FXML
    private Button hoKhauButton;

    @FXML
    private Button phanThuongButton;

    @FXML
    private Button thongKeButton;

    @FXML
    private Button dangXuatButton;

    @FXML
    private Pane mainPane;

    @SneakyThrows
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("trangChu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void resetButtonBackground() {
        trangChuButton.setStyle("-fx-background-color: #2F333D;");
        nhanKhauButton.setStyle("-fx-background-color: #2F333D;");
        hoKhauButton.setStyle("-fx-background-color: #2F333D;");
        phanThuongButton.setStyle("-fx-background-color: #2F333D;");
        thongKeButton.setStyle("-fx-background-color: #2F333D;");
    }

    public void trangChuButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        trangChuButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("trangChu.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void nhanKhauButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        nhanKhauButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("nhanKhau.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void hoKhauButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        hoKhauButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("hoKhau.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void phanThuongButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        phanThuongButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("phanThuong.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void thongKeButtonOnAction(ActionEvent event) throws IOException {
        resetButtonBackground();
        thongKeButton.setStyle("-fx-background-color: #757C95;");

        Pane trangChuPane =  FXMLLoader.load(Main.class.getResource("thongKe.fxml"));
        mainPane.getChildren().add(trangChuPane);
    }

    public void dangXuatButtonOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) dangXuatButton.getScene().getWindow();

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("dangNhap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        stage.setTitle("Đăng nhập");
        stage.setScene(scene);
        stage.setMaximized(true);
        stage.show();
    }
}
