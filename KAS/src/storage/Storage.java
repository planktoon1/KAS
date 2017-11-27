package storage;

import java.util.ArrayList;

import application.model.Deltager;
import application.model.Hotel;
import application.model.Konference;

public class Storage {
	private static final ArrayList<Hotel> hoteller = new ArrayList<>();
	private static final ArrayList<Konference> konferencer = new ArrayList<>();
	private static final ArrayList<Deltager> deltagere = new ArrayList<>();

	// -------------------------------------------------------------------------

	public static ArrayList<Deltager> getAllDeltagere() {
		return new ArrayList<>(deltagere);
	}

	public static void storeDeltager(Deltager deltager) {
		deltagere.add(deltager);
	}

	public static ArrayList<Konference> getAllKonferencer() {
		return new ArrayList<>(konferencer);
	}

	public static void storeKonference(Konference konference) {
		konferencer.add(konference);
	}

	public static ArrayList<Hotel> getAllHoteller() {
		return new ArrayList<>(hoteller);
	}

	public static void storeHotel(Hotel hotel) {
		hoteller.add(hotel);
	}

}
