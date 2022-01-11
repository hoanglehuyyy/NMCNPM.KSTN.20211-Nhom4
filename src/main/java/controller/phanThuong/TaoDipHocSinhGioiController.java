package controller.phanThuong;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Message;
import utility.Utility;

import java.util.Optional;

public class TaoDipHocSinhGioiController {

    @FXML
    private TextField namHoc;
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


    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();

    @SneakyThrows
    public void xacNhanClick(MouseEvent mouseEvent) {
        if (namHoc.getText().isEmpty() || phanThuongDacBiet.getText().isEmpty() || phanThuongGioi.getText().isEmpty() || phanThuongKha.getText().isEmpty() || tienDacBiet.getText().isEmpty() || tienGioi.getText().isEmpty() || tienKha.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            if (!Utility.isPostiveInteger(namHoc.getText()) || !Utility.isPostive(tienDacBiet.getText()) || !Utility.isPostive(tienGioi.getText()) || !Utility.isPostive(tienKha.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauNhapDungKieuDuLieu);
                alert.show();
            }
            else {
                if (hocSinhGioiImpl.traCuuDipByNam(Integer.parseInt(namHoc.getText())) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(Message.yeuCauDoiNamHoc);
                    alert.show();
                }
                else if (Integer.parseInt(tienDacBiet.getText()) <= Integer.parseInt(tienGioi.getText()) || Integer.parseInt(tienGioi.getText()) <= Integer.parseInt(tienKha.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(Message.yeuCauDoiTienThuong);
                    alert.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(Message.xacNhanThemMoiDip);
                    Optional<ButtonType> result =  alert.showAndWait();
                    if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                        hocSinhGioiImpl.taoDipHocSinhGioi(Integer.parseInt(namHoc.getText()), moTa.getText(), phanThuongDacBiet.getText(), phanThuongGioi.getText(), phanThuongKha.getText(),
                                Float.parseFloat(tienDacBiet.getText()), Float.parseFloat(tienGioi.getText()), Float.parseFloat(tienKha.getText()));
                        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
                        stage.close();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/phanThuong/chiTietHocSinhGioi.fxml"));
                        Parent p = loader.load();
                        ChiTietHocSinhGioiController c = loader.getController();
                        c.setDipHocSinhGioi(hocSinhGioiImpl.traCuuDipByNam(Integer.parseInt(namHoc.getText())));
                        Stage newStage = Utility.setStage(p);
                    }
                }
            }

        }
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node)mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
