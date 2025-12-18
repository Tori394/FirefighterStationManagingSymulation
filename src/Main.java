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


        SKKM skkm = new SKKM(jednostki);

        GeneratorZdarzen generator = new GeneratorZdarzen();
        List<Zdarzenie> aktywneZdarzenia = new ArrayList<>();

        generator.dodajObserwatora(skkm);

        generator.dodajObserwatora(zdarzenie -> {
            aktywneZdarzenia.add(zdarzenie);
        });

        new Thread(() -> {
            try {
                int sekundyDoZdarzenia = 0;

                while (true) {
                    Thread.sleep(1000);

                    if (sekundyDoZdarzenia <= 0) {
                        generator.generujRaz();

                        sekundyDoZdarzenia = random.nextInt(8) + 2;

                    } else {
                        sekundyDoZdarzenia--;
                    }

                    aktywneZdarzenia.removeIf(z -> !z.isAktywne());
                    mapa.setZdarzenia(aktywneZdarzenia);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
