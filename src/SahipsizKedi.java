public class SahipsizKedi extends SahipsizHayvan{
    private boolean miyavlıyorMu;
    private  String tuyUzunlugu;

    public SahipsizKedi(int hayvanID, String ad, String tur, String cins, int yas, String durum, boolean asiliMi, boolean kisirMi, String aciklama,
                        int sahipsizHayvanID, String saglikDurumu, int barinaktaBulunmaSuresi, String sahiplendirmeDurumu, String ilanTarihi,
                        boolean miyavlıyorMu, String tuyUzunlugu) {
        super(hayvanID, ad, tur, cins, yas, durum, asiliMi, kisirMi, aciklama, sahipsizHayvanID, saglikDurumu, barinaktaBulunmaSuresi, sahiplendirmeDurumu, ilanTarihi);
        this.miyavlıyorMu = miyavlıyorMu;
        this.tuyUzunlugu = tuyUzunlugu;
    }

    public boolean isMiyavlıyorMu() {
        return miyavlıyorMu;
    }

    public void setMiyavlıyorMu(boolean miyavlıyorMu) {
        this.miyavlıyorMu = miyavlıyorMu;
    }

    public String getTuyUzunlugu() {
        return tuyUzunlugu;
    }

    public void setTuyUzunlugu(String tuyUzunlugu) {
        this.tuyUzunlugu = tuyUzunlugu;
    }
}
