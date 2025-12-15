package model;

public class Auto {
    private final int id;

    private String stan = "WOLNY";

    public Auto(int id) {
        this.id = id;
    }

    public int getId() { return id; }

    public void setStan(String stan) { this.stan = stan; }
    public String getStan() { return stan; }
}
