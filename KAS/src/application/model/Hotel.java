package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisPrNat;
    private ArrayList<HotelTillæg> hoteltillæg;

    public Hotel(String navn, String adresse, double prisPrNat) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisPrNat = prisPrNat;
//        if (hoteltillæg == null) {
//            this.hoteltillæg = hoteltillæg;
//        } else {
//            this.hoteltillæg = new ArrayList<HotelTillæg>();
//        }
    }

    @Override
    public String toString() {
        return navn;
    }
    
    // ---------------------------
    
    public ArrayList<HotelTillæg> getHoteltillæg() {
        return new ArrayList<>(hoteltillæg);
    }

    void addHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
    	this.hoteltillæg.add(hoteltillæg);
    }

    void removeHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
    	this.hoteltillæg.remove(hoteltillæg);
    }

}
