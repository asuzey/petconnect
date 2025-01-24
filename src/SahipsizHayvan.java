public class SahipsizHayvan extends Hayvan {
    private int sahipsizHayvanID;
    private String saglikDurumu;
    private int barinaktaBulunmaSuresi;
    private String sahiplendirmeDurumu;

    // Constructor, Getter ve Setter'lar
    public SahipsizHayvan(int hayvanID, String ad, String tur, String cins, int yas, String durum,
                          boolean asiliMi, boolean kisirMi, String aciklama,
                          int sahipsizHayvanID, String saglikDurumu, int barinaktaBulunmaSuresi,
                          String sahiplendirmeDurumu) {
        super(hayvanID, ad, tur, cins, yas, durum);
        this.sahipsizHayvanID = sahipsizHayvanID;
        this.saglikDurumu = saglikDurumu;
        this.barinaktaBulunmaSuresi = barinaktaBulunmaSuresi;
        this.sahiplendirmeDurumu = sahiplendirmeDurumu;
    }

    public int getSahipsizHayvanID() {
        return sahipsizHayvanID;
    }

    public void setSahipsizHayvanID(int sahipsizHayvanID) {
        this.sahipsizHayvanID = sahipsizHayvanID;
    }

    public String getSaglikDurumu() {
        return saglikDurumu;
    }

    public void setSaglikDurumu(String saglikDurumu) {
        this.saglikDurumu = saglikDurumu;
    }

    public int getBarinaktaBulunmaSuresi() {
        return barinaktaBulunmaSuresi;
    }

    public void setBarinaktaBulunmaSuresi(int barinaktaBulunmaSuresi) {
        this.barinaktaBulunmaSuresi = barinaktaBulunmaSuresi;
    }

    public String getSahiplendirmeDurumu() {
        return sahiplendirmeDurumu;
    }

    public void setSahiplendirmeDurumu(String sahiplendirmeDurumu) {
        this.sahiplendirmeDurumu = sahiplendirmeDurumu;
    }
}

