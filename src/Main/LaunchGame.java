package progprak.src.Main;

import progprak.src.Logik.Spielfeld;

import javax.swing.plaf.nimbus.State;


public class LaunchGame {

    public static void main(String[] args) {
        displayFeld();
    }

    public static void displayFeld() {
        Spielfeld feld = new Spielfeld(15);
        System.out.print("     ");
        for (int row = 0; row < feld.getSpielfeldSize(); row++) {
            if(row < 10)
            {
                System.out.print(" 0" + row);
            }
            else
                System.out.print(" " + row);
        }
        System.out.println(" ");
        for (int col = 0; col < feld.getSpielfeldSize(); col++) {
            if(col < 10)
            {
                System.out.print(" 0" + col + " |");
            }
            else
                System.out.print(" " + col + " |");
            for (int row = 0; row < feld.getSpielfeldSize(); row++) {
                feld.setzeZustand(row, col, "Wasser");
                System.out.print(feld.getZustandPos(row,col));
            }
            System.out.println();
        }
    }
    public void placeShips() {

    }
}
