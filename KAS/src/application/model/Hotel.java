package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisPrNat;
    private ArrayList<HotelTillæg> hotelTillæg = new ArrayList<>();;

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
        return new ArrayList<>(hotelTillæg);
    }

    void addHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
        System.out.println(hoteltillæg);
    	hotelTillæg.add(hoteltillæg);
    }

    void removeHoteltillæg(HotelTillæg hoteltillæg) { // package visibility
    	this.hotelTillæg.remove(hoteltillæg);
    }

}
