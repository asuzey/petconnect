public class SahiplendirmeBasvuru extends Kullanici {
    private int basvuruID;
    private int hayvanID;
    private int kullaniciID;
    private String adres;
    private String aciklama;
    private String basvuruDurumu;
    private String basvuruTarihi;

    // Constructor, Getter ve Setter'lar
    public SahiplendirmeBasvuru(int kullaniciID, String kullaniciAdi, String sifre, String rol,
                                int basvuruID, int hayvanID, String adres, String aciklama,
                                String basvuruDurumu, String basvuruTarihi) {
        super(kullaniciID, kullaniciAdi, sifre, rol); // Ata sınıfın constructor'ını çağır (super ile)
        this.basvuruID = basvuruID;
        this.hayvanID = hayvanID;
        this.adres = adres;
        this.aciklama = aciklama;
        this.basvuruDurumu = basvuruDurumu;
        this.basvuruTarihi = basvuruTarihi;
    }


    public int getBasvuruID() {
        return basvuruID;
    }

    public void setBasvuruID(int basvuruID) {
        this.basvuruID = basvuruID;
    }

    public int getHayvanID() {
        return hayvanID;
    }

    public void setHayvanID(int hayvanID) {
        this.hayvanID = hayvanID;
    }

    public int getKullaniciID() {
        return kullaniciID;
    }

    public void setKullaniciID(int kullaniciID) {
        this.kullaniciID = kullaniciID;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public String getBasvuruDurumu() {
        return basvuruDurumu;
    }

    public void setBasvuruDurumu(String basvuruDurumu) {
        this.basvuruDurumu = basvuruDurumu;
    }

    public String getBasvuruTarihi() {
        return basvuruTarihi;
    }

    public void setBasvuruTarihi(String basvuruTarihi) {
        this.basvuruTarihi = basvuruTarihi;
    }
}
