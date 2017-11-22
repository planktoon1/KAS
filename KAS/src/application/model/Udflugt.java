package application.model;

import java.time.LocalDate;

public class Udflugt {
    private String sted;
    private LocalDate dato;
    private double pris;
    private boolean frokost;
    private String beskrivelse;
    private String navn;

    // association: --> 0..1 konference 
    private Konference konference;

    public Udflugt(String sted, LocalDate dato, double pris, boolean frokost, String beskrivelse, String navn,
            Konference konference) {
        this.sted = sted;
        this.dato = dato;
        this.pris = pris;
        this.frokost = frokost;
        this.beskrivelse = beskrivelse;
        this.navn = navn;
        setKonference(konference);
    }

    public String getSted() {
        return sted;
    }

    public LocalDate getDato() {
        return dato;
    }

    public String getFrokost() {
        if (frokost == true) {
            return "Ja";
        }
        return "Nej";
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

    @Override
    public String toString() {
        String string = "" + navn + ", " + pris;
        return string;
    }
    // -----------------------------------------------------------------------------

    public Konference getKonference() {
        return konference;
    }

    public void setKonference(Konference konference) {
        if (konference != null) {
            assert this.konference == null;
            this.konference = konference;
            konference.addUdflugt(this);
        } else {
            assert this.konference != null;
            this.konference.removeUdflugt(this);
            this.konference = null;
        }
    }

    // -----------------------------------------------------------------------------

}
