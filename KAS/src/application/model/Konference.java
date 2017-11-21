package application.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private LocalDate start;
    private LocalDate slut;
    private String adresse;

    // association: --> 0..* Tilmeldinger
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<Tilmelding>();
    // association: --> 0..* Udflugter
    private ArrayList<Udflugt> udflugter = new ArrayList<Udflugt>();
    // association: --> 0..* Hoteller
    private ArrayList<Hotel> hoteller = new ArrayList<Hotel>();;

    public Konference(String navn, LocalDate start, LocalDate slut, String adresse) {
        this.navn = navn;
        this.start = start;
        this.slut = slut;
        this.adresse = adresse;
    }

    //---------------Association's håndtering------------------
    //- Tilmeldinger
    public ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }
    
    void addTilmelding(Tilmelding tilmelding) { // package visibility
        tilmeldinger.add(tilmelding);
    }
    
    void removeTilmelding(Tilmelding tilmelding) { // package visibility
    	tilmeldinger.remove(tilmelding);
    }
    
    //- Udflugter
    public ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    void addUdflugt(Udflugt udflugt) { // package visibility
    	udflugter.add(udflugt);
    }
    
    void removeUdflugt(Udflugt udflugt) { // package visibility
    	udflugter.remove(udflugt);
    }

    //- Hoteller
    public ArrayList<Hotel> getHoteller() { 
        return new ArrayList<>(hoteller);
    }

    void addHotel(Hotel hotel) { // package visibility
    	hoteller.add(hotel);
    }
    
    void removeHotel(Hotel hotel) { // package visibility
    	hoteller.remove(hotel);
    }
    
    //---------------------------------------------------------
    @Override
    public String toString() {
        String string = "" + navn;
        return string;
    }

    public String getNavn() {
        return navn;
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
