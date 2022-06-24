package progprak.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BattleGrid extends Container {
    JFrame grid = new JFrame();
    int maxgroesse = Spielfeld.SpielfeldSize;
    public JPanel cells[][];
    Container g = grid.getContentPane();
    JPanel ships = new JPanel();
    JPanel buttonLayout;

    public BattleGrid() {
        grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid.setMinimumSize(new Dimension(600 ,600));
        grid.setVisible(true);
        buttonLayout = new JPanel();
        buttonLayout.setLayout(new GridLayout(maxgroesse, maxgroesse));

        g.setLayout(new GridLayout(maxgroesse,maxgroesse));

        fillBoard();
        addShips();
    }

    private void fillBoard() {
        for(int row = 0; row < maxgroesse; row++) {
            for(int column = 0; column < maxgroesse; column++) {
                cells[row][column] = new JPanel();
                cells[row][column].setOpaque(true);
                cells[row][column].setBackground(Color.black);
                cells[row][column].setBorder(BorderFactory.createLineBorder(Color.GRAY,2));
                g.add(cells[row][column], BorderLayout.WEST);
            }
        }
    }
    private void addShips(){

        JLabel test = new JLabel("Hier liegen die Schiffe: ");
        test.setHorizontalAlignment(SwingConstants.RIGHT);
        test.setVerticalAlignment(SwingConstants.TOP);
        ships.add(test, BorderLayout.EAST);

    }
}
