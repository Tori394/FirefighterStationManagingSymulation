package model;

import model.stany.*;
import model.zdarzenia.Zdarzenie;

import java.util.Random;

public class Auto {
    private final int id;
    private StanAuta stan;

    public Auto(int id) {
        this.id = id;
        this.stan = new StanWolny(); // Domyślny stan
    }

    public void wyjedzDoZdarzenia(Zdarzenie zdarzenie) {
        // 1. Zmieniamy stan na ZAJĘTY (natychmiast)
        this.setStan(new StanZajety());
        Random random = new Random();

        // 2. Uruchamiamy osobny wątek dla tego samochodu
        new Thread(() -> {
            try {
                // Dojazd (0-3s)
                int czasDojazdu = random.nextInt(4) * 1000;
                Thread.sleep(czasDojazdu);

                // Sprawdzenie czy AF (5% szans)
                boolean falszywy = random.nextInt(100) < 5;
                zdarzenie.handlePrzyjazdAuta();

                if (!falszywy) {
                    // Gaszenie pożaru (5-25s)
                    int czasAkcji = zdarzenie.getCzasAkcji();
                    Thread.sleep(czasAkcji);
                }

                zdarzenie.getStrategia().setKolor();
                zdarzenie.handleOdjazdAuta();
                if (zdarzenie.getAktywneAuta()==0){
                    zdarzenie.zakoncz();
                }

                // Powrót (0-3s)
                int czasPowrotu = random.nextInt(4) * 1000;
                Thread.sleep(czasPowrotu);

                // Koniec - Auto znowu wolne
                this.setStan(new StanWolny());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public int getId() {
        return id;
    }

    public void setStan(StanAuta stan) {
        this.stan = stan;
    }

    public boolean czyMozeWyjechac() {
        return stan.czyWolny();
    }
}