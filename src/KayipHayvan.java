public class KayipHayvan extends Hayvan {
    private String kaybolmaTarihi;
    private String kaybolmaYeri;
    private String bulanKisiAdi;
    private String bulanKisiTelefon;
    private String bulunmaDurumu;
    private String bulunmaTarihi;
    private boolean tasmaliMi;

    // Constructor, Getter ve Setter'lar
    public KayipHayvan(int hayvanID, String ad, String tur, String cins, int yas, String durum,
                       boolean asiliMi, boolean kisirMi, String fotografYolu, String aciklama,
                       String kaybolmaTarihi, String kaybolmaYeri,
                       String bulanKisiAdi, String bulanKisiTelefon, String bulunmaDurumu,
                       String bulunmaTarihi, boolean tasmaliMi) {
        super(hayvanID, ad, tur, cins, yas, durum, asiliMi, kisirMi, fotografYolu, aciklama);
        this.kaybolmaTarihi = kaybolmaTarihi;
        this.kaybolmaYeri = kaybolmaYeri;
        this.bulanKisiAdi = bulanKisiAdi;
        this.bulanKisiTelefon = bulanKisiTelefon;
        this.bulunmaDurumu = bulunmaDurumu;
        this.bulunmaTarihi = bulunmaTarihi;
        this.tasmaliMi = tasmaliMi;
    }

    public String getKaybolmaTarihi() {
        return kaybolmaTarihi;
    }

    public void setKaybolmaTarihi(String kaybolmaTarihi) {
        this.kaybolmaTarihi = kaybolmaTarihi;
    }

    public String getKaybolmaYeri() {
        return kaybolmaYeri;
    }

    public void setKaybolmaYeri(String kaybolmaYeri) {
        this.kaybolmaYeri = kaybolmaYeri;
    }

    public String getBulanKisiAdi() {
        return bulanKisiAdi;
    }

    public void setBulanKisiAdi(String bulanKisiAdi) {
        this.bulanKisiAdi = bulanKisiAdi;
    }

    public String getBulanKisiTelefon() {
        return bulanKisiTelefon;
    }

    public void setBulanKisiTelefon(String bulanKisiTelefon) {
        this.bulanKisiTelefon = bulanKisiTelefon;
    }

    public String getBulunmaDurumu() {
        return bulunmaDurumu;
    }

    public void setBulunmaDurumu(String bulunmaDurumu) {
        this.bulunmaDurumu = bulunmaDurumu;
    }

    public String getBulunmaTarihi() {
        return bulunmaTarihi;
    }

    public void setBulunmaTarihi(String bulunmaTarihi) {
        this.bulunmaTarihi = bulunmaTarihi;
    }

    public boolean isTasmaliMi() {
        return tasmaliMi;
    }

    public void setTasmaliMi(boolean tasmaliMi) {
        this.tasmaliMi = tasmaliMi;
    }
}

