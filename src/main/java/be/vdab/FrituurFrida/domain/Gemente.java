package be.vdab.FrituurFrida.domain;

public class Gemente {
    private final String naam;
    private final int postcode;

    public String getNaam() {
        return naam;
    }

    public int getPostcode() {
        return postcode;
    }

    public Gemente(String naam, int postcode) {
        this.naam = naam;
        this.postcode = postcode;
    }
}
