package model.zdarzenia;

import model.Wspolrzedne;
import model.strategie.StrategiaZdarzenia;

import java.util.Random;

public class Zdarzenie {
    private final StrategiaZdarzenia strategia;
    private final Wspolrzedne polozenie;
    private boolean aktywne;
    private int czasAkcji;
    private int aktywneAuta;

    private final Random random = new Random();

    public Zdarzenie(StrategiaZdarzenia strategia, Wspolrzedne polozenie) {
        this.strategia = strategia;
        this.polozenie = polozenie;
        this.aktywne = true;
        this.czasAkcji = (5 + random.nextInt(21)) * 1000;
        this.aktywneAuta = 0;
    }

    public int getCzasAkcji() {
        return czasAkcji;
    }

    public int getAktywneAuta() {
        return aktywneAuta;
    }

    public void handleOdjazdAuta() {
        aktywneAuta--;
    }

    public void handlePrzyjazdAuta() {
        aktywneAuta++;
    }

    public StrategiaZdarzenia getStrategia() {
        return strategia;
    }

    public Wspolrzedne getPolozenie() {
        return polozenie;
    }

    public boolean isAktywne() {
        return aktywne;
    }

    public void zakoncz() {
        this.aktywne = false;
    }
}


