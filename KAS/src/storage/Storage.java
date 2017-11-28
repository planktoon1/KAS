package storage;

import java.util.ArrayList;

import application.model.Deltager;
import application.model.Firma;
import application.model.Hotel;
import application.model.Konference;

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class Storage {
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();

    // -------------------------------------------------------------------------

    public static ArrayList<Deltager> getAllDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public static void storeDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }

    public static ArrayList<Konference> getAllKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void storeKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static ArrayList<Hotel> getAllHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void storeHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static ArrayList<Firma> getAllfirmaer() {
        return new ArrayList<>(firmaer);
    }

    public static void storeFirma(Firma firma) {
        firmaer.add(firma);
    }

}
