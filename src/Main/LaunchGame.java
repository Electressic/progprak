package progprak.src.Main;

import progprak.src.Logik.BattleShip;
import progprak.src.Logik.Spielfeld;
import progprak.src.UI.UI;

import javax.swing.plaf.nimbus.State;
import java.util.Scanner;


public class LaunchGame {

    public static void main(String[] args) {
        new UI();
    }

    public static void displayFeld() {
        Spielfeld feld = new Spielfeld(Spielfeld.SpielfeldSize);
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

    public static void placeShips() {
        System.out.println("Test");
        Spielfeld feld = new Spielfeld(Spielfeld.SpielfeldSize);
        Scanner myObj = new Scanner(System.in);
        //2D Objekt in Spielfeld muss zurückgegeben werden
        BattleShip[] vorhandeneSchiffe = feld.getVorhandeneSchiffe();//getMethode = Spielfeld.g
        for(int i = 0; i <feld.getAnzahlSchiffe(); i++)
        {
            System.out.println("Setze Schiff mit Groesse: "+ feld.getShip(i).getGroesse());
            //Hier muss was pasieren, dmit die Position abgefragt wird
            String userName = myObj.nextLine();  // Read user input
            String[] Koordinate = userName.split(" ");
            //feld.setzeSchiff((int)Koordinate[0], (int)Koordinate[1], true, vorhandeneSchiffe[i]);
        }

    }
}
