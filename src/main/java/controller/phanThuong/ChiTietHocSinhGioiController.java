package controller.phanThuong;

import entity.DipHocSinhGioi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Utility;

public class ChiTietHocSinhGioiController {

    @FXML
    private Label tieuDe;
    @FXML
    private Label namHoc;
    @FXML
    private Label tienDacBiet;
    @FXML
    private Label phanThuongDacBiet;
    @FXML
    private Label phanThuongGioi;
    @FXML
    private Label tienGioi;
    @FXML
    private Label phanThuongKha;
    @FXML
    private Label tienKha;
    @FXML
    private Label moTa;


    private DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();
    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();
    public void setDipHocSinhGioi(DipHocSinhGioi dipHocSinhGioi) {
        this.dipHocSinhGioi = dipHocSinhGioi;
        tieuDe.setText(tieuDe.getText() + dipHocSinhGioi.getNam());
        namHoc.setText(String.valueOf(dipHocSinhGioi.getNam()));
        tienDacBiet.setText(dipHocSinhGioi.getTienDacBiet() + " VND");
        phanThuongDacBiet.setText(dipHocSinhGioi.getPhanQuaDacBiet());
        phanThuongGioi.setText(dipHocSinhGioi.getPhanQuaGioi());
        tienGioi.setText(dipHocSinhGioi.getTienGioi() + " VND");
        phanThuongKha.setText(dipHocSinhGioi.getPhanQuaKha());
        tienKha.setText(dipHocSinhGioi.getTienKha() + "VND");
        moTa.setText(dipHocSinhGioi.getMoTa());
    }

    public void quayLaiClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
