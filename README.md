<h1 align="center">🚒 System Symulacji Państwowej Straży Pożarnej - Kraków</h1>

<p align="center">
  Aplikacja w języku Java symulująca działanie Stanowiska Kierowania Komendanta Miejskiego (SKKM) w Krakowie. Program wizualizuje na mapie jednostki ratownicze (JRG), generuje losowe zdarzenia i automatycznie dysponuje siły i środki, uwzględniając czas dojazdu, czas akcji oraz fałszywe alarmy.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-blue.svg">
  <img src="https://img.shields.io/badge/GUI-Swing-blue.svg">
  <img src="https://img.shields.io/badge/Concurrency-Multithreading-orange.svg">
  <img src="https://img.shields.io/badge/Status-Finished-brightgreen">
</p>

---
## 📋 Funkcjonalności

* **Rzeczywista Mapa:** Odzwierciedlenie koordynatów geograficznych krakowskich jednostek (JRG 1-7, Balice, Szkoła Aspirantów, Skawina).
* **Wizualizacja w czasie rzeczywistym:**
    * Zielone punkty: Jednostki Straży (z licznikiem dostępnych wozów).
    * Czerwone punkty: Pożary.
    * Pomarańczowe punkty: Miejscowe Zagrożenia.
* **Symulacja cyklu życia zgłoszenia:**
    1.  Generowanie zdarzenia w losowym czasie i lokalizacji.
    2.  Dysponowanie pojazdów przez SKKM (najbliższe wolne jednostki).
    3.  Dojazd na miejsce (symulowany czas).
    4.  Obsługa zdarzenia lub powrót (w przypadku fałszywego alarmu).
    5.  Powrót do bazy i przywrócenie gotowości bojowej.
* **Wielowątkowość:** Niezależne wątki dla generatora zdarzeń, renderowania mapy (GUI) oraz każdego samochodu bojowego.

## 🛠️ Zastosowane Wzorce Projektowe

Projekt został zrealizowany z naciskiem na architekturę oprogramowania i wzorce projektowe (GoF):

1.  **MVC (Model-View-Controller):** Wyraźny podział na logikę (paczka `model`), sterowanie (`controller`) i warstwę prezentacji (`view`).
2.  **Obserwator (Observer):**
    * `GeneratorZdarzen` (Subject) powiadamia `SKKM` oraz `Mapę` o nowych zagrożeniach.
    * Luźne powiązania między komponentami.
3.  **Strategia (Strategy):**
    * Interfejs `StrategiaZdarzenia` pozwala na różną obsługę typów zdarzeń.
    * `StrategiaPozar`: wymaga 3 aut, dłuższy czas akcji.
    * `StrategiaMZ`: wymaga 2 aut, krótszy czas akcji.
4.  **Stan (State):**
    * Klasa `Auto` posiada stan (`StanWolny`, `StanZajety`), który determinuje, czy pojazd może zostać zadysponowany.
5.  **Iterator:**
    * Używany w `SKKM` do iterowania po liście jednostek posortowanej według odległości od zdarzenia (od najbliższej do najdalszej).

## 📂 Struktura Projektu

```text
src/
├── controller/
│   ├── GeneratorZdarzen.java   # Wątek generujący zagrożenia
│   └── SKKM.java               # Mózg operacji (dysponowanie)
├── model/
│   ├── Auto.java               # Wątek pojedynczego samochodu
│   ├── JednostkaStrazy.java    # Kontener dla aut i lokalizacji
│   ├── StanPojazdu.java        # Interfejs Stanu
│   ├── StrategiaZdarzenia.java # Interfejs Strategii
│   ├── Wspolrzedne.java        # Obliczenia geograficzne (dystans)
│   ├── Zdarzenie.java          # Reprezentacja pożaru/zagrożenia
│   └── ... (Inne klasy modelu)
├── view/
│   └── Mapa.java               # Rysowanie (JPanel, Graphics2D)
└── Main.java                   # Punkt startowy, konfiguracja wątków
```
## 💻 Podgląd UI
<img width="1523" height="604" alt="lab4" src="https://github.com/user-attachments/assets/640a1151-66b1-4e6e-a5ea-d38dd86f770c" />
