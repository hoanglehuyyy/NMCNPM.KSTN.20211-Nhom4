package controller.nhanKhau;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import lombok.SneakyThrows;
import utility.DbUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN;

public class ChinhSuaNhanKhauController  {
    @FXML
    private TextField hoTenLabel;
    @FXML
    private TextField biDanhLabel;
    @FXML
    private DatePicker ngaySinhLabel;
    @FXML
    private TextField noiSinhLabel;
    @FXML
    private ComboBox combGioiTinh;
    @FXML
    private TextField nguyenQuanLabel;
    @FXML
    private TextField danTocLabel;
    @FXML
    private TextField tonGiaoLabel;
    @FXML
    private TextField quocTichLabel;
    @FXML
    private TextField ngheNghiepLabel;
    @FXML
    private TextField noiLamViecLabel;
    @FXML
    private TextField CMNDLabel;
    @FXML
    private DatePicker ngayCapLabel;
    @FXML
    private DatePicker chuyenDenNgayLabel;
    @FXML
    private TextField noiThuongTruTruocLabel;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    NhanKhau nhanKhau = null;

    private int id_NK;
    String gioiTinhC=null;



    public void setChinhSuaNK(NhanKhau nk){
        id_NK=nk.getId();
        ObservableList<String> listGioiTinh2 = FXCollections.observableArrayList("Nam","Nữ");
        combGioiTinh.setItems(listGioiTinh2);
        loadData();


    }

    @SneakyThrows
    @FXML
    private void save_ChinhSua(MouseEvent event) {
        connection = DbUtil.getInstance().getConnection();
        String hoTen = hoTenLabel.getText();
        String ngaySinh = String.valueOf(ngaySinhLabel.getValue());

        String noiSinh = noiSinhLabel.getText();

        String nguyenQuan = nguyenQuanLabel.getText();
        String danToc = danTocLabel.getText();
        String tonGiao = tonGiaoLabel.getText();
        String quocTich = quocTichLabel.getText();



        if (hoTen.isEmpty() || ngaySinh.isEmpty() || quocTich.isEmpty() || tonGiao.isEmpty()||
                danToc.isEmpty()||noiSinh.isEmpty()||nguyenQuan.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {

            getQuery();
            update();
            Alert alert_TC = new Alert(Alert.AlertType.CONFIRMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Chỉnh sửa thành công");
            alert_TC.showAndWait();

        }

    }
    @FXML
    private void selectGioiTinh(ActionEvent event) {
        gioiTinhC = combGioiTinh.getSelectionModel().getSelectedItem().toString();

    }

    @FXML
    private void loadData() {
        try {


            connection = DbUtil.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_LAY_THONG_TIN);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                if(resultSet.getInt("idNhanKhau")==id_NK){

                    hoTenLabel.setText(resultSet.getString("hoTen"));
                    biDanhLabel.setText(resultSet.getString("biDanh"));
                   //String ngaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);

                    ngaySinhLabel.setValue(LocalDate.parse(resultSet.getString("ngaySinh")));
                    noiSinhLabel.setText(resultSet.getString("noiSinh"));

                    combGioiTinh.getSelectionModel().select(resultSet.getString("gioiTinh"));
                    gioiTinhC=resultSet.getString("gioiTinh");
                    nguyenQuanLabel.setText(resultSet.getString("nguyenQuan"));
                    danTocLabel.setText(resultSet.getString("danToc"));
                    tonGiaoLabel.setText(resultSet.getString("tonGiao"));
                    quocTichLabel.setText(resultSet.getString("quocTich"));
                    ngheNghiepLabel.setText(resultSet.getString("ngheNghiep"));
                    noiLamViecLabel.setText(resultSet.getString("noiLamViec"));
                    CMNDLabel.setText(resultSet.getString("cmnd"));

                    if(resultSet.getString("ngayCap")!=null){
                        ngayCapLabel.setValue(LocalDate.parse(resultSet.getString("ngayCap")));
                    }

                    if(resultSet.getString("chuyenDenNgay")!=null){
                        chuyenDenNgayLabel.setValue(LocalDate.parse(resultSet.getString("chuyenDenNgay")));
                    }







                    noiThuongTruTruocLabel.setText(resultSet.getString("noiThuongTruTruoc"));
//                    trangThaiLabel.setText(resultSet.getString("trangThai"));

                }



            }


        } catch (SQLException ex) {
            Logger.getLogger(ThongTinNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    private void getQuery() {



        //query = "INSERT INTO `nhan_khau`( `hoTen`, `biDanh`, `ngaySinh`, `noiSinh`, `gioiTinh`, `nguyenQuan`, `danToc`, `tonGiao`, `quocTich`, `ngheNghiep`, `noiLamViec`, `cmnd`, `ngayCap`, `chuyenDenNgay`, `noiThuongTruTruoc`, `trangThai`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


            query = "UPDATE `nhan_khau` SET " +
                    "`hoTen`=?," +
                    "`biDanh`=?," +
                    "`ngaySinh`=?," +
                    "`noiSinh`=?," +
                    "`gioiTinh`=?," +
                    "`nguyenQuan`=?," +
                    "`danToc`=?," +
                    "`tonGiao`=?," +
                    "`quocTich`=?," +
                    "`ngheNghiep`=?," +
                    "`noiLamViec`=?," +
                    "`cmnd`=?," +
                    "`ngayCap`=?," +
                    "`chuyenDenNgay`=?," +
                    "`noiThuongTruTruoc`=?  WHERE idNhanKhau  = '"+id_NK+"'";


    }

    private void update() {

        String trangThaiMacDinh=null;


        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, hoTenLabel.getText());

            if (biDanhLabel.getText()=="" ){
                preparedStatement.setString(2, null);
            }else{
                preparedStatement.setString(2, biDanhLabel.getText());
            }
            preparedStatement.setString(3, String.valueOf(ngaySinhLabel.getValue()));
            preparedStatement.setString(4, noiSinhLabel.getText());
            preparedStatement.setString(5, gioiTinhC);
            preparedStatement.setString(6, nguyenQuanLabel.getText());
            preparedStatement.setString(7, danTocLabel.getText());
            preparedStatement.setString(8, tonGiaoLabel.getText());
            preparedStatement.setString(9, quocTichLabel.getText());
            if (ngheNghiepLabel.getText()=="" ){
                preparedStatement.setString(10, null);
            }else{
                preparedStatement.setString(10, ngheNghiepLabel.getText());
            }

            if (noiLamViecLabel.getText()=="" ){
                preparedStatement.setString(11, null);
            }else{
                preparedStatement.setString(11, noiLamViecLabel.getText());
            }

            if (CMNDLabel.getText()=="" ){
                preparedStatement.setString(12, null);
            }else{
                preparedStatement.setString(12, CMNDLabel.getText());
            }

            if (ngayCapLabel.getValue()==null ){
                preparedStatement.setString(13, null);
            }else{
                preparedStatement.setString(13, String.valueOf(ngayCapLabel.getValue()));
            }
            if (chuyenDenNgayLabel.getValue()==null ){
                preparedStatement.setString(14, null);
            }else{
                preparedStatement.setString(14, String.valueOf(chuyenDenNgayLabel.getValue()));
            }


            if (noiThuongTruTruocLabel.getText()=="" ){
                preparedStatement.setString(15, null);
            }else{
                preparedStatement.setString(15, noiThuongTruTruocLabel.getText());
            }


            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



    public void goBack_chinhSuaNK(ActionEvent e) throws IOException {
        hoTenLabel.setText(null);
        biDanhLabel.setText(null);
        ngaySinhLabel.setValue(null);
        noiSinhLabel.setText(null);
        nguyenQuanLabel.setText(null);
        danTocLabel.setText(null);
        tonGiaoLabel.setText(null);
        quocTichLabel.setText(null);
        ngheNghiepLabel.setText(null);
        noiLamViecLabel.setText(null);
        CMNDLabel.setText(null);
        ngayCapLabel.setValue(null);
        chuyenDenNgayLabel.setValue(null);
        noiThuongTruTruocLabel.setText(null);
    }
}
