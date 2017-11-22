package application.model;

import java.util.ArrayList;

public class Deltager {
	private String navn;
	private String adresse;
	private String tlf;

	// association: --> 0..* Tilmeldinger
	private final ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
	// association: --> 0..1 Firma
	private Firma firma;

	public Deltager(String navn, String adresse, String tlf) {
		this.navn = navn;
		this.adresse = adresse;
		this.firma = null;
		this.tlf = tlf;
	}

	public Deltager(String navn, String adresse, String tlf, String firmaNavn, String firmaTlf) {
		this.navn = navn;
		this.adresse = adresse;
		this.tlf = tlf;

		Firma firma = new Firma(firmaNavn, firmaTlf);
		setFirma(firma);
	}

	// ------------------------------------------
	// - Tilmeldinger
	public ArrayList<Tilmelding> getTilmeldinger() {
		return new ArrayList<>(tilmeldinger);
	}

	void addTilmelding(Tilmelding tilmelding) { // package visibility
		tilmeldinger.add(tilmelding);
	}

	/**
	 * Pre: This employee is not connected to a company, if company != null; Pre:
	 * This employee is connected to a company. if company == null; Note: company is
	 * nullable.
	 */
	public void setFirma(Firma firma) {
		if (firma != null) {
			assert this.firma == null;
			this.firma = firma;
			firma.addDeltager(this);
		} else {
			assert this.firma != null;
			this.firma.removeDeltager(this);
			this.firma = null;
		}
	}

	@Override
	public String toString() {
		return navn;
	}

	// ------------------------------------------
	// - Firma
	public Firma getFirma() {
		return firma;
	}

	public String getNavn() {
		return navn;
	}

}
