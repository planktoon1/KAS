package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private ArrayList<Tilmelding> tilmeldinger;
    private LocalDate start;
    private LocalDate slut;
    private String adresse;

    private ArrayList<Udflugt> udflugter;
    private ArrayList<Hotel> hoteller;

    public Konference(String navn, LocalDate start, LocalDate slut, String adresse) {
        this.navn = navn;
        this.tilmeldinger = new ArrayList<Tilmelding>();
        this.start = start;
        this.slut = slut;
        this.adresse = adresse;
        this.udflugter = new ArrayList<Udflugt>();
        this.hoteller = new ArrayList<Hotel>();
    }

    public void addTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    public void addUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public void addHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    @Override
    public String toString() {
        String string = "" + navn;
        return string;
    }

    public String getNavn() {
        return navn;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public ArrayList<Hotel> getHoteller() {
        return hoteller;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getSlut() {
        return slut;
    }

    public String getAdresse() {
        return adresse;
    }

}
