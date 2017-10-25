package tikape.runko.domain;

public class AnnosRaakaAine {

    private int id;
    private int annos_id;
    private int raakaAine_id;
    private int maara;
    private String ohje;

    public AnnosRaakaAine(int id, int annos_id, int raakaAine_id, int maara, String ohje) {
        this.id = id;
        this.annos_id = annos_id;
        this.raakaAine_id = raakaAine_id;
        this.maara = maara;
        this.ohje = ohje;
    }

    public int getId() {
        return id;
    }

    public int getAnnos_id() {
        return annos_id;
    }

    public int getRaakaAine_id() {
        return raakaAine_id;
    }

    public int getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnnos_id(int annos_id) {
        this.annos_id = annos_id;
    }

    public void setRaakaAine_id(int raakaAine_id) {
        this.raakaAine_id = raakaAine_id;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
}
