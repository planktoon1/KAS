package application.model;

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class HotelTillæg {
    private String navn;
    private double pris;
    private Hotel hotel;

    public HotelTillæg(String navn, double pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public double getPris() {
        return pris;
    }

    @Override
    public String toString() {
        return navn + ": " + pris + "Kr,-";
    }

    void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
