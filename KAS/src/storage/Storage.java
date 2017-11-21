package storage;

import java.time.LocalDate;
import java.util.ArrayList;

import application.model.Deltager;
import application.model.Firma;
import application.model.Hotel;
import application.model.HotelTillæg;
import application.model.Konference;
import application.model.Tilmelding;
import application.model.Udflugt;

public class Storage {
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private static final ArrayList<Udflugt> udflugter = new ArrayList<>();

    // ------------------------- ------------------------------------------------

    public static ArrayList<Konference> getAllKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void storeKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static ArrayList<Udflugt> getAllUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public static void storeUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    public static ArrayList<Hotel> getAllHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void storeHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static void initStorage() {
        LocalDate d1 = LocalDate.now();
        LocalDate d2 = d1.plusDays(3);

        LocalDate d3 = LocalDate.now().plusMonths(3);
        LocalDate d4 = d3.plusDays(3);

        Konference k1 = new Konference("konference 1: Star Trekz", d1, d2, "Sønderhøj 30");
        Konference k2 = new Konference("konference 2: Star warz", d3, d4, "Sønderhøj 30");

        storeKonference(k1);
        storeKonference(k2);

        HotelTillæg ht1 = new HotelTillæg("WIFI", 300.0);
        HotelTillæg ht2 = new HotelTillæg("Bad", 150.0);
        HotelTillæg ht3 = new HotelTillæg("Håndklæder", 50.0);

        ArrayList<HotelTillæg> hotelTillæg = new ArrayList<HotelTillæg>();
        hotelTillæg.add(ht1);
        hotelTillæg.add(ht2);
        hotelTillæg.add(ht3);

        Hotel h1 = new Hotel("nigga naps", "bullshitstræde 69", 800.0, hotelTillæg);
        Hotel h2 = new Hotel("sleepz", "Viby torv", 1600.0, hotelTillæg);

        storeHotel(h1);
        storeHotel(h2);

        Udflugt u1 = new Udflugt("Den mørke skov", d1.plusDays(1), 400.0, false,
                "Rollespil i den mørke skov og du må ikke være med før du får lov", "Rollespil",
                k1);

        storeUdflugt(u1);
    }

}
