package progprak.src.UI;

import progprak.src.Logik.Spielfeld;

import javax.swing.*;
import java.awt.*;

public class ShipPlacement extends Container {
    JFrame grid = new JFrame();
    int maxgroesse = Spielfeld.SpielfeldSize;
    Container g = grid.getContentPane();
    JPanel ships = new JPanel();
    JPanel buttonLayout;

    public ShipPlacement() {
        grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid.setMinimumSize(new Dimension(600 ,600));
        grid.setVisible(true);
        buttonLayout = new JPanel();
        buttonLayout.setLayout(new GridLayout(maxgroesse, maxgroesse));

        g.setLayout(new GridLayout(maxgroesse,maxgroesse));
        addShips();
    }
    private void addShips(){

        JLabel test = new JLabel("Hier liegen die Schiffe: ");
        test.setHorizontalAlignment(SwingConstants.RIGHT);
        test.setVerticalAlignment(SwingConstants.TOP);
        ships.add(test, BorderLayout.EAST);

    }
}
