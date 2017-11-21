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

    public static Hotel tilføjHotel(String navn, String adresse, double prisPrNat, ArrayList<HotelTillæg> hoteltillæg) {
        Hotel hotel = new Hotel(navn, adresse, prisPrNat, hoteltillæg);
        Storage.storeHotel(hotel);
        return hotel;
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

        tilføjKonference("konference 1: Star Trekz", d1, d2, "Sønderhøj 30");
        tilføjKonference("konference 2: Star warz", d3, d4, "Sønderhøj 30");

        HotelTillæg ht1 = new HotelTillæg("WIFI", 300.0);
        HotelTillæg ht2 = new HotelTillæg("Bad", 150.0);
        HotelTillæg ht3 = new HotelTillæg("Håndklæder", 50.0);

        ArrayList<HotelTillæg> hotelTillæg = new ArrayList<HotelTillæg>();
        hotelTillæg.add(ht1);
        hotelTillæg.add(ht2);
        hotelTillæg.add(ht3);

        tilføjHotel("nigga naps", "bullshitstræde 69", 800.0, hotelTillæg);
        tilføjHotel("sleepz", "Viby torv", 1600.0, hotelTillæg);

        tilføjUdflugt("Den mørke skov", d1.plusDays(1), 400.0, false,
                "Rollespil i den mørke skov og du må ikke være med før du får lov", "Rollespil",
                Storage.getAllKonferencer().get(0));

    }

}
