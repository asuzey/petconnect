import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

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

                hayvanListesi.add(new Hayvan(0, ad, tur, cins, yas, durum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hayvanListesi;
        }
    public static ObservableList<Hayvan> getHayvanlarWithoutAciklama() throws SQLException{
        ObservableList<Hayvan> hayvanListesiWaciklama = FXCollections.observableArrayList();

        String query = "SELECT ad, tur, cins, yas, durum FROM Hayvan";
        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String ad = rs.getString("ad");
                String tur = rs.getString("tur");
                String cins = rs.getString("cins");
                int yas = rs.getInt("yas");
                String durum = rs.getString("durum");

                hayvanListesiWaciklama.add(new Hayvan(0, ad, tur, cins, yas, durum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hayvanListesiWaciklama;
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
                        rs.getString("durum")
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
        String sql = "DELETE FROM kullanici WHERE kullaniciID = ?";

        try (Connection connection = new DB_Connection().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, kullanici.getKullaniciID());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Başarılı ise true döndür
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
    }
    public static boolean silHayvan(Hayvan hayvan) throws SQLException {

        String sql = "DELETE FROM hayvan WHERE ad = ? AND tur = ?"; // Örnek: "ad" ve "tur" kullanılarak silme

        try (Connection connection = new DB_Connection().getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            // SQL sorgusu için parametreleri ayarla
            pstmt.setString(1, hayvan.getAd());
            pstmt.setString(2, hayvan.getTur());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Başarılı ise true döndür
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Hata durumunda false döndür
        }
    }
    @FXML
    public static void saveToDatabase(Object hayvan) {
        System.out.println("Buraya düştü! saveToDB");
        PreparedStatement statement = null;
        ResultSet generatedKeys = null;
        Connection connection = null;

        try {
            // Veritabanı bağlantısını oluştur
            connection = new DB_Connection().getConnection();
            connection.setAutoCommit(false); // Transaction başlat

            // Hayvan nesnesinin türünü kontrol et
            if (hayvan instanceof Hayvan) { // instanceof belirli bir sınıfın içine değer aramak için kullanıalbilr kısaca
                Hayvan hayvanObj = (Hayvan) hayvan; // Hayvan türüne dönüştür

                String sqlHayvan = "INSERT INTO Hayvan (ad, cins, yas, durum, asiliMi, kisirMi, aciklama) VALUES (?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sqlHayvan, Statement.RETURN_GENERATED_KEYS);

                // Hayvan nesnesinin özelliklerini ayarlayın
                statement.setString(1, hayvanObj.getAd());
                statement.setString(2, hayvanObj.getCins());
                statement.setInt(3, hayvanObj.getYas());
                statement.setString(4, hayvanObj.getDurum());
                statement.setBoolean(5, hayvanObj.isAsiliMi());
                statement.setBoolean(6, hayvanObj.isKisirMi());
                statement.setString(7, hayvanObj.getAciklama());

                statement.executeUpdate();
                generatedKeys = statement.getGeneratedKeys();
                int hayvanID = 0;
                if (generatedKeys.next()) {
                    hayvanID = generatedKeys.getInt(1); // Yeni eklenen hayvanın ID'sini al
                }

                // 2. SahipsizHayvan tablosuna ekleme
                if (hayvan instanceof SahipsizHayvan) {
                    SahipsizHayvan sahipsizHayvan = (SahipsizHayvan) hayvan;
                    String sqlSahipsizHayvan = "INSERT INTO SahipsizHayvan (hayvanID, saglikDurumu, sahiplendirmeDurumu, barinaktaBulunmaSuresi) VALUES (?, ?, ?, ?)";
                    statement = connection.prepareStatement(sqlSahipsizHayvan, Statement.RETURN_GENERATED_KEYS);

                    // SahipsizHayvan nesnesinin özelliklerini ayarlayın
                    statement.setInt(1, hayvanID); // HayvanID'yi ayarlayın
                    statement.setString(2, sahipsizHayvan.getSaglikDurumu());
                    statement.setString(3, sahipsizHayvan.getSahiplendirmeDurumu());
                    statement.setInt(4, sahipsizHayvan.getBarinaktaBulunmaSuresi());

                    statement.executeUpdate();
                    generatedKeys = statement.getGeneratedKeys();
                    int sahipsizHayvanID = 0;
                    if (generatedKeys.next()) {
                        sahipsizHayvanID = generatedKeys.getInt(1); // Yeni eklenen sahipsiz hayvanın ID'sini al
                    }

                    // 3. SahipsizKedi tablosuna ekleme
                    if (hayvan instanceof SahipsizKedi) {
                        SahipsizKedi kedi = (SahipsizKedi) hayvan;
                        String sqlSahipsizKedi = "INSERT INTO SahipsizKedi (sahipsizHayvanID, miyavlıyor_mu, tüy_uzunlugu) VALUES (?, ?, ?)";
                        statement = connection.prepareStatement(sqlSahipsizKedi);
                        statement.setInt(1, sahipsizHayvanID); // SahipsizHayvanID'yi ayarlayın
                        statement.setBoolean(2, kedi.isMiyavlıyorMu());
                        statement.setString(3, kedi.getTuyUzunlugu());
                        statement.executeUpdate();
                    }
                    // 4. SahipsizKopek tablosuna ekleme
                    else if (hayvan instanceof SahipsizKopek) {
                        SahipsizKopek kopek = (SahipsizKopek) hayvan;
                        String sqlSahipsizKopek = "INSERT INTO SahipsizKopek (sahipsizHayvanID, havliyor_mu, cinsiyet) VALUES (?, ?, ?)";
                        statement = connection.prepareStatement(sqlSahipsizKopek);
                        statement.setInt(1, sahipsizHayvanID); // SahipsizHayvanID'yi ayarlayın
                        statement.setBoolean(2, kopek.isHavliyorMu());
                        statement.setString(3, kopek.getCinsiyet());
                        statement.executeUpdate();
                    }
                }
                // 5. KayipHayvan tablosuna ekleme
                else if (hayvan instanceof KayipHayvan) {
                    KayipHayvan kayipHayvan = (KayipHayvan) hayvan;
                    String sqlKayipHayvan = "INSERT INTO KayipHayvan (hayvanID, kaybolmaTarihi, kaybolmaYeri, bulanKisiAdi, bulanKisiTelefon, bulunmaDurumu, bulunmaTarihi, tasmaliMi) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                    statement = connection.prepareStatement(sqlKayipHayvan, Statement.RETURN_GENERATED_KEYS);

                    // KayipHayvan nesnesinin özelliklerini ayarlayın
                    statement.setInt(1, hayvanID); // HayvanID'yi ayarlayın
                    statement.setString(2, kayipHayvan.getKaybolmaTarihi());
                    statement.setString(3, kayipHayvan.getKaybolmaYeri());
                    statement.setString(4, kayipHayvan.getBulanKisiAdi());
                    statement.setString(5, kayipHayvan.getBulanKisiTelefon());
                    statement.setString(6, kayipHayvan.getBulunmaDurumu());
                    statement.setString(7, kayipHayvan.getBulunmaTarihi());
                    statement.setBoolean(8, kayipHayvan.isTasmaliMi());

                    statement.executeUpdate();
                    generatedKeys = statement.getGeneratedKeys();
                    int kayipHayvanID = 0;
                    if (generatedKeys.next()) {
                        kayipHayvanID = generatedKeys.getInt(1); // Yeni eklenen kayıp hayvanın ID'sini al
                    }

                    // 6. KayipKedi tablosuna ekleme
                    if (hayvan instanceof KayipKedi) {
                        KayipKedi kayipKedi = (KayipKedi) hayvan;
                        String sqlKayipKedi = "INSERT INTO KayipKedi (kayipHayvanID, tuyRengi, favoriYemek) VALUES (?, ?, ?)";
                        statement = connection.prepareStatement(sqlKayipKedi);
                        statement.setInt(1, kayipHayvanID); // KayipHayvanID'yi ayarlayın
                        statement.setString(2, kayipKedi.getTuyRengi());
                        statement.setString(3, kayipKedi.getFavoriYemek());
                        statement.executeUpdate();
                    }
                    // 7. KayipKopek tablosuna ekleme
                    else if (hayvan instanceof KayipKopek) {
                        KayipKopek kayipKopek = (KayipKopek) hayvan;
                        String sqlKayipKopek = "INSERT INTO KayipKopek (kayipHayvanID, enerjiSeviyesi) VALUES (?, ?)";
                        statement = connection.prepareStatement(sqlKayipKopek);
                        statement.setInt(1, kayipHayvanID); // KayipHayvanID'yi ayarlayın
                        statement.setString(2, kayipKopek.getEnerjiSeviyesi());
                        statement.executeUpdate();
                    }
                }


                // Transaction'ı onayla
                int affectedRows = statement.executeUpdate();
                connection.commit();
                System.out.println("Hayvan veritabanına başarıyla eklendi.");
                // Konsola yazdırma
                System.out.println("Etkilenen satır sayısı: " + affectedRows);

            }
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback(); // Hata durumunda geri al
                } catch (SQLException ex) {
                    System.err.println("Rollback işlemi sırasında hata: " + ex.getMessage());
                }
            }
            System.err.println("Veritabanı hatası: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Kaynak kapatma hatası: " + e.getMessage());
            }
        }
    }
    public static void sahiplendirmeBasvuruEkle(SahiplendirmeBasvuru basvuru) throws SQLException {
        String query = "INSERT INTO SahiplendirmeBasvuru (HayvanID, KullaniciID, AdSoyad, Telefon, Email, Adres, Aciklama, BasvuruDurumu) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new DB_Connection().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, basvuru.getHayvanID());
            stmt.setInt(2, basvuru.getKullaniciID());
            stmt.setString(3, basvuru.getAdSoyad());
            stmt.setString(4, basvuru.getTelefon());
            stmt.setString(5, basvuru.getEmail());
            stmt.setString(6, basvuru.getAdres());
            stmt.setString(7, basvuru.getAciklama());
            stmt.setString(8, basvuru.getBasvuruDurumu());

            stmt.executeUpdate();
        }
    }
}

