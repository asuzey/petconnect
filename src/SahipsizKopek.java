public class SahipsizKopek extends SahipsizHayvan{
    private boolean havliyorMu;
    private String cinsiyet;

    public SahipsizKopek(int hayvanID, String ad, String tur, String cins, int yas, String durum, boolean asiliMi, boolean kisirMi, String aciklama,
                         int sahipsizHayvanID, String saglikDurumu, int barinaktaBulunmaSuresi, String sahiplendirmeDurumu, String ilanTarihi,
                         boolean havliyorMu, String cinsiyet) {
        super(hayvanID, ad, tur, cins, yas, durum, asiliMi, kisirMi, aciklama, sahipsizHayvanID, saglikDurumu, barinaktaBulunmaSuresi, sahiplendirmeDurumu, ilanTarihi);
        this.havliyorMu = havliyorMu;
        this.cinsiyet = cinsiyet;
    }

    public boolean isHavliyorMu() {
        return havliyorMu;
    }

    public void setHavliyorMu(boolean havliyorMu) {
        this.havliyorMu = havliyorMu;
    }

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }
}
