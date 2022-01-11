package controller.nhanKhau;

import entity.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utility.DbUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utility.SQLCommand.NHAN_KHAU_QUERY_LAY_THONG_TIN;

public class ThongTinNhanKhauController {
       @FXML
       Label hoTenLabel;
       @FXML
       Label biDanhLabel;
       @FXML
       Label ngaySinhLabel;
       @FXML
       Label noiSinhLabel;
       @FXML
       Label gioiTinhLabel;
       @FXML
       Label nguyenQuanLabel;
       @FXML
       Label danTocLabel;
       @FXML
       Label tonGiaoLabel;
       @FXML
       Label quocTichLabel;
       @FXML
       Label ngheNghiepLabel;
       @FXML
       Label noiLamViecLabel;
       @FXML
       Label CMNDLabel;
       @FXML
       Label ngayCapLabel;
       @FXML
       Label chuyenDenNgayLabel;
       @FXML
       Label noiThuongTruTruocLabel;
       @FXML
       Label trangThaiLabel;
       private int id_NK;
       Connection connection = null;
       ResultSet resultSet = null;
       PreparedStatement preparedStatement;

       public void setNhanKhau(NhanKhau nk)  {

              id_NK=nk.getId();
              clean();
              loadData();

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
                                   String ngaySinh = resultSet.getString("ngaySinh").substring(8)+"-"+resultSet.getString("ngaySinh").substring(5,7)+"-"+resultSet.getString("ngaySinh").substring(0,4);
                                   ngaySinhLabel.setText(ngaySinh);
                                   noiSinhLabel.setText(resultSet.getString("noiSinh"));
                                   gioiTinhLabel.setText(resultSet.getString("gioiTinh"));
                                   nguyenQuanLabel.setText(resultSet.getString("nguyenQuan"));
                                   danTocLabel.setText(resultSet.getString("danToc"));
                                   tonGiaoLabel.setText(resultSet.getString("tonGiao"));
                                   quocTichLabel.setText(resultSet.getString("quocTich"));
                                   ngheNghiepLabel.setText(resultSet.getString("ngheNghiep"));
                                   noiLamViecLabel.setText(resultSet.getString("noiLamViec"));
                                   CMNDLabel.setText(resultSet.getString("cmnd"));

                                   if(resultSet.getString("ngayCap")!=null&&resultSet.getString("ngayCap")!=""){
                                          String ngayCap = resultSet.getString("ngayCap").substring(8)+"-"+resultSet.getString("ngayCap").substring(5,7)+"-"+resultSet.getString("ngayCap").substring(0,4);
                                          ngayCapLabel.setText(ngayCap);
                                   }else{
                                          String ngayCap="";
                                          ngayCapLabel.setText(ngayCap);
                                   }

                                   if(resultSet.getString("chuyenDenNgay")!=null&&resultSet.getString("chuyenDenNgay")!=""){
                                          String chuyenDenNgay = resultSet.getString("chuyenDenNgay").substring(8)+"-"+resultSet.getString("chuyenDenNgay").substring(5,7)+"-"+resultSet.getString("chuyenDenNgay").substring(0,4);
                                          chuyenDenNgayLabel.setText(chuyenDenNgay);
                                   }else{
                                          String chuyenDenNgay="";
                                          chuyenDenNgayLabel.setText(chuyenDenNgay);
                                   }

                                   noiThuongTruTruocLabel.setText(resultSet.getString("noiThuongTruTruoc"));
                                   trangThaiLabel.setText(resultSet.getString("trangThai"));

                            }



                     }


              } catch (SQLException ex) {
                     Logger.getLogger(ThongTinNhanKhauController.class.getName()).log(Level.SEVERE, null, ex);
              }



       }

       @FXML
       private void clean() {
              hoTenLabel.setText(null);
              biDanhLabel.setText(null);
              ngaySinhLabel.setText(null);
              noiSinhLabel.setText(null);
              gioiTinhLabel.setText(null);
              nguyenQuanLabel.setText(null);
              danTocLabel.setText(null);
              tonGiaoLabel.setText(null);
              quocTichLabel.setText(null);
              ngheNghiepLabel.setText(null);
              noiLamViecLabel.setText(null);
              CMNDLabel.setText(null);
              ngayCapLabel.setText(null);
              chuyenDenNgayLabel.setText(null);
              noiThuongTruTruocLabel.setText(null);
              trangThaiLabel.setText(null);


       }




       public void goBack_thongtinNK(ActionEvent e) throws IOException {
              Stage stage = (Stage)((Node)e.getSource()).getScene().getWindow();
              FXMLLoader loader= new FXMLLoader();
              loader.setLocation(getClass().getResource("/view/nhanKhau/nhanKhau.fxml"));
              Parent sampleParent=loader.load();
              Scene scene= new Scene(sampleParent);
              stage.setScene(scene);
       }

       

}
