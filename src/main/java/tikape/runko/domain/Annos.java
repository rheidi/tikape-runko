package tikape.runko.domain;

public class Annos {
    private Integer id;
    private String nimi;
    private String ohje;
    private Integer maara;
    
    public Annos(int id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public Integer getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }

}
