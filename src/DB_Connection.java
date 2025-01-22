import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class DB_Connection {
    public Connection con;

    public Connection getConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/HayvanBarinagi","root","1234");
            System.out.println("Conncted successfully");
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return con;
    }

    public static ObservableList<Hayvan> getHayvanlar() throws SQLException{
        ObservableList<Hayvan> hayvanListesi = FXCollections.observableArrayList();

        String query = "SELECT ad, tur, cins, yas, durum, asiliMi, kisirMi, aciklama FROM Hayvan";
        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String ad = rs.getString("ad");
                String tur = rs.getString("tur");
                String cins = rs.getString("cins");
                int yas = rs.getInt("yas");
                String durum = rs.getString("durum");
                boolean asiliMi = rs.getBoolean("asiliMi");
                boolean kisirMi = rs.getBoolean("kisirMi");
                String aciklama = rs.getString("aciklama");

                hayvanListesi.add(new Hayvan(0, ad, tur, cins, yas, durum, asiliMi, kisirMi, aciklama));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hayvanListesi;
        }
    public static ObservableList<Hayvan> getFilteredHayvanlar(String query) throws SQLException {
        ObservableList<Hayvan> hayvanListesi = FXCollections.observableArrayList();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = new DB_Connection().getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                Hayvan hayvan = new Hayvan(
                        rs.getInt("hayvanID"),
                        rs.getString("ad"),
                        rs.getString("tur"),
                        rs.getString("cins"),
                        rs.getInt("yas"),
                        rs.getString("durum"),
                        rs.getBoolean("asiliMi"),
                        rs.getBoolean("kisirMi"),
                        rs.getString("aciklama")
                );
                hayvanListesi.add(hayvan);
            }
        } finally {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        }

        return hayvanListesi;
    }

    public static int kullaniciEkleVeIDAl(Kullanici kullanici) throws SQLException {
        String query = "INSERT INTO kullanici (KullaniciAdi, Sifre, Rol) VALUES (?, ?, ?)";

        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, kullanici.getKullaniciAdi());
            stmt.setString(2, kullanici.getSifre());
            stmt.setString(3, kullanici.getRol());
            stmt.executeUpdate();

            // Oluşan KullaniciID'yi al
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Kullanıcı eklenirken ID alınamadı.");
            }
        }
    }


    public static void calisanEkle(Calisan calisan) throws SQLException {
        String query = "INSERT INTO calisan (KullaniciID, AdSoyad, Telefon, Email, Gorev) VALUES (?, ?, ?, ?, ?)"; // null dönebilir yaptık admin eklenebilmesi için
        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, calisan.getKullaniciID());
            stmt.setString(2, calisan.getAdSoyad());
            stmt.setString(3, calisan.getTelefon());
            stmt.setString(4, calisan.getEmail());
            stmt.setString(5, calisan.getGorev());
            stmt.executeUpdate();
        }
    }
    public static ObservableList<Kullanici> getKullanicilar() {
        ObservableList<Kullanici> kullanicilar = FXCollections.observableArrayList();

        try (Connection connection = new DB_Connection().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT kullaniciAdi, sifre, rol FROM kullanici")) {

            System.out.println("Veritabanından gelen kullanıcılar:");
            while (resultSet.next()) {
                String kullaniciAdi = resultSet.getString("kullaniciAdi");
                String sifre = resultSet.getString("sifre");
                String rol = resultSet.getString("rol");
                System.out.println("Kullanıcı Adı: " + kullaniciAdi + ", Rol: " + rol);

                Kullanici kullanici = new Kullanici(kullaniciAdi, sifre, rol);
                kullanicilar.add(kullanici);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kullanicilar;
    }

    public static boolean silKullanici(Kullanici kullanici) {
        String sql = "DELETE FROM kullanici WHERE kullaniciAdi = ?";

        try (Connection connection = new DB_Connection().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, kullanici.getKullaniciAdi());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Başarılı ise true döndür
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
    }
    public static void silHayvan(Hayvan hayvan) throws SQLException {
        String sql = "DELETE FROM hayvan WHERE hayvanId"; // Örnek: "ad" ve "tur" kullanılarak silme

        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // SQL sorgusu için parametreleri ayarla
            pstmt.setString(1, hayvan.getAd());
            pstmt.setString(2, hayvan.getTur());

            // Sorguyu çalıştır
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Hayvan silinirken bir hata oluştu. Hayvan bulunamadı.");
            }
        }
    }
}

