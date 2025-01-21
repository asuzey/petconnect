public class Kullanici {
    private int kullaniciID;
    private String kullaniciAdi;
    private String sifre;
    private String rol;

    // Parametresiz yapıcı metod
    public Kullanici() {
        // Varsayılan değerler atanabilir
        this.kullaniciID = 0;
        this.kullaniciAdi = "";
        this.sifre = "";
        this.rol = "";
    }

    // Constructor, Getter ve Setter'lar
    public Kullanici(int kullaniciID, String kullaniciAdi, String sifre, String rol) {
        this.kullaniciID = kullaniciID;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.rol = rol;
    }

    // Alternatif yapıcı
    public Kullanici(String kullaniciAdi, String sifre, String rol) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.rol = rol;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}

