package controller;

import model.*;
import model.stany.StanZajety;
import model.strategie.StrategiaZdarzenia;
import model.zdarzenia.Zdarzenie;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

// SKKM JEST OBSERWATOREM
public class SKKM implements Obserwator {
    private final List<JednostkaStrazy> jednostki;

    public SKKM(List<JednostkaStrazy> jednostki) {
        this.jednostki = jednostki;
    }

    //Wywoła handle automatycznie przez generator
    @Override
    public void naNoweZdarzenie(Zdarzenie zdarzenie) {
        obsluzZdarzenie(zdarzenie);
    }


    private void obsluzZdarzenie(Zdarzenie zdarzenie) {
        StrategiaZdarzenia strategia = zdarzenie.getStrategia();
        System.out.println("SKKM ODBIERA ZGŁOSZENIE: " + strategia.getNazwa());

        int potrzebneAuta = strategia.getWymaganeAuta();
        int zadysponowane = 0;

        Iterator<JednostkaStrazy> iterator = dajIteratorNajblizszych(zdarzenie.getPolozenie());

        while (iterator.hasNext() && zadysponowane < potrzebneAuta) {
            JednostkaStrazy jrg = iterator.next();
            for (Auto auto : jrg.getAuta()) {
                if (auto.czyMozeWyjechac()) {
                    auto.wyjedzDoZdarzenia(zdarzenie);
                    zadysponowane++;
                    System.out.println("Wysyłano wóz " + auto.getId() + " z " + jrg.getNazwa());
                    if (zadysponowane == potrzebneAuta) break;
                }
            }
        }
    }

    private Iterator<JednostkaStrazy> dajIteratorNajblizszych(Wspolrzedne cel) {
        return jednostki.stream()
                .sorted(Comparator.comparingDouble(j -> j.getPolozenie().obliczDystansKm(cel)))
                .iterator();
    }
}