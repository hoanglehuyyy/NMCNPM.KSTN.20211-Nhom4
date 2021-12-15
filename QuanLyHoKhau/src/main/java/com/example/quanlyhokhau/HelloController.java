package com.example.quanlyhokhau;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable{
    @FXML
    private TableView<Hokhau> table;

    @FXML
    private TableColumn<Hokhau, Integer> id_ho_khauCol;

    @FXML
    private TableColumn<Hokhau, Integer> id_chu_ho_khauCol;

    @FXML
    private TableColumn<Hokhau, String> address_ho_khauCol;

    @FXML
    private TableColumn<Hokhau, String> thanhpho_ho_khauCol;

    @FXML
    private TableColumn<Hokhau, String> quanhuyen_ho_khauCol;

    @FXML
    private TableColumn<Hokhau, String> phuongxa_ho_khauCol;

    @FXML
    private TableColumn<Hokhau, String> ngaytao_ho_khauCol;

    @FXML
    private TextField search_ho_khau;

    @FXML
    private ComboBox<String> comboBox;

    private ObservableList<String> list_combo_box = FXCollections.observableArrayList("All","Mã hộ khẩu","Mã chủ hộ","Địa chỉ");


    private ObservableList<Hokhau> hokhauList;

    private ObservableList<Hokhau> searchList;


    //checked//
    public void search_all(ActionEvent event){
        String sc = comboBox.getValue();
        if(sc.equals("All")){
            table.setItems(hokhauList);
        }
        else{
            return;
        }
    }

    //checked//
    public void search_hk(ActionEvent event){
        searchList.clear();
        String search_text = search_ho_khau.getText().trim().toLowerCase(); ;
        String sc = comboBox.getValue();
        if(sc.equals("All")){
            return;
        }
        else if(sc.equals("Mã hộ khẩu")){
            for(Hokhau a : hokhauList){
                try{
                    if(a.getId_ho_khau() == Integer.parseInt(search_text)){
                        Hokhau clone_hk = new Hokhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }catch(NumberFormatException e){
                    Alert m = new Alert(Alert.AlertType.INFORMATION);
                    m.setTitle("Alert!");
                    m.setHeaderText("Không thoả mãn trường dữ liệu!");
                    m.setContentText("Mời nhập lại!");
                    m.show();
                    return;
                }

            }
            table.setItems(searchList);
        }
        else if(sc.equals("Địa chỉ")){
            for(Hokhau a : hokhauList){
                if((a.getAddress_ho_khau().toLowerCase()).contains(search_text)){
                    Hokhau clone_hk = new Hokhau();
                    clone_hk.copy_hk(a);
                    searchList.add(clone_hk);
                }
            }
            table.setItems(searchList);
        }
        else if(sc.equals("Mã chủ hộ")){
            for(Hokhau a : hokhauList){
                try{
                    if(a.getId_chu_ho() == Integer.parseInt(search_text)){
                        Hokhau clone_hk = new Hokhau();
                        clone_hk.copy_hk(a);
                        searchList.add(clone_hk);
                    }
                }catch(NumberFormatException e){
                    Alert m = new Alert(Alert.AlertType.INFORMATION);
                    m.setTitle("Alert!");
                    m.setHeaderText("Không thoả mãn trường dữ liệu!");
                    m.setContentText("Mời nhập lại!");
                    m.show();
                    return;
                }

            }
            table.setItems(searchList);
        }
    }
    //checked//
    public void add(ActionEvent event) throws IOException {
        Parent them_ho_khau = FXMLLoader.load(getClass().getResource("them-ho-khau.fxml"));
        Scene scene = new Scene(them_ho_khau);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Add");
        stage.setScene(scene);
        stage.show();
    }
    //checked//
    public void delete(ActionEvent event){
        Hokhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete");
        alert.setHeaderText("Do you want to delete?");
        alert.setContentText("Choose your option:");

        ButtonType buttonYes = new ButtonType("Yes",ButtonBar.ButtonData.YES);
        ButtonType buttonNo = new ButtonType("No",ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(buttonYes,buttonNo);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Message");
        alert1.setHeaderText("Notification");

        if(result.get().getButtonData() == ButtonBar.ButtonData.YES){
            hokhauList.remove(hk);
            alert1.setContentText("Successful");
            alert1.show();
        }
        else if(result.get().getButtonData() == ButtonBar.ButtonData.NO){
            alert1.setContentText("Failed");
            alert1.show();
        }

    }

    //checked//
    public void show(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("show-ho-khau.fxml"));
        Parent show_ho_khau = loader.load();
        showHK controller = loader.getController();
        Hokhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.show_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Show");
        Scene scene = new Scene(show_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void change(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("change-ho-khau.fxml"));
        Parent change_ho_khau = loader.load();
        changeHK controller = loader.getController();
        Hokhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.change_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Change");
        Scene scene = new Scene(change_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void tach(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("tach-ho-khau.fxml"));
        Parent tach_ho_khau = loader.load();
        tachHK controller = loader.getController();
        Hokhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.tach_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Tach");
        Scene scene = new Scene(tach_ho_khau);
        stage.setScene(scene);
        stage.show();
    }

    //checked//
    public void chuyen(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("chuyen-ho-khau.fxml"));
        Parent chuyen_ho_khau = loader.load();
        chuyenHK controller = loader.getController();
        Hokhau hk = table.getSelectionModel().getSelectedItem();
        if (hk == null) {
            Alert m = new Alert(Alert.AlertType.INFORMATION);
            m.setTitle("Alert!");
            m.setHeaderText("Không hộ khẩu nào được chọn.");
            m.setContentText("Vui lòng chọn lại.");
            m.show();
            return;
        }
        controller.chuyen_hk(hk);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.DECORATED);
        stage.setTitle("Chuyen");
        Scene scene = new Scene(chuyen_ho_khau);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCol();
        loadData();
    }

    private void initCol(){
        id_ho_khauCol.setCellValueFactory(new PropertyValueFactory<Hokhau,Integer>("id_ho_khau"));
        id_chu_ho_khauCol.setCellValueFactory(new PropertyValueFactory<Hokhau,Integer>("id_chu_ho"));
        address_ho_khauCol.setCellValueFactory(new PropertyValueFactory<Hokhau,String>("address_ho_khau"));
    }

    private void loadData(){
        hokhauList = FXCollections.observableArrayList(
                new Hokhau(1,10,"127B Duong A","Thanh Hoa","15-12-2021"),
                new Hokhau(2,20,"128B Duong A","Nam Dinh","P","Q","15-12-2021"),
                new Hokhau(3,30,"129B Duong A","Hung Yen","X","Y","15-12-2021"),
                new Hokhau(4,40,"127B Duong A","Hung Yen","15-12-2021"),
                new Hokhau(5,50,"129B Duong X","Quang Tri","15-12-2021"),
                new Hokhau(6,60,"130B Duong X","Hai Duong","Z","W","15-12-2021"),
                new Hokhau(7,70,"131B Duong A","Phu Tho","15-12-2021"),
                new Hokhau(8,80,"131C Duong A","Thanh Hoa","15-12-2021")

        );
        searchList = FXCollections.observableArrayList();
        table.setItems(hokhauList);
        comboBox.setItems(list_combo_box);
    }





}
