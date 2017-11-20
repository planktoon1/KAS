package application.model;

import java.util.Date;

public class Udflugt {
    private String sted;
    private Date dato;
    private double pris;
    private boolean frokost;
    private String beskrivelse;
    private String navns;

    public Udflugt(String sted, Date dato, double pris, boolean frokost, String beskrivelse, String navn) {
        this.sted = sted;
        this.dato = dato;
        this.pris = pris;
        this.frokost = frokost;
        this.beskrivelse = beskrivelse;
        this.navns = navns;

    }
}
