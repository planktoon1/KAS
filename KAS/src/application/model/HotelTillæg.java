package application.model;

public class HotelTillæg {
    private String navn;
    private double pris;
    private Hotel hotel;

    public HotelTillæg(String navn, double pris, Hotel hotel) {
        this.navn = navn;
        this.pris = pris;

        this.hotel = hotel;
        setHotel(hotel);
    }

    public double getPris() {
        return pris;
    }

    @Override
    public String toString() {
        return navn + ": " + pris + "Kr,-";
    }

    public void setHotel(Hotel hotel) {
        if (hotel != null) {

            this.hotel = hotel;
            hotel.addHoteltillæg(this);
        }
    }
}
