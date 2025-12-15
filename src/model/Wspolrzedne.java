package model;

public class Wspolrzedne {
    private final double szerokosc; // Latitude
    private final double dlugosc;   // Longitude

    public Wspolrzedne(double szerokosc, double dlugosc) {
        this.szerokosc = szerokosc;
        this.dlugosc = dlugosc;
    }

    public double getSzerokosc() {
        return szerokosc;
    }

    public double getDlugosc() {
        return dlugosc;
    }

    // funkcja do mierzenia realnej odległości (w km) ze wzoru haversine
    public double obliczDystansKm(Wspolrzedne inny) {
        final int R = 6371; // Promień Ziemi w km
        double latDist = Math.toRadians(inny.szerokosc - this.szerokosc);
        double lonDist = Math.toRadians(inny.dlugosc - this.dlugosc);

        double a = Math.sin(latDist / 2) * Math.sin(latDist / 2)
                + Math.cos(Math.toRadians(this.szerokosc)) * Math.cos(Math.toRadians(inny.szerokosc))
                * Math.sin(lonDist / 2) * Math.sin(lonDist / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}