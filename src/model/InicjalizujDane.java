package model;

import java.util.ArrayList;
import java.util.List;

public class InicjalizujDane {
    public static List<JednostkaStrazy> wgrajJednostki() {
        List<JednostkaStrazy> lista = new ArrayList<>();

        lista.add(new JednostkaStrazy("JRG-1", new Wspolrzedne(50.0599424, 19.9432412)));
        lista.add(new JednostkaStrazy("JRG-2", new Wspolrzedne(50.0335189, 19.9358363)));
        lista.add(new JednostkaStrazy("JRG-3", new Wspolrzedne(50.0755229, 19.8875822)));
        lista.add(new JednostkaStrazy("JRG-4", new Wspolrzedne(50.0375876, 20.005719)));
        lista.add(new JednostkaStrazy("JRG-5", new Wspolrzedne(50.0921839, 19.9217896)));
        lista.add(new JednostkaStrazy("JRG-6", new Wspolrzedne(50.0159353, 20.0156738)));
        lista.add(new JednostkaStrazy("JRG-7", new Wspolrzedne(50.0941205, 19.977386)));
        lista.add(new JednostkaStrazy("JRG Szkoła", new Wspolrzedne(50.0769598, 20.0338662)));
        lista.add(new JednostkaStrazy("JRG Skawina", new Wspolrzedne(49.9721807, 19.7960337)));
        lista.add(new JednostkaStrazy("LSP Balice", new Wspolrzedne(50.0782553, 19.7862538)));

        return lista;
    }
}
