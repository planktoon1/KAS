package application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import application.model.Deltager;
import application.model.Hotel;
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

    public static Tilmelding tilføjTilmelding(Boolean erFordragsholder, Date ankomstdato, Date afrejsedato,
            String ledsager, Hotel hotel, Udflugt udflugt, Deltager deltager, double samletPris) {
        Tilmelding tilmelding = new Tilmelding(erFordragsholder, ankomstdato, afrejsedato, ledsager, hotel, udflugt,
                deltager, samletPris);
        return tilmelding;
    }

    public static void initStorage() {
        Storage.initStorage();
    }

}
