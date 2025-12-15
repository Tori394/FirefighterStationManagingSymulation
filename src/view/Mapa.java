package view;

import model.*;
import model.strategie.StrategiaPZ;
import model.zdarzenia.Zdarzenie;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Mapa extends JPanel {
    private final List<JednostkaStrazy> jednostki;
    private List<Zdarzenie> zdarzenia;

    // Granice obszaru z pożarami
    private final double AREA_MIN_LON = 19.688292482742394;
    private final double AREA_MAX_LON = 20.02470275868903;
    private final double AREA_MIN_LAT = 49.95855025648944;
    private final double AREA_MAX_LAT = 50.154564013341734;

    // Granice widoku
    private double viewMinLon, viewMaxLon;
    private double viewMinLat, viewMaxLat;

    private final Shape szaryObszarShape;
    private final double scaleX;
    private final double scaleY;

    public Mapa(List<JednostkaStrazy> jednostki, int width, int height) {
        this.jednostki = jednostki;
        this.setBackground(Color.WHITE);

        this.setPreferredSize(new Dimension(width, height));
        obliczGraniceGeograficzne();

        this.scaleX = width / (viewMaxLon - viewMinLon);
        this.scaleY = height / (viewMaxLat - viewMinLat);

        int x1 = (int) ((AREA_MIN_LON - viewMinLon) * scaleX);
        int x2 = (int) ((AREA_MAX_LON - viewMinLon) * scaleX);

        int y1 = (int) ((viewMaxLat - AREA_MAX_LAT) * scaleY);
        int y2 = (int) ((viewMaxLat - AREA_MIN_LAT) * scaleY);

        this.szaryObszarShape = new Rectangle(x1, y1, x2 - x1, y2 - y1);
    }

    public void setZdarzenia(List<Zdarzenie> zdarzenia) {
        this.zdarzenia = zdarzenia;
        this.repaint();
    }

    private void obliczGraniceGeograficzne() {
        double unitsMinLon = Double.MAX_VALUE;
        double unitsMaxLon = -Double.MAX_VALUE;
        double unitsMinLat = Double.MAX_VALUE;
        double unitsMaxLat = -Double.MAX_VALUE;

        for (JednostkaStrazy js : jednostki) {
            Wspolrzedne w = js.getPolozenie();
            if (w.getDlugosc() < unitsMinLon) unitsMinLon = w.getDlugosc();
            if (w.getDlugosc() > unitsMaxLon) unitsMaxLon = w.getDlugosc();
            if (w.getSzerokosc() < unitsMinLat) unitsMinLat = w.getSzerokosc();
            if (w.getSzerokosc() > unitsMaxLat) unitsMaxLat = w.getSzerokosc();
        }

        viewMinLon = Math.min(unitsMinLon, AREA_MIN_LON);
        viewMaxLon = Math.max(unitsMaxLon, AREA_MAX_LON);
        viewMinLat = Math.min(unitsMinLat, AREA_MIN_LAT);
        viewMaxLat = Math.max(unitsMaxLat, AREA_MAX_LAT);

        double margines = 0.03;
        viewMinLon -= margines;
        viewMaxLon += margines;
        viewMinLat -= margines;
        viewMaxLat += margines;
    }

    // Metody konwersji gps -> px
    private int lonToPixelX(double lon) {
        return (int) ((lon - viewMinLon) * scaleX);
    }

    private int latToPixelY(double lat) {
        return (int) ((viewMaxLat - lat) * scaleY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tło
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Szary obszar
        g2d.setColor(new Color(220, 220, 220));
        g2d.fill(szaryObszarShape);
        g2d.setColor(Color.GRAY);
        g2d.draw(szaryObszarShape);

        // Jednostki
        g2d.setColor(new Color(0, 150, 0));
        for (JednostkaStrazy js : jednostki) {
            Wspolrzedne w = js.getPolozenie();
            int x = lonToPixelX(w.getDlugosc());
            int y = latToPixelY(w.getSzerokosc());

            g2d.fillOval(x - 6, y - 6, 12, 12);

            long wolneAuta = js.getAuta().stream()
                    .filter(auto -> auto.czyMozeWyjechac())
                    .count();

            String opis = js.getNazwa() + " [" + wolneAuta + "]";
            g2d.setColor(Color.BLACK);
            g2d.drawString(opis, x - 30, y - 10);

            g2d.setColor(new Color(0, 150, 0));
        }

        // Zdarzenia
        if (zdarzenia != null) {
            for (Zdarzenie z : zdarzenia) {
                if (!z.isAktywne()) continue;

                Wspolrzedne w = z.getPolozenie();
                int x = lonToPixelX(w.getDlugosc());
                int y = latToPixelY(w.getSzerokosc());

                // Wybór koloru
                g2d.setColor(z.getStrategia().getKolor());
                g2d.fillOval(x - 4, y - 4, 8, 8);

                g2d.setColor(Color.BLACK);
                g2d.drawString(String.valueOf(z.getAktywneAuta()), x, y - 5);

            }
        }
    }
}