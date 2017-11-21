package application.model;

import java.time.LocalDate;

public class Udflugt {
    private String sted;
    private LocalDate dato;
    private double pris;
    private boolean frokost;
    private String beskrivelse;
    private String navn;
    private Konference konference;

    public Udflugt(String sted, LocalDate dato, double pris, boolean frokost, String beskrivelse, String navn,
            Konference konference) {
        this.sted = sted;
        this.dato = dato;
        this.pris = pris;
        this.frokost = frokost;
        this.beskrivelse = beskrivelse;
        this.navn = navn;
        this.konference = konference;
    }

    public String getSted() {
        return sted;
    }

    public LocalDate getDato() {
        return dato;
    }

    public double getPris() {
        return pris;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public String getNavn() {
        return navn;
    }

    public Konference getKonference() {
        return konference;
    }

    public String toString() {
        String string = "" + navn;
        return string;
    }

}
