public class Calisan extends Kullanici {
    private int kullaniciID;
    private String adSoyad;
    private String telefon;
    private String email;
    private String gorev;

    // Constructor, Getter ve Setter'lar
    public Calisan(int kullaniciID, String kullaniciAdi, String sifre, String rol,
                   String adSoyad, String telefon, String email, String gorev) {
        super(kullaniciID, kullaniciAdi, sifre, rol);
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.email = email;
        this.gorev = gorev;
    }

    // İkinci yapıcı metod (düzeltilmiş)
    public Calisan(int kullaniciID, String adSoyad, String telefon, String email, String gorev) {
        super(kullaniciID, "", "", ""); // Üst sınıfın yapıcı metodu çağrılıyor
        this.kullaniciID = kullaniciID;
        this.adSoyad = adSoyad;
        this.telefon = telefon;
        this.email = email;
        this.gorev = gorev;
    }

    public String getAdSoyad() {
        return adSoyad;
    }

    public void setAdSoyad(String adSoyad) {
        this.adSoyad = adSoyad;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }
}

