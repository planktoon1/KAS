package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisPrNat2;
    private double prisPrNat1;

    // association: --> 0..* HotelTillæg
    private ArrayList<HotelTillæg> hotelTillæg = new ArrayList<>();
    // association: --> 0..1 konference 
    private Konference konference;

    public Hotel(String navn, String adresse, double prisPrNat2, double prisPrNat1, Konference konference) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisPrNat2 = prisPrNat2;
        this.prisPrNat1 = prisPrNat1;
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
    public ArrayList<HotelTillæg> getHotelTillæg() {
        return new ArrayList<>(hotelTillæg);
    }

    void addHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
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

    public double getPrisPrNat1() {
        return prisPrNat1;
    }

    public double getPrisPrNat2() {
        return prisPrNat2;
    }

}
