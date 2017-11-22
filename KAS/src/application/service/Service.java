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

    public static Hotel tilføjHotel(String navn, String adresse, double prisPrNat2, double prisPrNat1,
            Konference konference) {
        Hotel hotel = new Hotel(navn, adresse, prisPrNat2, prisPrNat1, konference);
        Storage.storeHotel(hotel);
        return hotel;
    }

    public static HotelTillæg tilføjHotelTillæg(String navn, double pris, Hotel hotel) {
        HotelTillæg hotelTillæg = new HotelTillæg(navn, pris, hotel);
        return hotelTillæg;
    }

    public static Tilmelding tilføjTilmelding(Boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato,
            String ledsager, Hotel hotel, ArrayList<HotelTillæg> hotelTillæg, ArrayList<Udflugt> udflugter,
            Deltager deltager,
            Konference konference) {
        Tilmelding tilmelding = new Tilmelding(erFordragsholder, ankomstdato, afrejsedato, ledsager, hotel, hotelTillæg,
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

        Hotel h1 = tilføjHotel("Den Hvide Svane", "Eckersbergsgade 67", 1250, 1050, k1);
        Hotel h2 = tilføjHotel("Høtel Phønix", "Grøndlandsstræde 3", 800, 700, k1);
        Hotel h3 = tilføjHotel("Pension Tusindfryd", "Grøndlandsstræde 3", 600, 500, k1);

        HotelTillæg h1t1 = tilføjHotelTillæg("WIFI", 50.0, h1);
        HotelTillæg h2t1 = tilføjHotelTillæg("WIFI", 75.0, h2);
        HotelTillæg h2t2 = tilføjHotelTillæg("Bad", 200.0, h2);
        HotelTillæg h3t3 = tilføjHotelTillæg("Morgenmad", 100.0, h3);

        tilføjUdflugt("Den mørke skov", d1.plusDays(1), 125.0, true,
                "Rollespil i den mørke skov og du må ikke være med før du får lov", "Rollespil",
                k1);

        tilføjUdflugt("Slm Aarhus", d1.plusDays(4), 75.0, false,
                "En af de frækkeste aftener i SLM – eller ”Folden” som vi kalder klubben denne aften. ",
                "StallionNight",
                k1);

        tilføjUdflugt("Det falske sted", d1.plusDays(9), 200.0, true,
                "Vi øver os i at blæse sæbebobler med næsen", "Den farlige leg",
                k1);

        Deltager p1 = new Deltager("Finn Madsen", "8000 Århus C, Eckersbergsgade 19 st tv",
                "+45 22 99 88 41");
        Deltager p2 = new Deltager("Niels Petersen", "8000 Århus C, Eckersbergsgade 19 st tv",
                "+45 22 99 88 41");
        Deltager p3 = new Deltager("Ulla Hansen' Andersen", "8000 Århus C, Eckersbergsgade 19 st tv",
                "+45 22 99 88 41");
        Deltager p4 = new Deltager("Peter Sommer' Andersen", "8000 Århus C, Eckersbergsgade 19 st tv",
                "+45 22 99 88 41");
        Deltager p5 = new Deltager("Lone Jensen' Andersen", "8000 Århus C, Eckersbergsgade 19 st tv",
                "+45 22 99 88 41");

        ArrayList<Udflugt> p3Udflugter = new ArrayList<Udflugt>();
        p3Udflugter.add(Storage.getAllUdflugter().get(0));

        ArrayList<HotelTillæg> p4Tillæg = new ArrayList<HotelTillæg>();
        p4Tillæg.add(h1.getHotelTillæg().get(0));
        ArrayList<Udflugt> p4Udflugter = new ArrayList<Udflugt>();
        p4Udflugter.add(k1.getUdflugter().get(1));
        p4Udflugter.add(k1.getUdflugter().get(2));

        ArrayList<HotelTillæg> p5Tillæg = new ArrayList<HotelTillæg>();
        p5Tillæg.add(h1.getHotelTillæg().get(0));
        ArrayList<Udflugt> p5Udflugter = new ArrayList<Udflugt>();
        p5Udflugter.add(k1.getUdflugter().get(0));
        p5Udflugter.add(k1.getUdflugter().get(1));

        tilføjTilmelding(false, d1, d2, null, null, null, null, p1, k1);
        tilføjTilmelding(false, d1, d2, null, h1, null, null, p2, k1);
        tilføjTilmelding(false, d1, d1.plusDays(1), "Hans Hansen", null, null, p3Udflugter, p3, k1);
        tilføjTilmelding(false, d1, d2, "Mie Sommer", h1, p4Tillæg, p4Udflugter, p4, k1);
        tilføjTilmelding(true, d1, d2, "Jan Madsen", h1, p5Tillæg, p5Udflugter, p5, k1);

    }

}
