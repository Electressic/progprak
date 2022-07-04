package UI;

import Logik.Ship;
import Logik.Spielfeld;

import java.util.ArrayList;

public class ShipPlacementKi implements ButtonUndLabel{
    Spielfeld spielfeld;
    Ship ship;
    Cell[][] enemycells = new Cell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
    ShipPlacementKi() {
        for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                enemycells[row][col] = new Cell(row, col);
            }
        }
        while (ship.getEnemyShipList().size() > 0) {
            Spielfeld.res = new ArrayList<>();
            int finalRow = spielfeld.rn.nextInt(Spielfeld.getSpielfeldSize());
            int finalCol = spielfeld.rn.nextInt(Spielfeld.getSpielfeldSize());
            int randomrichtung = spielfeld.rn.nextInt(2);
            boolean isHorzon;
            if(randomrichtung ==0)
            {
                isHorzon = false;
            }
            else
            {
                isHorzon = true;
            }
            ship.setRichtung(isHorzon);
            if (spielfeld.gameState.equals("Setzen")) {
                if ((finalRow + ship.getEnemyShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                    break;
                }
                if ((finalCol + ship.getEnemyShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                    break;
                }
                System.out.println("test" + spielfeld.getKey());
                spielfeld.setKey(1);
                for (int s = 0; s < ship.getEnemyFleet().get(spielfeld.getKey()); s++) {
                    if (ship.getRichtung() == true) {
                        spielfeld.placeEnemyShips(finalRow, finalCol, ship);
                    } else {
                        spielfeld.placeEnemyShips(finalRow, finalCol, ship);
                    }
                    if (finalRow + s < Spielfeld.getSpielfeldSize() && finalRow + 1 < 0) ;
                }
                ship.getEnemyShipList().remove(0);
            }
        }
    }
}
