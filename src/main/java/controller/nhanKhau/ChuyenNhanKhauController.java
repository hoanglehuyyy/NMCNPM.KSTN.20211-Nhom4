package controller.nhanKhau;

import entity.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import utility.DbUtil;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.NHAN_KHAU_QUERY_INSERT_CHUYENNHANKHAU;
import static utility.SQLCommand.NHAN_KHAU_QUERY_UPDATE_TRANGTHAI;

public class ChuyenNhanKhauController {

    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiChuyenDenF;
    @FXML
    private DatePicker ngayChuyenDiF;
    @FXML
    private TextArea ghiChuF;
    Connection connection = null;
    PreparedStatement preparedStatement;
    int nhanKhauId;

    public void setChuyenNhanKhau(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        nhanKhauId=nk.getId();
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save_chuyenDi(MouseEvent event) {

        connection = DbUtil.getInstance().getConnection();
        String noiChuyenDen = noiChuyenDenF.getText();
        LocalDate ngayChuyenDi = ngayChuyenDiF.getValue();

        if (noiChuyenDen.isEmpty() || ngayChuyenDi==null  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {
            update();
            insert();
            clean();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Chuyển nhân khẩu thành công");
            alert_TC.showAndWait();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }


    }

    @FXML
    private void clean() {
        noiChuyenDenF.setText(null);
        ngayChuyenDiF.setValue(null);
        ghiChuF.setText(null);



    }

    private void update() {

        try {

            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_UPDATE_TRANGTHAI+nhanKhauId);
            preparedStatement.setString(1, "Đã chuyển đi");

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_INSERT_CHUYENNHANKHAU);
            preparedStatement.setInt(1, nhanKhauId);
            preparedStatement.setString(2, String.valueOf(ngayChuyenDiF.getValue()));
            preparedStatement.setString(3, noiChuyenDenF.getText());
            preparedStatement.setString(4, ghiChuF.getText());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void goBack_chuyenNK(ActionEvent e) throws IOException {
        final Node source = (Node) e.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
