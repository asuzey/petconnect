import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


import java.io.IOException;

public class Main extends Application {
    @Override

    public void start(Stage primaryStage) throws IOException {
        // FXML dosyasını yükle
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/petconnect.fxml"));
        Parent root = loader.load();


        // Sahneyi ve aşamayı ayarla
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Pet Connect");
        primaryStage.setResizable(false);
        
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}