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

                hayvanListesi.add(new Hayvan(0, ad, tur, cins, yas, durum, asiliMi, kisirMi,"", aciklama));
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
                        rs.getString("fotografYolu"),
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
        ObservableList<Kullanici> kullaniciListesi = FXCollections.observableArrayList();

        String query = "SELECT k.KullaniciAdi, k.Rol, c.AdSoyad, c.Telefon, c.Email, c.Gorev " +
                "FROM Kullanici k " +
                "LEFT JOIN Calisan c ON k.KullaniciID = c.KullaniciID";

        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String kullaniciAdi = rs.getString("kullaniciAdi");
                String rol = rs.getString("rol");
                String adSoyad = rs.getString("adSoyad");
                String telefon = rs.getString("telefon");
                String email = rs.getString("email");
                String gorev = rs.getString("gorev");

                if (gorev != null) {
                    // Çalışan sınıfı
                    kullaniciListesi.add(new Calisan(kullaniciAdi, rol, adSoyad, telefon, email, gorev));
                } else {
                    // Kullanıcı sınıfı
                    kullaniciListesi.add(new Kullanici(kullaniciAdi, rol));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kullaniciListesi;
    }
}

