import controller.GeneratorZdarzen;
import controller.SKKM;
import model.*;
import model.zdarzenia.Zdarzenie;
import view.Mapa;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        List<JednostkaStrazy> jednostki = InicjalizujDane.wgrajJednostki();
        Random random = new Random();

        JFrame frame = new JFrame("System PSP Kraków");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        Mapa mapa = new Mapa(jednostki, 800, 600);
        frame.add(mapa);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // --- KONFIGURACJA OBSERWATORÓW ---

        SKKM skkm = new SKKM(jednostki);

        GeneratorZdarzen generator = new GeneratorZdarzen();
        List<Zdarzenie> aktywneZdarzenia = new ArrayList<>();

        // 1. SKKM nasłuchuje Generatora (Logika biznesowa)
        generator.dodajObserwatora(skkm);

        // 2. Anonimowy obserwator nasłuchuje Generatora (Aktualizacja GUI/Listy)
        generator.dodajObserwatora(zdarzenie -> {
            aktywneZdarzenia.add(zdarzenie);
            // Tutaj mapa odświeży się przy następnym cyklu pętli lub można wymusić repaint()
        });

        // --- PĘTLA CZASU ---
        new Thread(() -> {
            try {
                // Zmienna licząca sekundy do kolejnego pożaru
                int sekundyDoZdarzenia = 0;

                while (true) {
                    // 1. Pętla kręci się ZAWSZE co 1 sekundę (dla płynności mapy)
                    Thread.sleep(1000);

                    // 2. Logika odliczania do pożaru
                    if (sekundyDoZdarzenia <= 0) {
                        // BUM! Czas minął, generujemy zdarzenie
                        generator.generujRaz();

                        // Losujemy, za ile sekund kolejne zdarzenie (2-15s)
                        sekundyDoZdarzenia = random.nextInt(14) + 2;

                    } else {
                        // Jeszcze nie czas, tylko odliczamy
                        sekundyDoZdarzenia--;
                    }

                    // 3. To wykonuje się CO SEKUNDĘ -> Aktualizacja liczników aut
                    aktywneZdarzenia.removeIf(z -> !z.isAktywne());
                    mapa.setZdarzenia(aktywneZdarzenia); // Repaint mapy
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
