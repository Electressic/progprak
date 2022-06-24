package progprak.src.Logik;

import javax.swing.*;
import java.awt.*;

public class SpielfeldBoard {
    int size= 0;
    public Spielfeld cells [][];
    JPanel buttonSpielfeld;
    int row, column;
    // private ArrayList<Ship> fleet;
    private String gameState;

    public SpielfeldBoard (int size, String gameState) {
        this.size = size;
        this.gameState = gameState;
        this.cells = new Spielfeld[size][size];
        setBoardLayout();
    }
    public void setBoardLayout(){
        setSize.setSize();
        setLayout(new GridBagLayout());
        buttonSpielfeld = new JPanel();
        buttonSpielfeld.setLayout(new GridLayout(size,size));
    }
}
