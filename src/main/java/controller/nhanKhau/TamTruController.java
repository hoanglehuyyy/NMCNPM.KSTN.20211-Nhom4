package controller.nhanKhau;

import entity.NhanKhau;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;
import utility.DbUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TamTruController {
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    private TextField noiTamTruF;
    @FXML
    private TextField noiOTruocKiaF;
    @FXML
    private DatePicker tuNgayF;
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

    public void setTamTru(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        nhanKhauId=nk.getId();
    }



    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save_tamTru(MouseEvent event) {

        connection = DbUtil.getInstance().getConnection();
        String noiTamTru = noiTamTruF.getText();
        String noiOTruocKia = noiOTruocKiaF.getText();
        String tuNgay = String.valueOf(tuNgayF.getValue());
        String lyDo=lyDoF.getText();

        if (noiTamTru.isEmpty() || tuNgay.isEmpty() ||noiOTruocKia.isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {
            getQuery();
            update();
            insert();
            clean();
            Alert alert_TC = new Alert(Alert.AlertType.CONFIRMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Khai báo thành công");
            alert_TC.showAndWait();

        }

    }

    @FXML
    private void clean() {
        noiTamTruF.setText(null);
        tuNgayF.setValue(null);
        lyDoF.setText(null);
        noiOTruocKiaF.setText(null);


    }
    @FXML
    private void huy(MouseEvent event) {
        noiTamTruF.setText(null);
        tuNgayF.setValue(null);
        lyDoF.setText(null);
        noiOTruocKiaF.setText(null);


    }

    private void getQuery() {



        //query = "INSERT INTO `nhan_khau`( `hoTen`, `biDanh`, `ngaySinh`, `noiSinh`, `gioiTinh`, `nguyenQuan`, `danToc`, `tonGiao`, `quocTich`, `ngheNghiep`, `noiLamViec`, `cmnd`, `ngayCap`, `chuyenDenNgay`, `noiThuongTruTruoc`, `trangThai`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        query = "UPDATE `nhan_khau` SET " +

                "`trangThai`=?  WHERE idNhanKhau  = '"+nhanKhauId+"'";
        query_insert="INSERT INTO `tam_tru`( `idNhanKhau`, `noiThuongTru`, `noiTamTru`, `tuNgay`, `lyDo`) VALUES (?,?,?,?,?)";

    }

    private void update() {

        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "Tạm trú");

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void insert() {

        try {

            preparedStatement = connection.prepareStatement(query_insert);
            preparedStatement.setInt(1, nhanKhauId);
            preparedStatement.setString(2, noiTamTruF.getText());
            preparedStatement.setString(3, noiOTruocKiaF.getText());
            preparedStatement.setString(4, String.valueOf(tuNgayF.getValue()));
            preparedStatement.setString(5, lyDoF.getText());

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


}
