package controller.thongKe;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lombok.SneakyThrows;
import repository.HoKhauRepository;
import repository.HoKhauRepositoryImpl;

import java.net.URL;
import java.util.ResourceBundle;

public class ThongKeHoKhauController implements Initializable {

    @FXML
    private Pane mainPane;

    @FXML
    private Pane trangThai;

    @FXML
    private Pane soLuong;

    HoKhauRepository hoKhauRepository = new HoKhauRepositoryImpl();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // trangThai
        ObservableList<PieChart.Data> dataTrangThai = FXCollections.observableArrayList(
                new PieChart.Data("Thường trú", hoKhauRepository.tongHoKhauThuongTru()),
                new PieChart.Data("Đã chuyển đi", hoKhauRepository.tongHoKhauDaChuyenDi()));
        final PieChart chartTrangThai = new PieChart(dataTrangThai);
        dataTrangThai.forEach(data ->
                data.nameProperty().bind((
                        Bindings.concat(data.getName(), " ", data.pieValueProperty().intValue())
                )));
        chartTrangThai.setPrefSize(400, 400);
        chartTrangThai.setLegendVisible(false);

        trangThai.getChildren().add(chartTrangThai);

        // soLuong
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Số lượng thành viên");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Số hộ khẩu");

        BarChart barChart = new BarChart(xAxis, yAxis);

        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Số lượng thành viên");
        for (Integer i : hoKhauRepository.soLuongThanhVien().keySet()) {
            dataSeries.getData().add(new XYChart.Data(String.valueOf(i), hoKhauRepository.soLuongThanhVien().get(i)));
        }
        barChart.getData().add(dataSeries);

        barChart.setPrefSize(550, 350);
        barChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");
        soLuong.getChildren().add(barChart);
    }

    @SneakyThrows
    public void backClick(MouseEvent mouseEvent) {
        Pane pane = FXMLLoader.load(getClass().getResource("/view/thongKe/thongKe.fxml"));
        mainPane.getChildren().clear();
        mainPane.getChildren().add(pane);
    }
}
