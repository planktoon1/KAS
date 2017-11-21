package application.model;

import java.time.LocalDate;

public class Tilmelding {
    private boolean erFordragsholder;
    private LocalDate ankomstdato;
    private LocalDate afrejsedato;
    private String ledsager;
    private Hotel hotel;
    private Udflugt udflugt;
    private double samletPris;
    
 // association: --> 0..1 konference 
    private Konference konference;
 // association: --> 1 deltager
    private Deltager deltager;
    
    public Tilmelding(boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato, String ledsager,
            Hotel hotel, Udflugt udflugt, Deltager deltager, double samletPris, Konference konference) {
        this.erFordragsholder = erFordragsholder;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        this.ledsager = ledsager;
        this.hotel = hotel;
        this.udflugt = udflugt;
        this.samletPris = samletPris;
        
        setDeltager(deltager);
        setKonference(konference);
    }
//
    public String getLedsager() {
        return ledsager;
    }
    
 // -----------------------------------------------------------------------------
    //- Konferencer
    public Konference getKonference() {
        return konference;
    }
    
    public void setKonference(Konference konference) {
        if (konference != null) {
            assert this.konference == null;
            this.konference = konference;
            konference.addTilmelding(this);
        } else {
            assert this.konference != null;
            this.konference.removeTilmelding(this);
            this.konference = null;
        }
    }

    //- Deltager
    public Deltager getDeltager() {
        return deltager;
    }
    
    public void setDeltager(Deltager deltager) {
            assert this.deltager == null;
            this.deltager = deltager;
            deltager.addTilmelding(this);
        }
    
 // -----------------------------------------------------------------------------
}
