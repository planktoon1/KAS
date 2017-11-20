package application.service;

import java.util.ArrayList;

import application.model.Konference;
import storage.Storage;

public class Service {

    /**
     * Returnerer alle Konferencer i Storage.
     */
    public static ArrayList<Konference> getAllkonferencer() {
        return Storage.getAllKonferencer();
    }

    /**
     * Creater en ny konference.<br />
     * Pre: hours >= 0.
     */
    public static Konference createKonference(String name, int hours) {
        assert hours >= 0;
        Konference konference = new Konference(navn, hours);
        Storage.storeCompany(company);
        return company;
    }
}
