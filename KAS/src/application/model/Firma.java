package application.model;

import java.util.ArrayList;

//gruppe: Anders R.P., Casper L. og Frederik Ø.
public class Firma {
    private String navn;
    private String tlf;

    // association: --> 0..* Deltagere
    private final ArrayList<Deltager> deltagere = new ArrayList<>();

    public Firma(String navn, String tlf) {
        this.navn = navn;
        this.tlf = tlf;
    }

    // -----------------------------------------------------------------------------

    public ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    void addDeltager(Deltager deltager) { // package visibility
        deltagere.add(deltager);
    }

    void removeDeltager(Deltager deltager) { // package visibility
        deltagere.remove(deltager);
    }

    // -----------------------------------------------------------------------------

}
