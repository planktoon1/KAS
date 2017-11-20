package application.model;

import java.util.Date;

public class Tilmelding {
    private boolean erFordragsholder;
    private Date ankomstdato;
    private Date afrejsedato;
    private String ledsager;
    private Hotel hotel;
    private Udflugt udflugt;
    private Deltager deltager;
    private double samletris;

    public Tilmelding(boolean erFordragsholder, Date ankomstdato, Date afrejsedato, String ledsager, Hotel hotel,
            Udflugt udflugt, Deltager deltager, double samletPris) {
        this.erFordragsholder = erFordragsholder;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        this.ledsager = ledsager;
        this.hotel = hotel;
        this.udflugt = udflugt;
        this.deltager = deltager;
        this.samletris = samletPris;
    }
}
