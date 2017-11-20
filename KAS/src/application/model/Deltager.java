package application.model;

public class Deltager {
    private String navn;
    private String adresse;
    private Firma firma;

    public Deltager(String navn, String adresse) {
        this.navn = navn;
        this.adresse = adresse;
        this.firma = null;
    }

    public Deltager(String navn, String adresse, Firma firma) {
        this.navn = navn;
        this.adresse = adresse;
        this.firma = firma;
    }

}
