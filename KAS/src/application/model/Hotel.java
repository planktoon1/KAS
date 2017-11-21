package application.model;

import java.util.ArrayList;

public class Hotel {
    private String navn;
    private String adresse;
    private double prisPrNat;
    private ArrayList<HotelTillæg> hoteltillæg;

    public Hotel(String navn, String adresse, double prisPrNat, ArrayList<HotelTillæg> hoteltillæg) {
        this.navn = navn;
        this.adresse = adresse;
        this.prisPrNat = prisPrNat;
        if (hoteltillæg == null) {
            this.hoteltillæg = hoteltillæg;
        } else {
            this.hoteltillæg = new ArrayList<HotelTillæg>();
        }
    }

}
