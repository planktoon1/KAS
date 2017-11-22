package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisPrNat;

    // association: --> 0..* HotelTillæg
    private ArrayList<HotelTillæg> hotelTillæg = new ArrayList<>();;
    // association: --> 0..1 konference 
    private Konference konference;

    public Hotel(String navn, String adresse, double prisPrNat, Konference konference) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisPrNat = prisPrNat;
        setKonference(konference);
    }

    @Override
    public String toString() {
        return navn;
    }

    // -------------------------------------
    //- Konference
    public Konference getKonference() {
        return konference;
    }

    public void setKonference(Konference konference) {
        if (konference != null) {
            assert this.konference == null;
            this.konference = konference;
            konference.addHotel(this);
        } else {
            assert this.konference != null;
            this.konference.removeHotel(this);
            this.konference = null;
        }
    }

    //- HotelTillæg
    public ArrayList<HotelTillæg> getHoteltillæg() {
        return new ArrayList<>(hotelTillæg);
    }

    void addHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
        System.out.println(hoteltillæg);
        hotelTillæg.add(hoteltillæg);
    }

    void removeHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
        this.hotelTillæg.remove(hoteltillæg);
    }

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getPrisPrNat() {
        return prisPrNat;
    }

    public ArrayList<HotelTillæg> getHotelTillæg() {
        return hotelTillæg;
    }

}
