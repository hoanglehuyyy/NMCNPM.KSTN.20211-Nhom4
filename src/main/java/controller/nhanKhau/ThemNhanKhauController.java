package controller.nhanKhau;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

import static utility.SQLCommand.NHAN_KHAU_QUERY_INSERT_NHANKHAU;


public class ThemNhanKhauController implements Initializable {
    @FXML
    private TextField hoTenF;
    @FXML
    private TextField biDanhF;
    @FXML
    private DatePicker ngaySinhF;
    @FXML
    private DatePicker ngayCapF;
    @FXML
    private DatePicker chuyenDenNgayF;
    @FXML
    private TextField noiSinhF;
    @FXML
    private ComboBox comb;
    @FXML
    private TextField nguyenQuanF;
    @FXML
    private TextField danTocF;
    @FXML
    private TextField tonGiaoF;
    @FXML
    private TextField quocTichF;
    @FXML
    private TextField ngheNghiepF;
    @FXML
    private TextField noiLamViecF;
    @FXML
    private TextField CMNDF;
    @FXML
    private TextField noiThuongTruTruocF;




    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    NhanKhau nhanKhau = null;
    private boolean update;
    int nhanKhauId;
    String gioiTinhC=null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listGioiTinh = FXCollections.observableArrayList("Nam","Nữ");
        comb.setItems(listGioiTinh);
        // TODO
    }

    @SneakyThrows
    @FXML
    private void save(MouseEvent event) {

        connection = DbUtil.getInstance().getConnection();
        String hoTen = hoTenF.getText();
        LocalDate ngaySinh = ngaySinhF.getValue();
        String noiSinh = noiSinhF.getText();
        String nguyenQuan = nguyenQuanF.getText();
        String danToc = danTocF.getText();
        String tonGiao = tonGiaoF.getText();
        String quocTich = quocTichF.getText();

        String cmnd = CMNDF.getText();

        try{
            int test_cmnd = Integer.parseInt(cmnd);
        }catch (NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Trường CMND không thoả mãn");
            alert.showAndWait();
            return;
        }

        if (hoTen.isEmpty() || ngaySinh==null || quocTich.isEmpty() || tonGiao.isEmpty()||
                danToc.isEmpty()||noiSinh.isEmpty()||nguyenQuan.isEmpty()||gioiTinhC==null||gioiTinhC=="") {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {
            insert();
            Alert alert_TC = new Alert(Alert.AlertType.INFORMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm thành công");
            alert_TC.showAndWait();
            gioiTinhC="";
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }

    }

    @FXML
     private void Select(ActionEvent event) {
         gioiTinhC = comb.getSelectionModel().getSelectedItem().toString();
    }

    @FXML
    private void huy(MouseEvent event) {
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();


    }



    private void insert() {

        String trangThaiMacDinh="";


        try {

            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_INSERT_NHANKHAU);
            preparedStatement.setString(1, hoTenF.getText());

            if (biDanhF.getText()=="" ){
                preparedStatement.setString(2, "");
            }else{
                preparedStatement.setString(2, biDanhF.getText());
            }
            preparedStatement.setString(3, String.valueOf(ngaySinhF.getValue()));
            preparedStatement.setString(4, noiSinhF.getText());
            preparedStatement.setString(5, gioiTinhC);
            preparedStatement.setString(6, nguyenQuanF.getText());
            preparedStatement.setString(7, danTocF.getText());
            preparedStatement.setString(8, tonGiaoF.getText());
            preparedStatement.setString(9, quocTichF.getText());
            if (ngheNghiepF.getText()=="" ){
                preparedStatement.setString(10, "");
            }else{
                preparedStatement.setString(10, ngheNghiepF.getText());
            }

            if (noiLamViecF.getText()=="" ){
                preparedStatement.setString(11, "");
            }else{
                preparedStatement.setString(11, noiLamViecF.getText());
            }

            if (CMNDF.getText()=="" ){
                preparedStatement.setString(12, "");
            }else{
                preparedStatement.setString(12, CMNDF.getText());
            }

            if (ngayCapF.getValue()==null ){
                preparedStatement.setString(13, null);
            }else{
                preparedStatement.setString(13, String.valueOf(ngayCapF.getValue()));
            }
            if (chuyenDenNgayF.getValue()==null ){
                preparedStatement.setString(14, null);
            }else{
                preparedStatement.setString(14, String.valueOf(chuyenDenNgayF.getValue()));
            }


            if (noiThuongTruTruocF.getText()=="" ){
                preparedStatement.setString(15, null);
            }else{
                preparedStatement.setString(15, noiThuongTruTruocF.getText());
            }
            preparedStatement.setString(16, trangThaiMacDinh);

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }





}
