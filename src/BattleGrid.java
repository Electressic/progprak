package progprak.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class BattleGrid extends Container {
    JFrame grid = new JFrame();
    int maxgroesse = Spielfeld.SpielfeldSize;
    JPanel[][] cells = new JPanel[maxgroesse][maxgroesse];
    Container g = grid.getContentPane();
    JPanel ships = new JPanel();

    public BattleGrid() {
        grid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        grid.setMinimumSize(new Dimension(600 ,400));
        grid.setVisible(true);

        g.setLayout(new GridLayout(maxgroesse,maxgroesse));

        fillBoard();
        addShips();
    }

    private void fillBoard() {
        for(int i = 0; i < maxgroesse; i++) {
            for(int j = 0; j < maxgroesse; j++) {
                cells[i][j] = new JPanel();
                cells[i][j].setOpaque(true);
                cells[i][j].setBackground(Color.black);
                cells[i][j].setBorder(BorderFactory.createLineBorder(Color.blue,2));
                g.add(cells[i][j], BorderLayout.WEST);
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
