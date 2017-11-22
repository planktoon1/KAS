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

    @Override
    public String toString() {
        return hotel + " | " + navn + ": " + pris + "Kr,-";
    }

    public void setHotel(Hotel hotel) {
        if (hotel != null) {
<<<<<<< HEAD
=======
            assert this.hotel != null;
>>>>>>> branch 'master' of https://github.com/planktoon1/KAS.git
            this.hotel = hotel;
            hotel.addHoteltillæg(this);
        }
    }
}
