package model.strategie;

import java.awt.*;
import java.util.Random;

public class StrategiaMZ implements StrategiaZdarzenia {
    Color kolor = new Color(223, 174, 4);

    @Override
    public int getWymaganeAuta() {
        return 2;
    }

    @Override
    public String getNazwa() {
        return "MIEJSCOWE ZAGROŻENIE";
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