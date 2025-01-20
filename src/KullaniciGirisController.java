//package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class KullaniciGirisController { // kullanici_giris.fxml sayfasının controllerı

    @FXML
    private TextField kullaniciAdiField;

    @FXML
    private PasswordField sifreField;

    @FXML
    private Button girisButton;

    private DB_Connection dbConnection = new DB_Connection();


    @FXML
    public void initialize() {
        girisButton.setOnAction(event -> girisYap());
    }

    private void girisYap() {
        String kullaniciAdi = kullaniciAdiField.getText();
        String sifre = sifreField.getText();

        if (kullaniciAdi.isEmpty() || sifre.isEmpty()) {
            showAlert("Hata", "Lütfen tüm alanları doldurun.", Alert.AlertType.ERROR);
            return;
        }

        try (Connection con = dbConnection.getConnection()) {
            String sql = "SELECT rol FROM kullanici WHERE KullaniciAdi = ? AND Sifre = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, sifre);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String rol = resultSet.getString("rol");
                if (rol.equals("admin")) {
                    showAlert("Başarılı", "Giriş başarılı! Admin olarak giriş yaptınız.", Alert.AlertType.INFORMATION);
                    // Yeni sayfaya geçiş
                    kullaniciEkraninaGecis();
                }
                else if(rol.equals("calisan")){
                    showAlert("Başarılı", "Giriş başarılı! Çalışan olarak giriş yaptınız.", Alert.AlertType.INFORMATION);

                    kullaniciEkraninaGecis();
                }else {
                    showAlert("Hata", "Bu kullanıcı giriş yetkisine sahip değil.", Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Hata", "Geçersiz kullanıcı adı veya şifre.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hata", "Veritabanı bağlantısında bir hata oluştu.", Alert.AlertType.ERROR);
        }
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

    private void showAlert(String baslik, String mesaj, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(baslik);
        alert.setHeaderText(null);
        alert.setContentText(mesaj);
        alert.showAndWait();
    }

    @FXML
    private void kullaniciEkraninaGecis() {
        try {
            // FXML dosyasını yükleyin
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("kullanici_ekrani.fxml"));
            javafx.scene.Parent root = loader.load();

            // Mevcut sahneyi değiştirin
            Stage stage = (Stage) girisButton.getScene().getWindow();
            stage.getScene().setRoot(root);
            // Sahneyi yeniden boyutlandırma
            stage.sizeToScene(); // İçeriğe göre boyutlandırır
            stage.centerOnScreen(); // Ekranın merkezine konumlandırır
            stage.setResizable(false); // Kullanıcının pencereyi yeniden boyutlandırmasını engeller
            stage.setFullScreen(false); // Tam ekran modunu devre dışı bırakır
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Hata", "Yeni ekran yüklenirken bir hata oluştu.", Alert.AlertType.ERROR);
        }
    }
}

