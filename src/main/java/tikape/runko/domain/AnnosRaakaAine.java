package tikape.runko.domain;

public class AnnosRaakaAine {

    private int annos_id;
    private int raakaAine_id;
    private String nimi;
    private int jarjestys;
    private String maara;
    private String ohje;

    public AnnosRaakaAine(int annos_id, int raakaAine_id, String nimi, int jarjestys, String maara, String ohje) {
        this.nimi = nimi;
        this.annos_id = annos_id;
        this.raakaAine_id = raakaAine_id;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getJarjestys() {
        return jarjestys;
    }

    public void setJarjestys(int jarjestys) {
        this.jarjestys = jarjestys;
    }

    public int getAnnos_id() {
        return annos_id;
    }

    public int getRaakaAine_id() {
        return raakaAine_id;
    }

    public String getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setAnnos_id(int annos_id) {
        this.annos_id = annos_id;
    }

    public void setRaakaAine_id(int raakaAine_id) {
        this.raakaAine_id = raakaAine_id;
    }

    public void setMaara(String maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
}
