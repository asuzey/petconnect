public class Tedavi {
    private int tedaviID;
    private int hayvanID;
    private String tedaviTuru;
    private String tedaviTarihi;
    private String tedaviDetaylari;
    private String asiBilgisi;
    private String asiTarihi;

    // Constructor, Getter ve Setter'lar
    public Tedavi(int tedaviID, int hayvanID, String tedaviTuru, String tedaviTarihi,
                  String tedaviDetaylari, String asiBilgisi, String asiTarihi) {
        this.tedaviID = tedaviID;
        this.hayvanID = hayvanID;
        this.tedaviTuru = tedaviTuru;
        this.tedaviTarihi = tedaviTarihi;
        this.tedaviDetaylari = tedaviDetaylari;
        this.asiBilgisi = asiBilgisi;
        this.asiTarihi = asiTarihi;
    }

    public int getTedaviID() {
        return tedaviID;
    }

    public void setTedaviID(int tedaviID) {
        this.tedaviID = tedaviID;
    }

    public int getHayvanID() {
        return hayvanID;
    }

    public void setHayvanID(int hayvanID) {
        this.hayvanID = hayvanID;
    }

    public String getTedaviTuru() {
        return tedaviTuru;
    }

    public void setTedaviTuru(String tedaviTuru) {
        this.tedaviTuru = tedaviTuru;
    }

    public String getTedaviTarihi() {
        return tedaviTarihi;
    }

    public void setTedaviTarihi(String tedaviTarihi) {
        this.tedaviTarihi = tedaviTarihi;
    }

    public String getTedaviDetaylari() {
        return tedaviDetaylari;
    }

    public void setTedaviDetaylari(String tedaviDetaylari) {
        this.tedaviDetaylari = tedaviDetaylari;
    }

    public String getAsiBilgisi() {
        return asiBilgisi;
    }

    public void setAsiBilgisi(String asiBilgisi) {
        this.asiBilgisi = asiBilgisi;
    }

    public String getAsiTarihi() {
        return asiTarihi;
    }

    public void setAsiTarihi(String asiTarihi) {
        this.asiTarihi = asiTarihi;
    }
}
