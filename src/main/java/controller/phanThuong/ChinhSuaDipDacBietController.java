package controller.phanThuong;

import entity.DipDacBiet;
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
import repository.DipDacBietRepository;
import repository.DipDacBietRepositoryImpl;
import utility.Message;
import utility.Utility;

import java.util.Optional;

public class ChinhSuaDipDacBietController {

    @FXML
    private Label namHoc;
    @FXML
    private TextField tenDip;
    @FXML
    private TextField phanThuong05;
    @FXML
    private TextField phanThuong614;
    @FXML
    private TextField phanThuong1517;
    @FXML
    private TextField tien05;
    @FXML
    private TextField tien614;
    @FXML
    private TextField tien1517;
    @FXML
    private TextField moTa;

    private DipDacBiet dipDacBiet = new DipDacBiet();
    private DipDacBietRepository dipDacBietImpl = new DipDacBietRepositoryImpl();

    private String tenGoc;

    public void setThongTin(DipDacBiet dip) {
        this.dipDacBiet = dip;
        namHoc.setText(String.valueOf(dip.getNam()));
        tenDip.setText(dip.getTen());
        phanThuong05.setText(dip.getPhanQua05());
        phanThuong614.setText(dip.getPhanQua1517());
        phanThuong1517.setText(dip.getPhanQua1517());
        tien05.setText(String.valueOf(dip.getTien05()));
        tien614.setText(String.valueOf(dip.getTien614()));
        tien1517.setText(String.valueOf(dip.getTien1517()));
        moTa.setText(dip.getMoTa());
        tenGoc = dip.getTen();
    }

    @SneakyThrows
    public void xacNhanClick(MouseEvent mouseEvent) {
        if (tenDip.getText().isEmpty() || phanThuong05.getText().isEmpty() || phanThuong614.getText().isEmpty() || phanThuong1517.getText().isEmpty() ||
                tien05.getText().isEmpty() || tien614.getText().isEmpty() || tien1517.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            if (!Utility.isPostive(tien05.getText()) || !Utility.isPostive(tien614.getText()) || !Utility.isPostive(tien1517.getText())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauNhapDungKieuDuLieu);
                alert.show();
            }
            else {
                if (!tenDip.getText().equals(tenGoc) && dipDacBietImpl.traCuuDipByTenNam(Integer.parseInt(namHoc.getText()), tenDip.getText()) != null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(Message.yeuCauDoiTenDip);
                    alert.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setHeaderText(Message.xacNhanThayDoiThongTinDip);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        dipDacBiet.setPhanQua05(phanThuong05.getText());
                        dipDacBiet.setPhanQua614(phanThuong614.getText());
                        dipDacBiet.setPhanQua1517(phanThuong1517.getText());
                        dipDacBiet.setTien05(Float.parseFloat(tien05.getText()));
                        dipDacBiet.setTien614(Float.parseFloat(tien614.getText()));
                        dipDacBiet.setTien1517(Float.parseFloat(tien1517.getText()));
                        dipDacBietImpl.chinhSuaThongTinDip(dipDacBiet.getIdDip(), tenDip.getText(), moTa.getText(), phanThuong05.getText(), phanThuong614.getText(),
                                phanThuong1517.getText(), Float.parseFloat(tien05.getText()), Float.parseFloat(tien614.getText()), Float.parseFloat(tien1517.getText()));
                        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                        stage.close();
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("/view/phanThuong/chiTietDipDacBiet.fxml"));
                        Parent p = loader.load();
                        ChiTietDipDacBietController c = loader.getController();
                        c.setDipDacBiet(dipDacBiet);
                        Utility.setStage(p);
                    }
                }
            }
        }
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
