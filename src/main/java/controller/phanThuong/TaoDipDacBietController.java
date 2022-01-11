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
import repository.DipDacBietRepository;
import repository.DipDacBietRepositoryImpl;
import utility.Message;
import utility.Utility;

import java.io.IOException;
import java.util.Optional;

public class TaoDipDacBietController {

    @FXML
    private TextField nam;
    @FXML
    private TextField tenDip;
    @FXML
    private TextField tien05;
    @FXML
    private TextField phanThuong05;
    @FXML
    private TextField phanThuong614;
    @FXML
    private TextField tien614;
    @FXML
    private TextField phanThuong1517;
    @FXML
    private TextField tien1517;
    @FXML
    private TextField moTa;

    private DipDacBietRepository dipDacBietImpl = new DipDacBietRepositoryImpl();

    public void xacNhanClick(MouseEvent mouseEvent) {
        if (nam.getText().isEmpty() || tenDip.getText().isEmpty() || phanThuong05.getText().isEmpty() || phanThuong614.getText().isEmpty() || phanThuong1517.getText().isEmpty() || tien05.getText().isEmpty() || tien614.getText().isEmpty() || tien1517.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            if (!Utility.isPostiveInteger(nam.getText()) || !Utility.isPostive(tien05.getText()) || !Utility.isPostive(tien614.getText()) || !Utility.isPostive(tien1517.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauNhapDungKieuDuLieu);
                alert.show();
            }
            else {
                if (dipDacBietImpl.traCuuDipByTenNam(Integer.parseInt(nam.getText()), tenDip.getText()) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(Message.yeuCauDoiTenDip + nam.getText());
                    alert.show();
                }
                else if (Integer.parseInt(tien05.getText()) <= Integer.parseInt(tien614.getText()) || Integer.parseInt(tien614.getText()) <= Integer.parseInt(tien1517.getText())) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(Message.yeuCauDoiTienThuong);
                    alert.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(Message.xacNhanThemMoiDip);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        dipDacBietImpl.taoDipDacBiet(tenDip.getText(), Integer.parseInt(nam.getText()), moTa.getText(), phanThuong05.getText(), phanThuong614.getText(), phanThuong1517.getText(), Float.parseFloat(tien05.getText()),Float.parseFloat(tien614.getText()) , Float.parseFloat(tien1517.getText()));
                        alert.close();
                        Alert newAlert = new Alert(Alert.AlertType.INFORMATION);
                        newAlert.setHeaderText(Message.thongBaoThemDip);
                        newAlert.setOnCloseRequest(event -> {
                            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                            stage.close();
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("/view/phanThuong/chiTietDipDacBiet.fxml"));
                            try {
                                Parent p = loader.load();
                                ChiTietDipDacBietController c = loader.getController();
                                c.setDipDacBiet(dipDacBietImpl.traCuuDipByTenNam(Integer.parseInt(nam.getText()), tenDip.getText()));
                                Utility.setStage(p);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                        newAlert.show();
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
