package application.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Konference {
    private String navn;
    private ArrayList<Tilmelding> tilmeldinger;
    private LocalDate start;
    private LocalDate slut;
    private String adresse;

    public Konference(String navn, Date start, Date slut, String adresse) {
        this.navn = navn;
        this.tilmeldinger = new ArrayList<Tilmelding>();
        this.start = start;
        this.slut = slut;
        this.adresse = adresse;

    }

    public void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

}
