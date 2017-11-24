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
    private ArrayList<HotelTillæg> hotelTillæg;
    private ArrayList<Udflugt> udflugter;
    private double samletPris;

    // association: --> 0..1 konference
    private Konference konference;
    // association: --> 1 deltager
    private Deltager deltager;

    public Tilmelding(boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato, String ledsager,
            Hotel hotel, ArrayList<HotelTillæg> hotelTillæg, ArrayList<Udflugt> udflugter, Deltager deltager,
            Konference konference) {
        this.erFordragsholder = erFordragsholder;
        this.ankomstdato = ankomstdato;
        this.afrejsedato = afrejsedato;
        this.ledsager = ledsager;
        this.hotel = hotel;
        this.hotelTillæg = hotelTillæg;
        this.udflugter = udflugter;

        setDeltager(deltager);
        setKonference(konference);
        this.samletPris = prisForTilmelding();
    }

    //
    public String getLedsager() {
        return ledsager;
    }

    // -----------------------------------------------------------------------------
    // - Konferencer
    public Konference getKonference() {
        return konference;
    }

    public ArrayList<HotelTillæg> getHotelTillæg() {
        return hotelTillæg;
    }

    // -----------------------------------------------------------------------------

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

    // - Deltager
    public Deltager getDeltager() {
        return deltager;
    }

    public void setDeltager(Deltager deltager) {
        assert this.deltager == null;
        this.deltager = deltager;
        deltager.addTilmelding(this);
    }

    // -----------------------------------------------------------------------------

    public boolean isErFordragsholder() {
        return erFordragsholder;
    }

    public double prisForTilmelding() {
        double totalPris = 0.0;
        int personer = 1;
        if (ledsager != null) {
            personer = 2;
        }
        if (!erFordragsholder) {
            totalPris += ChronoUnit.DAYS.between(ankomstdato, afrejsedato) * konference.getDagsPris();
        }
        if (udflugter != null) {
            for (Udflugt e : udflugter) {
                totalPris += e.getPris();
            }
        }
        if (hotel != null) {
            double prisPrNat = hotel.getPrisPrNat1();
            if (ledsager != null) {
                prisPrNat = hotel.getPrisPrNat2();

            }
            if (hotelTillæg != null) {
                for (HotelTillæg e : hotelTillæg) {
                    totalPris += e.getPris() * personer;

                }
            }

            totalPris += (ChronoUnit.DAYS.between(ankomstdato, afrejsedato) - 1) * prisPrNat;
        }
        return totalPris;
    }

    public LocalDate getAnkomstdato() {
        return ankomstdato;
    }

    public LocalDate getAfrejsedato() {
        return afrejsedato;
    }

    public boolean isFordragsholder() {
        return this.isErFordragsholder();
    }

    public Hotel getHotel() {
        return hotel;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public double getSamletPris() {
        return samletPris;
    }

    @Override
    public String toString() {
        return this.deltager.toString();
    }
}
