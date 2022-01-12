package controller.phanThuong;

import controller.nhanKhau.ThongTinNhanKhauController;
import entity.ChiTietDipHocSinhGioi;
import entity.DipHocSinhGioi;
import entity.NhanKhauHocSinhGioi;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lombok.SneakyThrows;
import repository.HocSinhGioiRepositoryImpl;
import repository.HocSinhGioiRepository;
import utility.Message;
import utility.Variable;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.Optional;
import java.util.ResourceBundle;

public class MinhChungNhanThuongController implements Initializable {

    @FXML
    private Label namHoc;
    @FXML
    private Label idNhanKhau;
    @FXML
    private Label tenNhanKhau;
    @FXML
    private TextField truongHoc;
    @FXML
    private TextField lopHoc;
    @FXML
    private ChoiceBox<String> thanhTich;
    @FXML
    private ImageView anhMinhChung;

    private HocSinhGioiRepository hocSinhGioiImpl = new HocSinhGioiRepositoryImpl();
    private DipHocSinhGioi dipHocSinhGioi;

    public void setThongTin(DipHocSinhGioi dipHocSinhGioi, int idNhanKhau, String tenNhankhau) {
        this.dipHocSinhGioi = dipHocSinhGioi;
        namHoc.setText(String.valueOf(dipHocSinhGioi.getNam()));
        this.idNhanKhau.setText(String.valueOf(idNhanKhau));
        this.tenNhanKhau.setText(String.valueOf(tenNhankhau));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thanhTich.setItems(FXCollections.observableArrayList(Variable.DAC_BiET, Variable.GIOI, Variable.KHA));
    }

    @SneakyThrows
    public void taiAnhMinhChung(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
        if (file != null) {
            String mimetype = Files.probeContentType(file.toPath());
            if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                String url = file.getPath();
                Image image = new Image(url);
                anhMinhChung.setImage(image);
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(Message.yeuCauDoiFileAnh);
                alert.show();
            }
        }
    }

    public void xacNhanClick(MouseEvent mouseEvent) {
        try{
            int test = Integer.valueOf(lopHoc.getText());
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Trường lớp phải là số nguyên");
            alert.show();
            return;
        }

        if (truongHoc.getText().isEmpty() || lopHoc.getText().isEmpty() || thanhTich.getValue().isEmpty() || anhMinhChung.getImage() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(Message.xacNhanThemMinhChung);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                ChiTietDipHocSinhGioi chiTietDipHocSinhGioi = new ChiTietDipHocSinhGioi();
                chiTietDipHocSinhGioi.setIdDip(dipHocSinhGioi.getIdDip());
                chiTietDipHocSinhGioi.setIdNhanKhau(Integer.valueOf(idNhanKhau.getText()));
                chiTietDipHocSinhGioi.setTruong(truongHoc.getText());
                chiTietDipHocSinhGioi.setLop(lopHoc.getText());
                chiTietDipHocSinhGioi.setKiemTra(false);
                chiTietDipHocSinhGioi.setNhom(NhanKhauHocSinhGioi.getNhom(thanhTich.getValue()));
                chiTietDipHocSinhGioi.setMinhChung(anhMinhChung.getImage().getUrl());
                hocSinhGioiImpl.themMinhChung(chiTietDipHocSinhGioi);
                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                stage.close();
            }
        }
    }

    public void huyClick(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        stage.close();
    }
}
