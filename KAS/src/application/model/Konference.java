package application.model;

import java.util.ArrayList;
import java.util.Date;

public class Konference {
    private String navn;
    private ArrayList<Tilmelding> tilmeldinger;
    private Date start;
    private Date slut;
    private String adresse;

    public Konference(String navn, ArrayList<Tilmelding> tilmeldinger, Date start, Date slut, String adresse) {
        this.navn = navn;
        if (tilmeldinger.size() > 0) {
            this.tilmeldinger = tilmeldinger;
        } else {
            this.tilmeldinger = new ArrayList<Tilmelding>();
        }
        this.start = start;
        this.slut = slut;
        this.adresse = adresse;

    }

}
