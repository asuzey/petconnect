
public class Hayvan {
    private int hayvanID;
    private String ad;
    private String tur; // 'Kedi', 'Kopek', 'Diğer'
    private String cins;
    private int yas;
    private String durum; // 'sahipsiz', 'sahipli', 'tedavi_altinda', 'kayıp'
    private boolean asiliMi;
    private boolean kisirMi;
    private String fotografYolu;
    private String aciklama;

    // Constructor
    public Hayvan(int hayvanID, String ad, String tur, String cins, int yas, String durum, boolean asiliMi, boolean kisirMi, String fotografYolu, String aciklama) {
        this.hayvanID = hayvanID;
        this.ad = ad;
        this.tur = tur;
        this.cins = cins;
        this.yas = yas;
        this.durum = durum;
        this.asiliMi = asiliMi;
        this.kisirMi = kisirMi;
        this.fotografYolu = fotografYolu;
        this.aciklama = aciklama;
    }

    // Default Constructor
    public Hayvan() {}

    // Getters and Setters
    public int getHayvanID() {
        return hayvanID;
    }

    public void setHayvanID(int hayvanID) {
        this.hayvanID = hayvanID;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getCins() {
        return cins;
    }

    public void setCins(String cins) {
        this.cins = cins;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public String getDurum() {
        return durum;
    }

    public void setDurum(String durum) {
        this.durum = durum;
    }

    public boolean isAsiliMi() {
        return asiliMi;
    }

    public void setAsiliMi(boolean asiliMi) {
        this.asiliMi = asiliMi;
    }

    public boolean isKisirMi() {
        return kisirMi;
    }

    public void setKisirMi(boolean kisirMi) {
        this.kisirMi = kisirMi;
    }

    public String getFotografYolu() {
        return fotografYolu;
    }

    public void setFotografYolu(String fotografYolu) {
        this.fotografYolu = fotografYolu;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    @Override
    public String toString() {
        return "Hayvan{" +
                "hayvanID=" + hayvanID +
                ", ad='" + ad + '\'' +
                ", tur='" + tur + '\'' +
                ", cins='" + cins + '\'' +
                ", yas=" + yas +
                ", durum='" + durum + '\'' +
                ", asiliMi=" + asiliMi +
                ", kisirMi=" + kisirMi +
                ", fotografYolu='" + fotografYolu + '\'' +
                ", aciklama='" + aciklama + '\'' +
                '}';
    }
}
