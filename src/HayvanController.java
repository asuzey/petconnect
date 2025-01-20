//package com.example;
import com.sun.javafx.charts.Legend;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.SQLException;

public class HayvanController { //hayvansever_giris.fxml sayfasının controllerı

    @FXML
    private javafx.scene.control.ComboBox<String> durumComboBox;

    @FXML
    private javafx.scene.control.ComboBox<String> turComboBox;

    @FXML
    private javafx.scene.control.ComboBox<String> yasComboBox;

    @FXML
    private Button filtreleButton;

    @FXML
    private TableView<Hayvan> hayvanlarTableView;

    @FXML
    private TableColumn<Hayvan, String> adColumn;
    @FXML
    private TableColumn<Hayvan, String> turColumn;
    @FXML
    private TableColumn<Hayvan, String> cinsColumn;
    @FXML
    private TableColumn<Hayvan, Integer> yasColumn;
    @FXML
    private TableColumn<Hayvan, String> durumColumn;
    @FXML
    private TableColumn<Hayvan, Boolean> asiliMiColumn;
    @FXML
    private TableColumn<Hayvan, Boolean> kisirMiColumn;
    @FXML
    private TableColumn<Hayvan, String> aciklamaColumn;


    @FXML
    public void initialize() {
        // ComboBox içeriklerini doldur
        durumComboBox.getItems().addAll("Sahipsiz", "Kayıp");
        turComboBox.getItems().addAll("Kedi", "Köpek");
        yasComboBox.getItems().addAll("Yavru (0-6 ay)", "Genç (6 ay-2 yaş)", "Yetişkin (3-10 yaş)", "Yaşlı (11+ yaş)");

        // Sütunları verilerle ilişkilendir
        adColumn.setCellValueFactory(new PropertyValueFactory<>("ad"));
        turColumn.setCellValueFactory(new PropertyValueFactory<>("tur"));
        cinsColumn.setCellValueFactory(new PropertyValueFactory<>("cins"));
        yasColumn.setCellValueFactory(new PropertyValueFactory<>("yas"));
        durumColumn.setCellValueFactory(new PropertyValueFactory<>("durum"));
        asiliMiColumn.setCellValueFactory(new PropertyValueFactory<>("asiliMi"));
        kisirMiColumn.setCellValueFactory(new PropertyValueFactory<>("kisirMi"));
        aciklamaColumn.setCellValueFactory(new PropertyValueFactory<>("aciklama"));

        try {
            ObservableList<Hayvan> hayvanListesi = DB_Connection.getHayvanlar(); // Veritabanından verileri çek
            hayvanlarTableView.setItems(hayvanListesi); // TableView'e verileri ekle
        } catch (SQLException e) {
            e.printStackTrace();
            // Hata mesajı göster
            System.err.println("Veritabanı hatası: " + e.getMessage());
        }

        filtreleButton.setOnAction(event -> {
            try {
                String seciliDurum = durumComboBox.getValue();
                String seciliTur = turComboBox.getValue();
                String seciliYas = yasComboBox.getValue();

                StringBuilder query = new StringBuilder("SELECT * FROM Hayvan WHERE 1=1");

                if (seciliDurum != null && !seciliDurum.isEmpty()) {
                    query.append(" AND durum = '").append(seciliDurum).append("'");
                }

                if (seciliTur != null && !seciliTur.isEmpty()) {
                    query.append(" AND tur = '").append(seciliTur).append("'");
                }

                if (seciliYas != null && !seciliYas.isEmpty()) {
                    if (seciliYas.equals("Yavru (0-6 ay)")) {
                        query.append(" AND yas <= 0.5");
                    } else if (seciliYas.equals("Genç (6 ay-2 yaş)")) {
                        query.append(" AND yas > 0.5 AND yas <= 2");
                    } else if (seciliYas.equals("Yetişkin (3-10 yaş)")) {
                        query.append(" AND yas > 2 AND yas <= 10");
                    } else if (seciliYas.equals("Yaşlı (11+ yaş)")) {
                        query.append(" AND yas > 10");
                    }
                }

                ObservableList<Hayvan> filtrelenmisListe = DB_Connection.getFilteredHayvanlar(query.toString());
                hayvanlarTableView.setItems(filtrelenmisListe);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Filtreleme sırasında hata: " + e.getMessage());
            }
        });

    }

    public void cikisYap (ActionEvent event) {
        try {
            // Yeni sahneyi yükle
            FXMLLoader loader = new FXMLLoader(getClass().getResource("petconnect.fxml"));
            Parent root = loader.load();

            // Sahneyi ve Stage'i al
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);

            // Yeni sahneyi ayarla
            stage.setScene(scene);
            stage.centerOnScreen(); // Ortaya yerleştir
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void filtrelemeyiKaldir(ActionEvent event) {
        // Burada filtreleme işlemini sıfırlayabilirsiniz
        System.out.println("Filtreleme kaldırıldı!");
        // ComboBox seçimlerini temizlemek için:
        durumComboBox.getSelectionModel().clearSelection();
        turComboBox.getSelectionModel().clearSelection();
        yasComboBox.getSelectionModel().clearSelection();

        try {
            // Tüm hayvanları getir
            ObservableList<Hayvan> hayvanListesi = DB_Connection.getHayvanlar();
            hayvanListesi = DB_Connection.getHayvanlar();
            hayvanlarTableView.setItems(hayvanListesi);


            // eger liste mevcutsa listeyi guncellio
            if (hayvanlarTableView != null) {
                hayvanlarTableView.setItems(hayvanListesi);
            } else {
                System.err.println("TableView doğru şekilde yüklenemedi.");
            }


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}

