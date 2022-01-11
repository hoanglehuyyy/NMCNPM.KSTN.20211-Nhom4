package controller.phanThuong;

import controller.nhanKhau.ThongTinNhanKhauController;
import entity.ChiTietDipHocSinhGioi;
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

public class SuaMinhChungNhanThuongController implements Initializable {

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
    private ChiTietDipHocSinhGioi chiTietDipHocSinhGioi;
    private String anhPath;

    public void setThongTin(int nam, ChiTietDipHocSinhGioi chiTietDipHocSinhGioi, String tenNhankhau) {
        this.chiTietDipHocSinhGioi = chiTietDipHocSinhGioi;
        namHoc.setText(String.valueOf(nam));
        idNhanKhau.setText(String.valueOf(chiTietDipHocSinhGioi.getIdNhanKhau()));
        this.tenNhanKhau.setText(String.valueOf(tenNhankhau));
        truongHoc.setText(chiTietDipHocSinhGioi.getTruong());
        lopHoc.setText(chiTietDipHocSinhGioi.getLop());
        thanhTich.setValue(NhanKhauHocSinhGioi.getThanhTich(chiTietDipHocSinhGioi.getNhom()));
        Image image = new Image(chiTietDipHocSinhGioi.getMinhChung());
        anhPath = chiTietDipHocSinhGioi.getMinhChung();
        anhMinhChung.setImage(image);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        thanhTich.setItems(FXCollections.observableArrayList(Variable.DAC_BiET, Variable.GIOI, Variable.KHA));
    }

    @SneakyThrows
    public void taiAnhMinhChung(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(anhPath).getParentFile());
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String mimetype = Files.probeContentType(file.toPath());
            if (mimetype != null && mimetype.split("/")[0].equals("image")) {
                anhPath = file.getPath();
                Image image = new Image(anhPath);
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
        if (truongHoc.getText().isEmpty() || lopHoc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(Message.yeuCauNhapDayDu);
            alert.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(Message.xacNhanSuaMinhChung);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                hocSinhGioiImpl.chinhSuaMinhChung(chiTietDipHocSinhGioi.getIdDip(), chiTietDipHocSinhGioi.getIdNhanKhau(), truongHoc.getText(), lopHoc.getText(), NhanKhauHocSinhGioi.getNhom(thanhTich.getValue()), anhPath);
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
