package application.service;

import java.util.ArrayList;
import java.util.Date;

import application.model.Konference;
import storage.Storage;

public class Service {

    /**
     * Returnerer alle Konferencer i Storage-klassen.
     */
    public static ArrayList<Konference> getAllkonferencer() {
        return Storage.getAllKonferencer();
    }

    /**
     * Creater en ny konference.<br />
     */
    public static Konference createKonference(String navn, Date start, Date slut,
            String adresse) {
        Konference konference = new Konference(navn, start, slut, adresse);
        Storage.storeKonference(konference);
        return konference;
    }
}
