import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.SQLException;

public class Controller {

    @FXML
    public void KullaniciGirisi(ActionEvent event){ //petconnect.fxml ve kullanici_ekrani.fxml sayfalarının controllerı.
        // System.out.println("Kullanici Girisi Butonuna Tıklandı!"); deneme yapmistim
        try {
            // Kullanıcı giriş ekranını yükle
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/kullanici_giris.fxml"));
            Parent root = loader.load();

            // Yeni bir sahne oluştur
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.sizeToScene(); // İçeriğe göre boyutlandırır
            stage.centerOnScreen(); // Ekranın merkezine konumlandırır
            stage.setResizable(false); // Kullanıcının pencereyi yeniden boyutlandırmasını engeller
            stage.setFullScreen(false); // Tam ekran modunu devre dışı bırakır
            stage.setTitle("PetConnect");
            stage.show();

            // Mevcut pencereyi kapat
            // Event'in gerçekleştiği sahneyi alın
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.hide();
        } catch (IOException e) {
            e.printStackTrace(); // Hata ayıklama için hata bilgilerini yazdır
        }
    }

    public void handleButtonClick(ActionEvent event) {
        try {
            // Yeni sahneyi yükle
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hayvansever_giris.fxml"));

            Parent root = loader.load();

            // Sahne ve Stage ayarları
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            // Sahneyi yeniden boyutlandırma
            stage.sizeToScene(); // İçeriğe göre boyutlandırır
            stage.centerOnScreen(); // Ekranın merkezine konumlandırır
            stage.setResizable(false); // Kullanıcının pencereyi yeniden boyutlandırmasını engeller
            stage.setFullScreen(false); // Tam ekran modunu devre dışı bırakır
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
