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

    public static int kullaniciEkle(Kullanici kullanici) throws SQLException { // voidden int' dönüştürüldü
        Connection conn = new DB_Connection().getConnection(); // Veritabanı bağlantısını al
        String query = "INSERT INTO kullanici (kullaniciAdi, sifre, rol) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, kullanici.getKullaniciAdi());
        pstmt.setString(2, kullanici.getSifre());
        pstmt.setString(3, kullanici.getRol());
        pstmt.executeUpdate();

        // Kullanıcı ID'sini al
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next()) {
            return rs.getInt(1); // Kullanıcı ID'sini döndür
        } else {
            throw new SQLException("Kullanıcı eklenirken ID oluşturulamadı.");
        }
    }

    public static void calisanEkle(Calisan calisan) throws SQLException {
        Connection conn = new DB_Connection().getConnection(); // Veritabanı bağlantısını al

        try {
            conn.setAutoCommit(false); // İşlemleri bir bütün olarak ele al

            // 1. Adım: Kullanıcı ekle
            int kullaniciID = kullaniciEkle(calisan); // Kullanıcı ekle ve ID al

        String query = "INSERT INTO calisan (kullaniciID, adSoyad, telefon, email, gorev) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, calisan.getKullaniciID());
        pstmt.setString(2, calisan.getAdSoyad());
        pstmt.setString(3, calisan.getTelefon());
        pstmt.setString(4, calisan.getEmail());
        pstmt.setString(5, calisan.getGorev());
        pstmt.executeUpdate();

        conn.commit();
        } catch (SQLException e) {
            conn.rollback(); // Hata durumunda işlemleri geri al
            throw e;
        } finally {
            conn.setAutoCommit(true); // Auto-commit modunu eski haline getir
        }
    }
}

