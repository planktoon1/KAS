package application.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tilmelding {
<<<<<<< Upstream, based on branch 'master' of https://github.com/planktoon1/KAS.git
	private boolean erFordragsholder;
	private LocalDate ankomstdato;
	private LocalDate afrejsedato;
	private String ledsager;
	private Hotel hotel;
	private ArrayList<Udflugt> udflugter;
	private double samletPris;
=======
    private boolean erFordragsholder;
    private LocalDate ankomstdato;
    private LocalDate afrejsedato;
    private String ledsager;
    private Hotel hotel;
    private ArrayList<HotelTillæg> hotelTillæg;
    private ArrayList<Udflugt> udflugter;
    private double samletPris;
>>>>>>> 4848f08 kkkkk

	// association: --> 0..1 konference
	private Konference konference;
	// association: --> 1 deltager
	private Deltager deltager;

<<<<<<< Upstream, based on branch 'master' of https://github.com/planktoon1/KAS.git
	public Tilmelding(boolean erFordragsholder, LocalDate ankomstdato, LocalDate afrejsedato, String ledsager,
			Hotel hotel, ArrayList<Udflugt> udflugter, Deltager deltager, Konference konference) {
		this.erFordragsholder = erFordragsholder;
		this.ankomstdato = ankomstdato;
		this.afrejsedato = afrejsedato;
		this.ledsager = ledsager;
		this.hotel = hotel;
		this.udflugter = udflugter;
=======
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
>>>>>>> 4848f08 kkkkk

		setDeltager(deltager);
		setKonference(konference);
		this.samletPris = prisForTilmelding();
	}

	//
	public String getLedsager() {
		return ledsager;
	}

<<<<<<< Upstream, based on branch 'master' of https://github.com/planktoon1/KAS.git
	// -----------------------------------------------------------------------------
	// - Konferencer
	public Konference getKonference() {
		return konference;
	}
=======
    public ArrayList<HotelTillæg> getHotelTillæg() {
        return hotelTillæg;
    }

    // -----------------------------------------------------------------------------
    //- Konferencer
    public Konference getKonference() {
        return konference;
    }
>>>>>>> 4848f08 kkkkk

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

<<<<<<< Upstream, based on branch 'master' of https://github.com/planktoon1/KAS.git
	public boolean isErFordragsholder() {
		return erFordragsholder;
	}
=======
    public double prisForTilmelding() {
        double totalPris = 0.0;
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
            totalPris += ChronoUnit.DAYS.between(ankomstdato, afrejsedato) * prisPrNat;
        }
>>>>>>> 4848f08 kkkkk

	public LocalDate getAnkomstdato() {
		return ankomstdato;
	}

	public LocalDate getAfrejsedato() {
		return afrejsedato;
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

	public double prisForTilmelding() {
		double totalPris = 0.0;
		if (!erFordragsholder) {
			totalPris += ChronoUnit.DAYS.between(ankomstdato, afrejsedato) * konference.getDagsPris();
		}
		if (udflugter != null) {
			for (Udflugt e : udflugter) {
				totalPris += e.getPris();
			}
		}
		if (hotel != null) {
			totalPris += ChronoUnit.DAYS.between(ankomstdato, afrejsedato) * hotel.getPrisPrNat();
		}

		return totalPris;
	}

	@Override
	public String toString() {
		return this.deltager.toString();
	}
}
