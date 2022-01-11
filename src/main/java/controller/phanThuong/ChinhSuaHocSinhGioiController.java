package controller.phanThuong;

import entity.DipHocSinhGioi;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Message;
import utility.Utility;

import java.util.Optional;

public class ChinhSuaHocSinhGioiController {

    @FXML
    private Label tieuDe;
    @FXML
    private Label namHoc;
    @FXML
    private TextField tienDacBiet;
    @FXML
    private TextField phanThuongDacBiet;
    @FXML
    private TextField phanThuongGioi;
    @FXML
    private TextField tienGioi;
    @FXML
    private TextField phanThuongKha;
    @FXML
    private TextField tienKha;
    @FXML
    private TextField moTa;

    private DipHocSinhGioi dipHocSinhGioi = new DipHocSinhGioi();
    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();

    public void setThongTin(DipHocSinhGioi dip) {
        this.dipHocSinhGioi = dip;
        tieuDe.setText(tieuDe.getText() + dip.getNam());
        namHoc.setText(String.valueOf(dip.getNam()));
        phanThuongDacBiet.setText(dip.getPhanQuaDacBiet());
        phanThuongGioi.setText(dip.getPhanQuaGioi());
        phanThuongKha.setText(dip.getPhanQuaKha());
        tienDacBiet.setText(String.valueOf(dip.getTienDacBiet()));
        tienGioi.setText(String.valueOf(dip.getTienGioi()));
        tienKha.setText(String.valueOf(dip.getTienKha()));
        moTa.setText(dip.getMoTa());
    }

    @SneakyThrows
    public void xacNhanClick(MouseEvent mouseEvent) {
        if (phanThuongDacBiet.getText().isEmpty() || phanThuongGioi.getText().isEmpty() || phanThuongKha.getText().isEmpty() ||
                tienDacBiet.getText().isEmpty() || tienGioi.getText().isEmpty() || tienKha.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            if (!Utility.isPostive(tienDacBiet.getText()) || !Utility.isPostive(tienGioi.getText()) || !Utility.isPostive(tienKha.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauNhapDungKieuDuLieu);
                alert.show();
            }
            else if (Integer.parseInt(tienDacBiet.getText()) <= Integer.parseInt(tienGioi.getText()) || Integer.parseInt(tienGioi.getText()) <= Integer.parseInt(tienKha.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauDoiTienThuong);
                alert.show();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setHeaderText(Message.xacNhanThayDoiThongTinDip);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    dipHocSinhGioi.setPhanQuaDacBiet(phanThuongDacBiet.getText());
                    dipHocSinhGioi.setPhanQuaGioi(phanThuongGioi.getText());
                    dipHocSinhGioi.setPhanQuaKha(phanThuongKha.getText());
                    dipHocSinhGioi.setTienDacBiet(Float.parseFloat(tienDacBiet.getText()));
                    dipHocSinhGioi.setTienGioi(Float.parseFloat(tienGioi.getText()));
                    dipHocSinhGioi.setTienKha(Float.parseFloat(tienKha.getText()));
                    dipHocSinhGioi.setMoTa(moTa.getText());
                    hocSinhGioiImpl.chinhSuaThongTinDip(dipHocSinhGioi.getIdDip(), moTa.getText(),phanThuongDacBiet.getText() , phanThuongGioi.getText(),
                            phanThuongKha.getText(), Float.parseFloat(tienDacBiet.getText()), Float.parseFloat(tienGioi.getText()), Float.parseFloat(tienKha.getText()));
                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("/view/phanThuong/chiTietHocSinhGioi.fxml"));
                    Parent p = loader.load();
                    ChiTietHocSinhGioiController c = loader.getController();
                    c.setDipHocSinhGioi(dipHocSinhGioi);
                    Utility.setStage(p);
                }
            }
        }
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
