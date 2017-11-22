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
            String adresse, double dagsPris) {
        Konference konference = new Konference(navn, start, slut, adresse, dagsPris);
        Storage.storeKonference(konference);
        return konference;
    }

    public static Udflugt tilføjUdflugt(String sted, LocalDate dato, double pris, boolean frokost, String beskrivelse,
            String navn, Konference konference) {
        Udflugt udflugt = new Udflugt(sted, dato, pris, frokost, beskrivelse, navn, konference);
        Storage.storeUdflugt(udflugt);
        return udflugt;
    }

    public static Hotel tilføjHotel(String navn, String adresse, double prisPrNat, Konference konference) {
        Hotel hotel = new Hotel(navn, adresse, prisPrNat, konference);
        Storage.storeHotel(hotel);
        return hotel;
    }

    public static HotelTillæg tilføjHotelTillæg(String navn, double pris, Hotel hotel) {
        HotelTillæg hotelTillæg = new HotelTillæg(navn, pris, hotel);
        return hotelTillæg;
    }

    public static Tilmelding tilføjTilmelding(Boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato,
            String ledsager, Hotel hotel, ArrayList<Udflugt> udflugter, Deltager deltager,
            Konference konference) {
        Tilmelding tilmelding = new Tilmelding(erFordragsholder, ankomstdato, afrejsedato, ledsager, hotel,
                udflugter, deltager, konference);

        return tilmelding;
    }

    public static boolean validDouble(String string) {
        boolean result = true;
        boolean dotFound = false;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= '0' && string.charAt(i) <= '9' || string.charAt(i) == '.') {
                if (string.charAt(i) == '.') {
                    if (dotFound) {
                        result = false;
                        break;
                    }
                    dotFound = true;
                }
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    public static void initStorage() {
        //Storage.initStorage();
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = d1.plusDays(3);

        LocalDate d3 = LocalDate.now().plusMonths(3);
        LocalDate d4 = d3.plusDays(3);

        Konference k1 = tilføjKonference("Hav og Himmel", d1, d2, "Sønderhøj 30", 1500);
        Konference k2 = tilføjKonference("Comic-Con", d3, d4, "Finlandsgade 19", 1300);

        Hotel h1 = tilføjHotel("Den Hvide Svane", "Eckersbergsgade 67", 800.0, k1);
        Hotel h2 = tilføjHotel("Hilton", "Grøndlandsstræde 3", 1600.0, k2);

        HotelTillæg ht1 = tilføjHotelTillæg("WIFI", 300.0, h1);
        HotelTillæg ht2 = tilføjHotelTillæg("Bad", 150.0, h1);
        HotelTillæg ht3 = tilføjHotelTillæg("Håndklæder", 50.0, h2);

        tilføjUdflugt("Den mørke skov", d1.plusDays(1), 400.0, false,
                "Rollespil i den mørke skov og du må ikke være med før du får lov", "Rollespil",
                k1);

        tilføjUdflugt("Slm Aarhus", d1.plusDays(4), 130.0, false,
                "En af de frækkeste aftener i SLM – eller ”Folden” som vi kalder klubben denne aften. ",
                "StallionNight",
                k1);

        tilføjUdflugt("Det falske sted", d1.plusDays(9), 10.0, true,
                "Vi øver os i at blæse sæbebobler med næsen", "Den farlige leg",
                k2);

        Deltager p1 = new Deltager("Finn 'Karrigan' Andersen", "8000 Århus C, Eckersbergsgade 19 st tv",
                "+45 22 99 88 41");

        tilføjTilmelding(false, d1, d2, null, null, null, p1, k1);

    }

}
