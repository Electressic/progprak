package progprak.src.Logik;

import java.util.Scanner;

public class PlaceShips {
    Ship ship;
    public PlaceShips() {
        Spielfeld spielfeld = new Spielfeld(Spielfeld.SpielfeldSize);
        placeShips();
    }
    public void placeShips() {
        Spielfeld spielfeld = new Spielfeld(Spielfeld.SpielfeldSize);
        int key = 0;
        ship.setRichtung(true);
        Scanner myObj = new Scanner(System.in);
        for(int i = 1; i <ship.getAnzahlderSchiffe().length; i++)
        {
            for (int j = 1; j < ship.getAnzahlderSchiffe()[i]; j++) {
                key++;
                System.out.println("X Koordinate: ");
                int x = myObj.nextInt();  // Read user input
                System.out.println("Y Koordinate: ");
                int y = myObj.nextInt();  // Read user input
                boolean richtung = ship.setRichtung(myObj.nextBoolean());
                for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                    for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                        for (int z = 0; z < i +2; z++) {
                            if (row == x && col == y) {
                                if (ship.getRichtung() == true) {
                                    spielfeld.setZustandSpielfeld(row + z, col, key);
                                } else {
                                    spielfeld.setZustandSpielfeld(row, col + z, key);
                                }
                            }
                        }
                    }
                }
            }
        }
        spielfeld.displayFeld();
    }
}
