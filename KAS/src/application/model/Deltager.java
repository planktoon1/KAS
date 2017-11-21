package application.model;

public class Deltager {
    private String navn;
    private String adresse;
    private Firma firma;
    private String tlf;

    public Deltager(String navn, String adresse, String tlf) {
        this.navn = navn;
        this.adresse = adresse;
        this.firma = null;
        this.tlf = tlf;
    }

    public Deltager(String navn, String adresse, Firma firma, String tlf) {
        this.navn = navn;
        this.adresse = adresse;
        this.firma = firma;
        this.tlf = tlf;
    }

}
