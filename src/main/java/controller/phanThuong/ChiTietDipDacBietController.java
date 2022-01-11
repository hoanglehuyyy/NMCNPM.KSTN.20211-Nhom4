package controller.phanThuong;

import entity.DipDacBiet;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import utility.Utility;

public class ChiTietDipDacBietController {

    @FXML
    private Label tieuDe;
    @FXML
    private Label tenDip;
    @FXML
    private Label namHoc;
    @FXML
    private Label phanThuong05;
    @FXML
    private Label phanThuong614;
    @FXML
    private Label phanThuong1517;
    @FXML
    private Label tien05;
    @FXML
    private Label tien614;
    @FXML
    private Label tien1517;
    @FXML
    private Label moTa;

    private DipDacBiet dipDacBiet = new DipDacBiet();
    
    public void setDipDacBiet(DipDacBiet dipDacBiet) {
        this.dipDacBiet = dipDacBiet;
        tieuDe.setText(tieuDe.getText() + dipDacBiet.getTen());
        tenDip.setText(dipDacBiet.getTen());
        namHoc.setText(String.valueOf(dipDacBiet.getNam()));
        phanThuong05.setText(dipDacBiet.getPhanQua05());
        phanThuong614.setText(dipDacBiet.getPhanQua614());
        phanThuong1517.setText(dipDacBiet.getPhanQua1517());
        tien05.setText(dipDacBiet.getTien05() + " VND");
        tien614.setText(dipDacBiet.getTien614() + " VND");
        tien1517.setText(dipDacBiet.getTien1517() + "VND");
        moTa.setText(dipDacBiet.getMoTa());
    }

    public void quayLaiClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
