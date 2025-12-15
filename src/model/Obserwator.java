package model;

import model.zdarzenia.Zdarzenie;

public interface Obserwator {
    void naNoweZdarzenie(Zdarzenie zdarzenie);
}