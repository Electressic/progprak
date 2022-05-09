import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class framework 
{

    int size;
    /*w�rde eine Klasse erschaffen die Battleship heißt die als hauptklasse agiert und alle daten, runden, schiffe als methoden drin hat oder so */
    /*je nachdem sind in meinem kopf folgende klassen: Grid für einen selber, Grid für Gegner
    PlayerScreen, Battleship als Hauptklasse, weiß nicht eventuell Status für das Game? also Anfang zum auswählen, mitte während spielen und ende für endscreen
    Coordinates und Schiffdaten dann noch..

     */
    BattleShip battleShip;
    boolean anfangPlayer1 = true;
    boolean anfangPlayer2 = true;
    JLabel eigenesSchiffversunken;
    JLabel schiffAnfang;
    JLabel gegnerSchiffversunken;
    JFrame frame = new JFrame("Battleship");

    public framework(String name, int size, BattleShip battleShip) 
    {
        //super(name);
        this.size = size;
        this.battleShip = battleShip;

        Box verticalBox = Box.createVerticalBox();

        /* UI Elemente für wer dran ist und wieviele Schiffe weg sind*/
        Box horizontalBox0 = Box.createHorizontalBox();
        horizontalBox0.add(new JLabel("Status: "));
        verticalBox.add(horizontalBox0, BorderLayout.WEST);

        Box horizontalBox1 = Box.createHorizontalBox();
        horizontalBox1.add(new JLabel("Own ships: "));
        schiffAnfang = new JLabel("" + Integer.toString(size));
        horizontalBox1.add(schiffAnfang);
        verticalBox.add(horizontalBox1, BorderLayout.SOUTH);

        Box horizontalBox2 = Box.createHorizontalBox();
        horizontalBox2.add(new JLabel("Own ships sunk: "));
        eigenesSchiffversunken = new JLabel("" + Integer.toString(size));
        horizontalBox2.add(eigenesSchiffversunken);
        verticalBox.add(horizontalBox2, BorderLayout.EAST);

        Box horizontalBox3 = Box.createHorizontalBox();
        horizontalBox3.add(new JLabel("Enemy's ships sunk: "));
        gegnerSchiffversunken = new JLabel("" + Integer.toString(size));
        horizontalBox3.add(gegnerSchiffversunken);
        verticalBox.add(horizontalBox3);

        AbstractButton next;
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (name.equals("Player1")) {
                	//Welche Gruesse? Wie soll das angezeigt werden? Welche Daten sollen gesammelt werden?
                    size = battleShip.getPlayer1Data().getFleet().size();
                    if (anfangPlayer1) 
                    {
                    	//KLasse BattleShip.setStartposition
                        shipBeginning.setText(Integer.toString(size));
                        anfangPlayer1 = false;
                    }
                    if (!anfangPlayer1) {
                        battleShip.player1Turn();
                    }
                    hideScreen();
                  //Welche Gruesse? Wie soll das angezeigt werden? Welche Daten sollen gesammelt werden?
                    battleShip.getPlayer2().showScreen();
                }
                if (name.equals("Player2")) {
                	//getFleet ist in Spielfeldimpl
                    size = battleShip.getPlayer2Data().getFleet().size();
                    if (anfangPlayer2) 
                    {
                    	//KLasse BattleShip.setStartposition
                        shipBeginning.setText(Integer.toString(size));
                        anfangPlayer2 = false;
                    }
                    if (!anfangPlayer2) {
                        battleShip.player2turn();
                    }
                    hideScreen();
                    battleShip.getPlayer1().showScreen();
                }
            }
        });
        this.add(next, BorderLayout.CENTER);
        this.add(verticalBox, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(show);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void showScreen() {
        this.setVisible(true);
    }

    public void hideScreen() {

        this.setVisible(false);
    }
    /* je nachdem wie wir das strukturieren irgendwie so? kann das später noch erklären aber sind halt getter */
    public SelfGrid getSelfGrid() {

        for (Component child : this.getContentPane().getComponents()) {

            if (child instanceof SelfGrid) {
                return (SelfGrid) child;
            }
        }
        return null;
    }

    public AttackGrid getAttackGrid() {
        for (Component child : this.getContentPane().getComponents()) {
            if (child instanceof AttackGrid) {
                return (AttackGrid) child;
            }

        }
        return null;
    }

    public JButton getNextButton() {
        for (Component child : this.getContentPane().getComponents()) 
        {
            if (child instanceof JButton) {
                return (JButton) child;
            }

        }
        return null;
    }

    public boolean getIsbeginningOfTheGameOfPlayer2() 
    {
        return isbeginningOfTheGameOfPlayer2;
    }
}
