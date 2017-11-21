package application.service;

import java.time.LocalDate;
import java.util.ArrayList;

import application.model.Deltager;
import application.model.Hotel;
import application.model.HotelTillæg;
import application.model.Konference;
import application.model.Tilmelding;
import application.model.Udflugt;
import storage.Storage;

public class Service {

    /**
     * 
     * Returnerer alle Konferencer i Storage-klassen.
     */
    public static ArrayList<Konference> getAllkonferencer() {
        return Storage.getAllKonferencer();
    }

    public static ArrayList<Udflugt> getAllUdflugter() {
        return Storage.getAllUdflugter();
    }

    public static ArrayList<Hotel> getAllHoteller() {
        return Storage.getAllHoteller();
    }

    /**
     * Creater en ny konference.<br />
     */
    public static Konference tilføjKonference(String navn, LocalDate start, LocalDate slut,
            String adresse) {
        Konference konference = new Konference(navn, start, slut, adresse);
        Storage.storeKonference(konference);
        return konference;
    }

    public static Udflugt tilføjUdflugt(String sted, LocalDate dato, double pris, boolean frokost, String beskrivelse,
            String navn, Konference konference) {
        Udflugt udflugt = new Udflugt(sted, dato, pris, frokost, beskrivelse, navn, konference);
        konference.addUdflugt(udflugt);
        Storage.storeUdflugt(udflugt);
        return udflugt;
    }

    public static Hotel tilføjHotel(String navn, String adresse, double prisPrNat) {
        Hotel hotel = new Hotel(navn, adresse, prisPrNat);
        Storage.storeHotel(hotel);
        return hotel;
    }
    
    public static HotelTillæg tilføjHotelTillæg(String navn,  double pris, Hotel hotel) {
    	HotelTillæg hotelTillæg = new HotelTillæg(navn, pris, hotel);
    	//Storage.storeHotelTillæg(hotelTillæg); //Nødvendig ? idk
        return hotelTillæg;
    }

    public static Tilmelding tilføjTilmelding(Boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato,
            String ledsager, Hotel hotel, Udflugt udflugt, Deltager deltager, double samletPris,
            Konference konference) {
        Tilmelding tilmelding = new Tilmelding(erFordragsholder, ankomstdato, afrejsedato, ledsager, hotel,
                udflugt, deltager, samletPris, konference);
        konference.addTilmelding(tilmelding);

        return tilmelding;
    }

    public static void initStorage() {
        //Storage.initStorage();
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = d1.plusDays(3);

        LocalDate d3 = LocalDate.now().plusMonths(3);
        LocalDate d4 = d3.plusDays(3);

        Konference k1 = tilføjKonference("konference 1: Star Trekz", d1, d2, "Sønderhøj 30");
        Konference k2 = tilføjKonference("konference 2: Star warz", d3, d4, "Sønderhøj 30");

        Hotel h1 = tilføjHotel("nigga naps", "bullshitstræde 69", 800.0);
        Hotel h2 = tilføjHotel("sleepz", "Viby torv", 1600.0);
        
        HotelTillæg ht1 = tilføjHotelTillæg("WIFI", 300.0, h1);
        HotelTillæg ht2 = tilføjHotelTillæg("Bad", 150.0, h1);
        HotelTillæg ht3 = tilføjHotelTillæg("Håndklæder", 50.0, h2);

        k1.addHotel(h1);
        k2.addHotel(h2);


        

//        tilføjUdflugt("Den mørke skov", d1.plusDays(1), 400.0, false,
//                "Rollespil i den mørke skov og du må ikke være med før du får lov", "Rollespil",
//                Storage.getAllKonferencer().get(0));
        
        //Tilmelding t1 = new Tilmelding(false,d1,d2,"Heidi",new Hotel("nigga naps", "bullshitstræde 69", 800.0, hotelTillæg),getAllkonferencer().get(0).getUdflugter().get(1),new Deltager("lars", "et sted ", "99999999"),1000.00,getAllkonferencer().get(0));

    }

}
