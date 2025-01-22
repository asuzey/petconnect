import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;


import java.sql.SQLException;

public class KullaniciController {

    @FXML
    private TableView<Hayvan> hayvanlarTableViewonHayvanEkle;

    @FXML
    private TableColumn<Hayvan, String> adColumn2;

    @FXML
    private TableColumn<Hayvan, String> turColumn2;

    @FXML
    private TableColumn<Hayvan, String> cinsColumn2;

    @FXML
    private TableColumn<Hayvan, Integer> yasColumn2;

    @FXML
    private TableColumn<Hayvan, String> durumColumn2;

    @FXML
    private TableColumn<Hayvan, Boolean> asiliMiColumn1;

    @FXML
    private TableColumn<Hayvan, Boolean> kisirMiColumn1;

    @FXML
    private TableColumn<Hayvan, String> aciklamaColumn1;
    @FXML
    private TableView<Kullanici> KullaniciTableView;
    @FXML
    private TableColumn<Kullanici, String> kullaniciAdiColumn;
    @FXML
    private TableColumn<Kullanici, String> sifreColumn;
    @FXML
    private TableColumn<Kullanici, String> rolColumn;
    @FXML
    private Button BtnKullaniciyiSil;
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
    private Pane KullaniciEklePanel;
    @FXML
    private ComboBox<String> cbRol; // ComboBox
    @FXML
    private TextField txtKullaniciAdi, txtSifre, txtAdSoyad, txtTelefon, txtEmail, txtGorev; // TextField'lar
    @FXML
    private ComboBox<String> cbHayvanDurumu, cbHayvanTuru, cbTuyUzunluk, cbEnerjiSeviye, cbCinsiyet, cbSAhiplendirmeDurum, cbBulunmaDurumu;
    @FXML
    private Pane SahipsizHayvanPanel, KayipHayvanPanel;
    @FXML
    private RadioButton rbMiyavEvet, rbMiyavHayir, rbHavliyorEvet, rbHavliyorHayir;
    @FXML
    private RadioButton rbAsiliMiEvet;
    @FXML
    private RadioButton rbAsiliMiHayir;
    @FXML
    private RadioButton rbKisirMiEvet;
    @FXML
    private RadioButton rbKisirMiHayir;
    @FXML
    private RadioButton rbTasmaliMiEvet;
    @FXML
    private RadioButton rbTasmaliMiHayir;
    @FXML
    private  TextField txtTuyRengi, txtFavYemek;
    @FXML
    private TextField txtHayvanAdi;
    @FXML
    private TextField txtHayvanCinsi;
    @FXML
    private TextField txtHayvanYasi;
    @FXML
    private TextArea txtAciklama;
    @FXML
    private TextField txtSaglikDurumu;
    @FXML
    private TextField txtKaybolmaYeri;
    @FXML
    private TextField txtBulanKisiAd;
    @FXML
    private TextField txtBulanKisiTel;

    // ToggleGroup tanımları (RadioButton grupları)
    private ToggleGroup asiliMiGroup;
    private ToggleGroup kisirMiGroup;
    private ToggleGroup miyavGroup;
    private ToggleGroup havliyorGroup;
    private ToggleGroup tasmaliMiGroup;


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

        // Hayvan Ekle panelindeki liste için.
        adColumn2.setCellValueFactory(new PropertyValueFactory<>("ad"));
        turColumn2.setCellValueFactory(new PropertyValueFactory<>("tur"));
        cinsColumn2.setCellValueFactory(new PropertyValueFactory<>("cins"));
        yasColumn2.setCellValueFactory(new PropertyValueFactory<>("yas"));
        durumColumn2.setCellValueFactory(new PropertyValueFactory<>("durum"));
        asiliMiColumn1.setCellValueFactory(new PropertyValueFactory<>("asiliMi"));
        kisirMiColumn1.setCellValueFactory(new PropertyValueFactory<>("kisirMi"));
        aciklamaColumn1.setCellValueFactory(new PropertyValueFactory<>("aciklama"));

        // Veritabanından veri çekme
        try {
            ObservableList<Hayvan> hayvanListesi = DB_Connection.getHayvanlar(); // Veritabanı işlemi
            hayvanlarTableViewonHayvanEkle.setItems(hayvanListesi);
        } catch (SQLException e) {
            e.printStackTrace();
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
        });

        // KULLANİCİ LİSTESİ TABLEVİEW

        // Kolonları sınıflardaki değişkenlerle eşleştirme - Kullanıcı tablosu için
        kullaniciAdiColumn.setCellValueFactory(new PropertyValueFactory<>("kullaniciAdi"));
        sifreColumn.setCellValueFactory(new PropertyValueFactory<>("sifre"));
        rolColumn.setCellValueFactory(new PropertyValueFactory<>("rol"));

        ObservableList<Kullanici> kullaniciListesi = DB_Connection.getKullanicilar();
        System.out.println("Kullanıcı sayısı: " + kullaniciListesi.size());
        for (Kullanici kullanici : kullaniciListesi) {
            System.out.println("Kullanıcı Adı: " + kullanici.getKullaniciAdi() + ", Rol: " + kullanici.getRol());
        }
        if (kullaniciListesi.isEmpty()) {
            System.out.println("Kullanıcı listesi boş.");
        } else {
            System.out.println("Kullanıcı sayısı: " + kullaniciListesi.size());
            for (Kullanici kullanici : kullaniciListesi) {
                System.out.println("Kullanıcı Adı: " + kullanici.getKullaniciAdi() + ", Rol: " + kullanici.getRol());
            }
        }
        KullaniciTableView.setItems(kullaniciListesi);

        BtnKullaniciyiSil.setOnAction(event -> {
            silSelectedKullanici();
        });

        // HAYVAN EKLE OLAYLARI
        // Combobox'lara veri ekleme
        cbHayvanDurumu.getItems().addAll("Kayıp", "Sahipsiz");
        cbHayvanTuru.getItems().addAll("Kedi", "Köpek");

        // Hayvan Durumu combobox'unun değişim dinleyicisi
        cbHayvanDurumu.valueProperty().addListener((obs, oldVal, newVal) -> {
            if ("Kayıp".equals(newVal)) {
                KayipHayvanPanel.setVisible(true);
                SahipsizHayvanPanel.setVisible(false);
            } else if ("Sahipsiz".equals(newVal)) {
                KayipHayvanPanel.setVisible(false);
                SahipsizHayvanPanel.setVisible(true);
            }
        });
        // Hayvan Türü combobox'unun değişim dinleyicisi (disable durumlarını ayarlıyor)
        cbHayvanTuru.valueProperty().addListener((obs, oldVal, newVal) -> {
            if ("Kedi".equals(newVal)) {
                // Kedi seçilince aktifleşecek bileşenler
                cbTuyUzunluk.setDisable(false);
                rbMiyavEvet.setDisable(false);
                rbMiyavHayir.setDisable(false);
                txtTuyRengi.setDisable(false);
                txtFavYemek.setDisable(false);

                // Köpeğe özgü bileşenleri devre dışı bırak
                rbHavliyorEvet.setDisable(true);
                rbHavliyorHayir.setDisable(true);
                cbEnerjiSeviye.setDisable(true);
                cbCinsiyet.setDisable(true);
            } else if ("Köpek".equals(newVal)) {
                // Köpek seçilince aktifleşecek bileşenler
                rbHavliyorEvet.setDisable(false);
                rbHavliyorHayir.setDisable(false);
                cbEnerjiSeviye.setDisable(false);
                cbCinsiyet.setDisable(false);

                // Kediye özgü bileşenleri devre dışı bırak
                cbTuyUzunluk.setDisable(true);
                rbMiyavEvet.setDisable(true);
                rbMiyavHayir.setDisable(true);
                txtTuyRengi.setDisable(true);
                txtFavYemek.setDisable(true);
            }
        });
        // ComboBox değerleri ekleme
        cbSAhiplendirmeDurum.getItems().addAll("Sahiplendirilebilir", "Sahiplenmiş","Sahiplendirilemez");
        cbTuyUzunluk.getItems().addAll("Kısa", "Orta", "Uzun");
        cbCinsiyet.getItems().addAll("Dişi", "Erkek");
        cbBulunmaDurumu.getItems().addAll("Bulundu", "Kayboldu");
        cbEnerjiSeviye.getItems().addAll("Düşük", "Orta", "Yüksek");

        // ToggleGroup tanımlama ve RadioButton'ları gruplama
        asiliMiGroup = new ToggleGroup();
        rbAsiliMiEvet.setToggleGroup(asiliMiGroup);
        rbAsiliMiHayir.setToggleGroup(asiliMiGroup);

        kisirMiGroup = new ToggleGroup();
        rbKisirMiEvet.setToggleGroup(kisirMiGroup);
        rbKisirMiHayir.setToggleGroup(kisirMiGroup);

        miyavGroup = new ToggleGroup();
        rbMiyavEvet.setToggleGroup(miyavGroup);
        rbMiyavHayir.setToggleGroup(miyavGroup);

        havliyorGroup = new ToggleGroup();
        rbHavliyorEvet.setToggleGroup(havliyorGroup);
        rbHavliyorHayir.setToggleGroup(havliyorGroup);

        tasmaliMiGroup = new ToggleGroup();
        rbTasmaliMiEvet.setToggleGroup(tasmaliMiGroup);
        rbTasmaliMiHayir.setToggleGroup(tasmaliMiGroup);

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

    public void KullaniciEklePanel(ActionEvent event) {
        boolean isVisible = KullaniciEklePanel.isVisible();
        KullaniciEklePanel.setVisible(!isVisible);
        KullaniciEklePanel.setManaged(!isVisible);
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

    private void silSelectedKullanici() {
        // Tabloyu kontrol ederek seçili kullanıcıyı alın
        Kullanici selectedKullanici = KullaniciTableView.getSelectionModel().getSelectedItem();

        if (selectedKullanici != null) {
            // Kullanıcıdan silme işlemi için onay alın
            Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Kullanıcı Silme");
            confirmationAlert.setHeaderText("Bu kullanıcıyı silmek istediğinize emin misiniz?");
            confirmationAlert.setContentText("Silme işlemi geri alınamaz!");

            // Onay penceresini göster ve kullanıcıdan yanıt al
            ButtonType result = confirmationAlert.showAndWait().orElse(ButtonType.CANCEL);

            if (result == ButtonType.OK) {
                // Kullanıcı onayladıysa, silme işlemini gerçekleştir
                silKullanici(selectedKullanici);

                // Başarı mesajını göster
                showAlert(AlertType.INFORMATION, "Başarılı", "Kullanıcı başarıyla silindi.");
            }
        } else {
            // Eğer hiç kullanıcı seçilmemişse, bilgilendirme mesajı göster
            showAlert(AlertType.WARNING, "Uyarı", "Silinecek bir kullanıcı seçmediniz.");
        }
    }
    private void silKullanici(Kullanici kullanici) {
        // Kullanıcıyı veritabanından silme işlemi
        try {
            DB_Connection.silKullanici(kullanici);
            // Silinen kullanıcıyı tablodan da kaldır
            KullaniciTableView.getItems().remove(kullanici);
        } catch (Exception e) {
            // Hata mesajı göster
            showAlert(AlertType.ERROR, "Hata", "Kullanıcı silinirken bir hata oluştu.");
        }
    }

    @FXML
    public void handleHayvanSil(ActionEvent event) {
        // Seçili hayvanı al
        Hayvan seciliHayvan = hayvanlarTableViewonHayvanEkle.getSelectionModel().getSelectedItem();

        if (seciliHayvan == null) {
            // Hiçbir hayvan seçilmediyse uyarı göster
            Alert alert = new Alert(Alert.AlertType.WARNING, "Lütfen silmek için bir hayvan seçin.", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        // Silme işlemi için onay iste
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Bu hayvanı silmek istediğinizden emin misiniz? (" + seciliHayvan.getAd() + ")",
                ButtonType.YES, ButtonType.NO);
        ButtonType result = confirmAlert.showAndWait().orElse(ButtonType.NO);

        if (result == ButtonType.YES) {
            try {
                // Veritabanından silme işlemi
                DB_Connection.silHayvan(seciliHayvan);

                // TableView'den silme işlemi
                ObservableList<Hayvan> mevcutHayvanlar = hayvanlarTableViewonHayvanEkle.getItems();
                mevcutHayvanlar.remove(seciliHayvan);

                // Başarı mesajı göster
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Hayvan başarıyla silindi!", ButtonType.OK);
                successAlert.showAndWait();
            } catch (SQLException e) {
                e.printStackTrace();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Hayvan silinirken bir hata oluştu: " + e.getMessage(), ButtonType.OK);
                errorAlert.showAndWait();
            }
        }
    }
    private boolean validateHayvanInputs() {
        if (txtHayvanAdi.getText().isEmpty() || txtHayvanCinsi.getText().isEmpty()) {
            showAlert( Alert.AlertType.ERROR, "Hata","Tüm zorunlu alanları doldurunuz.");
            return false;
        }
        try {
            Integer.parseInt(txtHayvanYasi.getText());
        } catch (NumberFormatException e) {
            showAlert( Alert.AlertType.ERROR,"Hata", "Yaş alanına geçerli bir sayı giriniz.");
            return false;
        }
        return true;
    }



}
