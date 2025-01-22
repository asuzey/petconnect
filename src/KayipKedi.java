public class KayipKedi extends KayipHayvan{
    private String tuyRengi;
    private String favoriYemek;

    public KayipKedi(int hayvanID, String ad, String tur, String cins, int yas, String durum, boolean asiliMi, boolean kisirMi, String aciklama,
                     String kaybolmaTarihi, String kaybolmaYeri, String bulanKisiAdi, String bulanKisiTelefon, String bulunmaDurumu, String bulunmaTarihi, boolean tasmaliMi,
                     String tuyRengi, String favoriYemek) {
        super(hayvanID, ad, tur, cins, yas, durum, asiliMi, kisirMi, aciklama, kaybolmaTarihi, kaybolmaYeri, bulanKisiAdi, bulanKisiTelefon, bulunmaDurumu, bulunmaTarihi, tasmaliMi);
        this.tuyRengi = tuyRengi;
        this.favoriYemek = favoriYemek;
    }


    public String getTuyRengi() {
        return tuyRengi;
    }

    public void setTuyRengi(String tuyRengi) {
        this.tuyRengi = tuyRengi;
    }

    public String getFavoriYemek() {
        return favoriYemek;
    }

    public void setFavoriYemek(String favoriYemek) {
        this.favoriYemek = favoriYemek;
    }
}
