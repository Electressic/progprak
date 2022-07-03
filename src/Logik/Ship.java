package progprak.src.Logik;

import progprak.src.UI.UI;

import java.awt.*;
import java.util.*;

public class Ship {
    private int[] ships = {2,3,4,5,6};
    private int[] anzahlderSchiffe;
    protected Image shipPieceAlive;
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

    /*
        bei UI brauch ich Gamestate entweder setzen oder battle

        bei Button Click ruft Funktion auf:
        if gamestate == setzen =>
        ruft placeships auf
        set Button zum starten true
        ruft placeships für enemy auf

        if gamestate == battle =>
        ruft das untere alles auf

        if isPlayerTurn =>
        (shoot();
        isShipDead();
        habeVerloren();
        setPlayerTurn false
        )

        else =>
        (shoot();
        isShipDead();
        habeVerloren();
        setPlayerTurn true
        )


        Funktion in der Klasse hier:
        public boolean isShipDead() {
        // row col pack ich in die funktion rein wie bei shoot
        if ((ship.Fleet.get(ship.getShipFleet()[row][col]) == 0) {
            ship.Fleet.remove(ship.getShipFleet()[row][col]);
            return true;
        }
            return false;
        }

        public boolean habeVerloren()
        {
            if(ship.Fleet.size() == 0)
            {
                return true;
            }
            UI schließt irgendwie oder so
            return false;
        }
        */
    public boolean isShipDead(int x, int y) {
        if ((fleet.get(getShipFleet()[x][y]) == 0)) {
            fleet.remove(getShipFleet()[x][y]);
            return true;
        }
        return false;
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
