package controller.nhanKhau;

import entity.NhanKhau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

import static utility.SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN;

public class KhaiTuController implements Initializable {

    @FXML
    private TableView<NhanKhau> table;
    @FXML
    private TableColumn<NhanKhau,Integer> idColumn;
    @FXML
    private TableColumn<NhanKhau,String>  hoTenColumn;
    @FXML
    private TableColumn<NhanKhau,String>  ngaySinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  gioiTinhColumn;
    @FXML
    private TableColumn<NhanKhau,String>  CMNDColumn;
    @FXML
    private TableColumn<NhanKhau,String>  trangThaiColumn;
    @FXML
    private ComboBox truongTraCuuF;
    @FXML
    private TextField duLieuF;
    @FXML
    private ImageView confirmF;
    @FXML
    Label ngaySinhLabel;
    @FXML
    Label hoTenLabel;
    @FXML
    DatePicker ngayMatF;
    @FXML
    DatePicker ngayKhaiBaoF;
    @FXML
    TextArea lyDoF;
    @FXML
    Label nguoiKhaiBaoF;
    @FXML
    Label ngaySinhKB;
    private String query = null;
    private String query_update = null;
    private String query_hoTen=null;
    private String query_CMND=null;
    private String query_trangThai=null;
    private String query_nguyenQuan=null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    NhanKhau nhanKhau = null ;
    private String truongTraCuu=null;
    private String duLieuTraCuu=null;
    private int idNguoiMat=-1;
    private int idNguoiKhaiBao=-1;
    @FXML
    ObservableList<NhanKhau>  nhanKhauList2 = FXCollections.observableArrayList();
    @FXML
     private void Select_KT(ActionEvent event) {
        truongTraCuu = truongTraCuuF.getSelectionModel().getSelectedItem().toString();

    }
    @SneakyThrows
    @FXML
    private void findF(MouseEvent event) {

        try {
            nhanKhauList2.clear();
            duLieuTraCuu=duLieuF.getText();
            query_hoTen="SELECT * FROM `nhan_khau` WHERE hoTen like '%" + duLieuTraCuu+"%'";
            query_CMND="SELECT * FROM `nhan_khau` WHERE cmnd like '%" + duLieuTraCuu+"%'";
            query_trangThai="SELECT * FROM `nhan_khau` WHERE trangThai like '%" + duLieuTraCuu+"%'";
            query_nguyenQuan="SELECT * FROM `nhan_khau` WHERE nguyenQuan like '%" + duLieuTraCuu+"%'";
            if(truongTraCuu=="Họ tên"){
                preparedStatement = connection.prepareStatement(query_hoTen);
            } else if(truongTraCuu=="Chứng minh nhân dân"){
                preparedStatement = connection.prepareStatement(query_CMND);
            }else if(truongTraCuu=="Trạng thái"){
                preparedStatement = connection.prepareStatement(query_trangThai);
            }else if(truongTraCuu=="Nguyên quán"){
                preparedStatement = connection.prepareStatement(query_nguyenQuan);
            }

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String bieuDienNgaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);
                nhanKhauList2.add(new NhanKhau(
                        resultSet.getInt("idNhanKhau"),
                        resultSet.getString("hoTen"),
                        bieuDienNgaySinh,
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("CMND"),
                        resultSet.getString("trangThai")));
                table.setItems(nhanKhauList2);

            }


        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    @FXML
    private void handleClickTableView(MouseEvent click) {
        NhanKhau userlist = table.getSelectionModel().getSelectedItem();
        if (userlist != null) {
               nguoiKhaiBaoF.setText(userlist.getHoTen());
               ngaySinhKB.setText(userlist.getBieuDienNgaySinh());
               idNguoiKhaiBao=userlist.getId();
        }
    }

    @SneakyThrows
    @FXML
    private void save_khaiTu(MouseEvent event) {

        connection = DbUtil.getInstance().getConnection();
        LocalDate ngayMat = ngayMatF.getValue();
        LocalDate ngayKhaiBao = ngayKhaiBaoF.getValue();
        String hoTenNguoiKhaiBao = nguoiKhaiBaoF.getText();



        if (ngayMat==null||ngayKhaiBao==null || hoTenNguoiKhaiBao.isEmpty() ||idNguoiKhaiBao==-1||idNguoiMat==-1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Nhập các trường dữ liệu bắt buộc");
            alert.showAndWait();

        } else {


            getQuery();
            insert();
            update();
            Alert alert_TC = new Alert(Alert.AlertType.CONFIRMATION);
            alert_TC.setHeaderText(null);
            alert_TC.setContentText("Thêm thành công");
            alert_TC.showAndWait();

        }

    }
    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listTruongTraCuu = FXCollections.observableArrayList("Họ tên","Chứng minh nhân dân","Trạng thái","Nguyên quán");
        truongTraCuuF.setItems(listTruongTraCuu);
        loadData();


    }


    public void setKhaiTu(NhanKhau nk){
        ngaySinhLabel.setText(nk.getBieuDienNgaySinh());
        hoTenLabel.setText((nk.getHoTen()));
        idNguoiMat=nk.getId();
    }

    @FXML
    private void refreshTable() {
        try {
            nhanKhauList2.clear();


            preparedStatement = connection.prepareStatement(NHAN_KHAU_QUERY_LAY_THONG_TIN);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                String bieuDienNgaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);
                nhanKhauList2.add(new NhanKhau(
                        resultSet.getInt("idNhanKhau"),
                        resultSet.getString("hoTen"),
                        bieuDienNgaySinh,
                        resultSet.getString("gioiTinh"),
                        resultSet.getString("CMND"),
                        resultSet.getString("trangThai")));
                table.setItems(nhanKhauList2);

            }


        } catch (SQLException ex) {
            Logger.getLogger(NhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }



    }
    private void loadData() throws SQLException {
        connection = DbUtil.getInstance().getConnection();
        refreshTable();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        hoTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("bieuDienNgaySinh"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        CMNDColumn.setCellValueFactory(new PropertyValueFactory<>("CMND"));
        trangThaiColumn.setCellValueFactory(new PropertyValueFactory<>("trangThai"));
        table.setItems(nhanKhauList2);
    }
    private void getQuery() {
        query = "INSERT INTO `khai_tu`(`idNguoiMat`, `idNguoiKhai`, `ngayKhai`, `ngayMat`, `liDoMat`) VALUES (?,?,?,?,?)";
        query_update = "UPDATE `nhan_khau` SET " +

                "`trangThai`=?  WHERE idNhanKhau  = '"+idNguoiMat+"'";

    }
    private void insert() {



        try {

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(idNguoiMat));
            preparedStatement.setString(2, String.valueOf(idNguoiKhaiBao));
            preparedStatement.setString(3,  String.valueOf(ngayKhaiBaoF.getValue()));
            preparedStatement.setString(4,  String.valueOf(ngayMatF.getValue()));
            if (lyDoF.getText()=="" ){
                preparedStatement.setString(5, null);
            }else{
                preparedStatement.setString(5, lyDoF.getText());
            }



            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(KhaiTuController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private void update() {

        try {

            preparedStatement = connection.prepareStatement(query_update);
            preparedStatement.setString(1, "Đã mất");

            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ThemNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
