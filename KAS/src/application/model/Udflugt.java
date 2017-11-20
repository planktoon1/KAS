package application.model;

import java.time.LocalDate;

public class Udflugt {
    private String sted;
    private LocalDate dato;
    private double pris;
    private boolean frokost;
    private String beskrivelse;
    private String navn;

    public Udflugt(String sted, LocalDate dato, double pris, boolean frokost, String beskrivelse, String navn) {
        this.sted = sted;
        this.dato = dato;
        this.pris = pris;
        this.frokost = frokost;
        this.beskrivelse = beskrivelse;
        this.navn = navn;

    }
}
