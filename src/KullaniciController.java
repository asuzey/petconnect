import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KullaniciController {

    @FXML
    private TableView<Kullanici> CalisanTableView;

    @FXML
    private TableColumn<Kullanici, String> adSoyadColumn;

    @FXML
    private TableColumn<Kullanici, String> telefonColumn;

    @FXML
    private TableColumn<Kullanici, String> emailColumn;

    @FXML
    private TableColumn<Calisan, String> gorevColumn;

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
    private TableView<Hayvan> hayvanlarTableView;
    @FXML
    private Pane hayvanListPanel;
    @FXML
    private ComboBox<String> cbRol; // ComboBox
    @FXML
    private TextField txtKullaniciAdi, txtSifre, txtAdSoyad, txtTelefon, txtEmail, txtGorev; // TextField'lar

    @FXML
    public void initialize() {
        System.out.println("Initialize metodu çalışıyor");

        // HAYVAN LİSTESİ TABLEVİEW

        // Sütunları verilerle ilişkilendir - Hayvan tablosu için
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

        cbRol.getItems().addAll("Admin", "Calisan");

        // ComboBox seçimi değiştiğinde yapılacak işlemler
        cbRol.valueProperty().addListener((observable, oldValue, newValue) -> {
            if ("Admin".equals(newValue)) {
                // Admin seçiliyse TextField'ları disable yap
                txtAdSoyad.setDisable(true);
                txtTelefon.setDisable(true);
                txtEmail.setDisable(true);
                txtGorev.setDisable(true);
            } else if ("Calisan".equals(newValue)) {
                // Çalisan seçiliyse TextField'ları tekrar aktif yap
                txtAdSoyad.setDisable(false);
                txtTelefon.setDisable(false);
                txtEmail.setDisable(false);
                txtGorev.setDisable(false);
            }

            // KULLANİCİ LİSTESİ TABLEVİEW

            // Kolonları sınıflardaki değişkenlerle eşleştirme - Kullanıcı tablosu için
            adSoyadColumn.setCellValueFactory(new PropertyValueFactory<>("adSoyad"));
            telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefon"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            gorevColumn.setCellValueFactory(new PropertyValueFactory<>("gorev"));

            // Yalnızca çalışanları listele
            ObservableList<Calisan> calisanListesi = DB_Connection.getCalisanlar();
            CalisanTableView.setItems(calisanListesi);
        });
    }

    public void cikisYap(ActionEvent event) {
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
    public void HayvanListPanel(ActionEvent event) {
        // Panelin görünürlük durumunu tersine çevir
        boolean isVisible = hayvanListPanel.isVisible();
        hayvanListPanel.setVisible(!isVisible);

        // Eğer aynı zamanda panel yönetiminden çıkmasını istiyorsanız:
        hayvanListPanel.setManaged(!isVisible);
    }

    @FXML
    private void handleYeniKullaniciEkle(ActionEvent event) {
        System.out.println("handleYeniKullaniciEkle calisiyor"); //debug
        showAlert(Alert.AlertType.INFORMATION, "Debug", "Buraya geldi.");

        // Kullanıcı adı ve şifre kontrolü
        if (txtKullaniciAdi.getText().isEmpty() || txtSifre.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Kullanıcı adı ve şifre alanlarını doldurun.");
            return;
        }

        // Seçilen rolü al
        String selectedRole = cbRol.getValue();

        // Rol kontrolü yap
        if (selectedRole == null || selectedRole.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Lütfen bir rol seçin.");
            return;
        }

        try {
            if ("Admin".equals(selectedRole)) {
                // Admin nesnesi oluştur
                Kullanici admin = new Kullanici(
                        txtKullaniciAdi.getText(),
                        txtSifre.getText(),
                        selectedRole);

                // Veritabanına ekle
                DB_Connection.kullaniciEkleVeIDAl(admin);
                System.out.println("Kullanıcı admin eklendi.");// debugging

                showAlert(Alert.AlertType.INFORMATION, "Başarılı", "Admin başarıyla eklendi.");
            } else if ("Calisan".equals(selectedRole)) {
                // Çalışan alanlarının doldurulduğunu kontrol et
                if (txtAdSoyad.getText().isEmpty() || txtTelefon.getText().isEmpty() ||
                        txtEmail.getText().isEmpty() || txtGorev.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, "Hata", "Tüm alanları doldurun.");
                    return;
                }

                try {
                    // İlk olarak Kullanıcı tablosuna kayıt ekle
                    Kullanici kullanici = new Kullanici(
                            txtKullaniciAdi.getText(),
                            txtSifre.getText(),
                            selectedRole
                    );

                    int kullaniciID = DB_Connection.kullaniciEkleVeIDAl(kullanici); // Kullanıcı eklenip ID geri alınır

                    if ("Çalışan".equals(selectedRole)) {
                        // Çalışan nesnesi oluştur
                        Calisan calisan = new Calisan(
                                kullaniciID, // Alınan KullaniciID buraya gönderiliyor
                                txtAdSoyad.getText(),
                                txtTelefon.getText(),
                                txtEmail.getText(),
                                txtGorev.getText()
                        );

                        // Çalışan tablosuna kayıt ekle
                        DB_Connection.calisanEkle(calisan);
                        System.out.println("Çalışan başarıyla eklendi.");
                        showAlert(Alert.AlertType.INFORMATION, "Başarılı", "Çalışan başarıyla eklendi.");
                    }
                } catch (SQLException e) {
                    showAlert(Alert.AlertType.ERROR, "Hata", "Veritabanı hatası: " + e.getMessage());
                    e.printStackTrace();
                }
            }else {
                showAlert(Alert.AlertType.ERROR, "Hata", "Bilinmeyen rol: " + selectedRole);
            }
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Hata", "Bir hata oluştu: " + e.getMessage());
        }

        // debugging icin
        System.out.println("Rol: " + cbRol.getValue());
        System.out.println("Kullanıcı Adı: " + txtKullaniciAdi.getText());

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }



}
