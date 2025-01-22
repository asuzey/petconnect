public class KayipKopek extends KayipHayvan{
    private String enerjiSeviyesi;

    public KayipKopek(int hayvanID, String ad, String tur, String cins, int yas, String durum, boolean asiliMi, boolean kisirMi, String aciklama,
                      String kaybolmaTarihi, String kaybolmaYeri, String bulanKisiAdi, String bulanKisiTelefon, String bulunmaDurumu, String bulunmaTarihi, boolean tasmaliMi,
                      String enerjiSeviyesi) {
        super(hayvanID, ad, tur, cins, yas, durum, asiliMi, kisirMi, aciklama, kaybolmaTarihi, kaybolmaYeri, bulanKisiAdi, bulanKisiTelefon, bulunmaDurumu, bulunmaTarihi, tasmaliMi);
        this.enerjiSeviyesi = enerjiSeviyesi;
    }

    public String getEnerjiSeviyesi() {
        return enerjiSeviyesi;
    }

    public void setEnerjiSeviyesi(String enerjiSeviyesi) {
        this.enerjiSeviyesi = enerjiSeviyesi;
    }
}
