import model.InicjalizujDane;
import view.Mapa;
import model.*;
import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<JednostkaStrazy> dane = InicjalizujDane.wgrajJednostki();

        JFrame frame = new JFrame("Symulacja PSP Kraków");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        Mapa mapa = new Mapa(dane, 1000, 700);

        frame.add(mapa);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}