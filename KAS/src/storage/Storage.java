package storage;

import java.util.ArrayList;

import application.model.Deltager;
import application.model.Firma;
import application.model.Hotel;
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
        Hotel h1 = new Hotel("Hilton", "Parkvej 9", 1049.0, null);
        Hotel h2 = new Hotel("Lars Larsen Hotel", "Bowlingvej 11", 559.0, null);
        hoteller.add(h1);
        hoteller.add(h2);
    }

}
