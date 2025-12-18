<h1 align="center">🚒 State Fire Service Simulation System - Kraków</h1>

<p align="center">
  A Java application simulating the operations of the City Headquarters Command Centre (SKKM) in Kraków. The program visualizes rescue units (JRG) on a map, generates random incidents, and automatically dispatches resources, taking into account travel time, action duration, and false alarms.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Language-Java-blue.svg">
  <img src="https://img.shields.io/badge/GUI-Swing-blue.svg">
  <img src="https://img.shields.io/badge/Concurrency-Multithreading-orange.svg">
  <img src="https://img.shields.io/badge/Status-Finished-brightgreen">
</p>

---
## 📋 Features

* **Real-world Map:** Accurate representation of geographical coordinates for Kraków rescue units (JRG 1-7, Balice, Aspirants School, Skawina).
* **Real-time Visualization:**
    * Green dots: Fire Stations (with a live counter of available trucks).
    * Red dots: Fires.
    * Orange dots: Local Threats.
* **Incident Lifecycle Simulation:**
    1.  Incident generation at a random time and location.
    2.  Vehicle dispatching by SKKM (nearest available units).
    3.  Travel to the site (simulated time).
    4.  Handling the incident or returning (in case of a false alarm).
    5.  Return to base and restoration of combat readiness.
* **Multithreading:** Independent threads for the incident generator, map rendering (GUI), and every individual fire truck.

## 🛠️ Design Patterns Applied

The project was developed with a strong emphasis on software architecture and **Gang of Four (GoF)** design patterns:

1.  **MVC (Model-View-Controller):** Clear separation between business logic (`model`), control logic (`controller`), and the presentation layer (`view`).
2.  **Observer:**
    * `GeneratorZdarzen` (Subject) notifies `SKKM` and `Map` about new threats.
    * Ensures loose coupling between components.
3.  **Strategy:**
    * `StrategiaZdarzenia` interface allows for different handling of incident types.
    * `StrategiaPozar`: Requires 3 cars, longer action duration.
    * `StrategiaMZ`: Requires 2 cars, shorter action duration.
4.  **State:**
    * The `Auto` class holds a state (`StanWolny`, `StanZajety`), which determines if the vehicle can be dispatched.
5.  **Iterator:**
    * Used in `SKKM` to iterate through the list of units sorted by distance from the incident (from nearest to furthest).

## 💻UI Preview
<img width="1523" height="604" alt="lab4" src="https://github.com/user-attachments/assets/640a1151-66b1-4e6e-a5ea-d38dd86f770c" />
