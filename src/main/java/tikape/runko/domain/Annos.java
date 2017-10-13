package tikape.runko.domain;

public class Annos {
    private Integer id;
    private String nimi;
    private String ohje;
    private Integer maara;
    
    public Annos(int id, String nimi, String ohje, int maara) {
        this.id = id;
        this.nimi = nimi;
        this.ohje = ohje;
        this.maara = maara;
    }

    public Integer getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

    public String getOhje() {
        return ohje;
    }

    public Integer getMaara() {
        return maara;
    }
}
