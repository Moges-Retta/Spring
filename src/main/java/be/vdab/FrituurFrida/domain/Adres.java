package be.vdab.FrituurFrida.domain;

public class Adres {
    private final String straat;
    private final String huisNr;
    private Gemente gemente;

    public Gemente getGemente() {
        return gemente;
    }


    public String getStraat() {
        return straat;
    }

    public String getHuisNr() {
        return huisNr;
    }


    public Adres(String straat, String huisNr, Gemente gemente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.gemente = gemente;
    }

}
