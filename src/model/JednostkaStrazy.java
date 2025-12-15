package model;

import java.util.ArrayList;
import java.util.List;

public class JednostkaStrazy {
    private final String nazwa;
    private final Wspolrzedne polozenie;
    private final List<Auto> auta;

    public JednostkaStrazy(String nazwa, Wspolrzedne polozenie) {
        this.nazwa = nazwa;
        this.polozenie = polozenie;
        this.auta = new ArrayList<>();

        // Każda jednostka ma 5 samochodów
        for (int i = 1; i <= 5; i++) {
            auta.add(new Auto(i));
        }
    }

    public Wspolrzedne getPolozenie() {
        return polozenie;
    }
    public String getNazwa() {
        return nazwa;
    }
    public List<Auto> getAuta() { return auta; }
}