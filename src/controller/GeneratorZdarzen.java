package controller;

import model.*;
import model.strategie.StrategiaMZ;
import model.strategie.StrategiaPZ;
import model.strategie.StrategiaZdarzenia;
import model.zdarzenia.Zdarzenie;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneratorZdarzen {
    // Granice obszaru
    private static final double MIN_LON = 19.688292482742394;
    private static final double MAX_LON = 20.02470275868903;
    private static final double MIN_LAT = 49.95855025648944;
    private static final double MAX_LAT = 50.154564013341734;

    private final Random random = new Random();

    private final List<Obserwator> obserwatorzy = new ArrayList<>();

    public void dodajObserwatora(Obserwator o) {
        obserwatorzy.add(o);
    }

    // powiadomienie
    public void generujRaz() {
        double lat = MIN_LAT + (MAX_LAT - MIN_LAT) * random.nextDouble();
        double lon = MIN_LON + (MAX_LON - MIN_LON) * random.nextDouble();
        Wspolrzedne pos = new Wspolrzedne(lat, lon);

        StrategiaZdarzenia strategia;
        if (random.nextInt(10) < 3) {
            strategia = new StrategiaPZ();
        } else {
            strategia = new StrategiaMZ();
        }

        Zdarzenie nowe = new Zdarzenie(strategia, pos);

        for (Obserwator o : obserwatorzy) {
            o.naNoweZdarzenie(nowe);
        }
    }
}