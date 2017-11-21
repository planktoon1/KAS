package application.model;

public class HotelTillæg {
    private String navn;
    private double pris;
    private Hotel hotel;

    public HotelTillæg(String navn, double pris, Hotel hotel) {
        this.navn = navn;
        this.pris = pris;
        this.hotel = hotel;
        this.hotel = hotel;
        hotel.addHoteltillæg(this);
    }
}
