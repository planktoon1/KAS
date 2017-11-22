package application.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tilmelding {
    private boolean erFordragsholder;
    private LocalDate ankomstdato;
    private LocalDate afrejsedato;
    private String ledsager;
    private Hotel hotel;
    private ArrayList<Udflugt> udflugter;
    private double samletPris;

    // association: --> 0..1 konference 
    private Konference konference;
    // association: --> 1 deltager
    private Deltager deltager;

    public Tilmelding(boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato, String ledsager,
            Hotel hotel, ArrayList<Udflugt> udflugter, Deltager deltager, double samletPris, Konference konference) {
        this.erFordragsholder = erFordragsholder;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        this.ledsager = ledsager;
        this.hotel = hotel;
        this.udflugter = udflugter;
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

    public double prisForTilmelding() {
        double totalPris = 0.0;
        if (!erFordragsholder) {
            totalPris += ChronoUnit.DAYS.between(ankomstdato, afrejsedato) * konference.getDagsPris();
        }
        for (Udflugt e : udflugter) {
            totalPris += e.getPris();
        }
        if (hotel != null) {
            totalPris += ChronoUnit.DAYS.between(ankomstdato, afrejsedato) * hotel.getPrisPrNat()
        }

        return totalPris;
    }
}
