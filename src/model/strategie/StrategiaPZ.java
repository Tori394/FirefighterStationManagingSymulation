package model.strategie;

import java.awt.*;
import java.util.Random;

public class StrategiaPZ implements StrategiaZdarzenia {
    Color kolor = new Color(198, 13, 13);

    @Override
    public int getWymaganeAuta() {
        return 3;
    }

    @Override
    public String getNazwa() {
        return "POŻAR";
    }

    @Override
    public  Color getKolor() {
        return kolor;
    }

    @Override
    public void setKolor() {
        this.kolor = new Color(112, 100, 64);
    }
}