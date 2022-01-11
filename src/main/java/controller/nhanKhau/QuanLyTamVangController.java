package controller.nhanKhau;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import utility.DbUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.NHAN_KHAU_QUERY_INSERT_TAMVANG;
import static utility.SQLCommand.NHAN_KHAU_QUERY_UPDATE_TRANGTHAI;

public class QuanLyTamVangController implements Initializable {
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiTamTruF;
    @FXML
    private DatePicker tuNgayF;
    @FXML
    private DatePicker denNgayF;
    @FXML
    private TextArea lyDoF;
    String query = null;
    String query_insert = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    NhanKhau nhanKhau = null;
    private boolean update;
    int nhanKhauId;

    public void setTamVang(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        nhanKhauId=nk.getId();
    }

    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save(MouseEvent event) {

        connection = DbUtil.getInstance().getConnection();
        String noiTamTru = noiTamTruF.getText();
        LocalDate tuNgay = tuNgayF.getValue();



        if (noiTamTru.isEmpty() || tuNgay==null  ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {
            if (String.valueOf(tuNgay).compareTo(String.valueOf(denNgayF.getValue()))>0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Trường ĐẾN NGÀY phải có thời gian sau trường TỪ NGÀY ");
                alert.showAndWait();
            }else{
                update();
                insert();
                clean();
                Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
                alert_TC.setHeaderText(null);
                alert_TC.setContentText("Khai báo thành công");
                alert_TC.showAndWait();
                final Node source = (Node) event.getSource();
                final Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }


        }

    }

    @FXML
    private void clean() {
        noiTamTruF.setText(null);
        tuNgayF.setValue(null);
        denNgayF.setValue(null);
        lyDoF.setText(null);


    }
    @FXML
    private void huy(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


    }



    private void update() {

        try {

            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_UPDATE_TRANGTHAI+nhanKhauId);
            preparedStatement.setString(1, "Tạm vắng");

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_INSERT_TAMVANG);
            preparedStatement.setInt(1, nhanKhauId);
            preparedStatement.setString(2, noiTamTruF.getText());
            preparedStatement.setString(3, String.valueOf(tuNgayF.getValue()));
            if (denNgayF.getValue()==null ){
                preparedStatement.setString(4, null);
            }else{
                preparedStatement.setString(4, String.valueOf(denNgayF.getValue()));
            }
            preparedStatement.setString(5, lyDoF.getText());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
