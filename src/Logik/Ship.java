package progprak.src.Logik;

import progprak.src.UI.UI;

import java.awt.*;
import java.util.*;

public class Ship {
    private int[] ships = {2,3,4,5,6};
    private int[] anzahlderSchiffe;
    public int[][]  shipFleet;
    protected int[][]  enemyShipFleet;

    Map<Integer, Integer> fleet = new HashMap<>();
    public Map<Integer, Integer> enemyFleet = new HashMap<>();

    ArrayList<Integer> shipList = new ArrayList<>();
    ArrayList<Integer> enemyShipList = new ArrayList<>();

    int [] storage = new int[290];
    boolean richtung;

    public Ship () {
        shipFleet = new int[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
        enemyShipFleet = new int[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
    }
    public boolean getRichtung() {
        return richtung;
    }

    public boolean setRichtung(boolean richtung) {
        this.richtung = richtung;
        return richtung;
    }

    public int[] getShips() {
        return ships;
    }
    public int[] getAnzahlderSchiffe() {
        return anzahlderSchiffe;
    }

    public int[] setAnzahlderSchiffe(int[] anzahlderSchiffe) {
        this.anzahlderSchiffe = anzahlderSchiffe;
        return anzahlderSchiffe;
    }
    public void setShipList(int shipListValue) {
        enemyShipList.add(shipListValue);
        shipList.add(shipListValue);
    }
    public ArrayList<Integer> getShipList() {
        return shipList;
    }
    public ArrayList<Integer> getEnemyShipList() {
        return enemyShipList;
    }

    public Map<Integer, Integer> getFleet() {
        return fleet;
    }

    public Map<Integer, Integer> getEnemyFleet() {
        return enemyFleet;
    }

    public int[][] getShipFleet() {
        return shipFleet;
    }

    public int[][] getEnemyShipFleet() {
        return enemyShipFleet;
    }

    public void createShips(int[] ShipCount) {
        for (int i = 0; i <= storage.length; i++) {
            for (int j = 0; j < getShipList().size(); j++) {
                getShipList().remove(j);
                getEnemyShipList().remove(j);
            }
        }
        int[] tempShipCount = ShipCount;
        int temp = 0;
        int temp2 = 0;
        for (int x= 0; x <5; x++) {
            setAnzahlderSchiffe(tempShipCount);
            int[] AnzahlSchiffe = getAnzahlderSchiffe();
            for (int y = 0; y < AnzahlSchiffe[x]; y++) {
                setShipList(x +2);
                fleet.put(++temp, x + 2);
                enemyFleet.put(++temp2, x +2);
            }
        }
    }

}
