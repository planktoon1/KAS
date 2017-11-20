package application.service;

import java.time.LocalDate;
import java.util.ArrayList;

import application.model.Konference;
import storage.Storage;

public class Service {

    /**
     * 
     * Returnerer alle Konferencer i Storage-klassen.
     */
    public static ArrayList<Konference> getAllkonferencer() {
        return Storage.getAllKonferencer();
    }

    /**
     * Creater en ny konference.<br />
     */
    public static Konference createKonference(String navn, LocalDate start, LocalDate slut,
            String adresse) {
        Konference konference = new Konference(navn, start, slut, adresse);
        Storage.storeKonference(konference);
        return konference;
    }

    public static void initStorage() {
        Storage.initStorage();
    }

}
