package progprak.src.UI;

import progprak.src.Logik.BattleShip;
import progprak.src.Logik.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShipPlacement extends Container {
    JFrame gridframe = new JFrame();
    int maxgroesse = Spielfeld.SpielfeldSize;
    int battleshipcount = BattleShip.battleshipCount6;
    int shipSize = BattleShip.battleshipSize6;
    JPanel gridBox = new JPanel();
    JPanel shipBox = new JPanel();
    Font gridFont = new Font(Font.DIALOG, Font.PLAIN, 24);
    JButton[][] cells = new JButton[maxgroesse][maxgroesse];
    JButton[][] ships = new JButton[battleshipcount][shipSize];
    JButton[][] ship = new JButton[5][5];

    public ShipPlacement() {
        gridframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridframe.setMinimumSize(new Dimension(600 ,600));
        gridframe.setLocationRelativeTo(null);
        gridframe.add(gridBox, BorderLayout.LINE_START);
        gridframe.add(shipBox, BorderLayout.CENTER);

        gridBox.setLayout(new GridLayout(maxgroesse,maxgroesse,1,1));
        gridBox.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        gridBox.setBorder(BorderFactory.createLineBorder(Color.black));

        shipBox.setLayout(new GridLayout(10,10,1,1));
        shipBox.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        shipBox.setBorder(BorderFactory.createLineBorder(Color.black));

        makeGrid();
        addShips();

        gridframe.pack();
        gridframe.setVisible(true);
    }
    public void makeGrid(){
        for(int row = 0; row < cells.length; row++) {
            for(int column = 0; column < cells[row].length; column++) {
                cells[row][column] = new JButton("");
                cells[row][column].setFont(gridFont);
                cells[row][column].setBackground(Color.GRAY);
                gridBox.add(cells[row][column]);
            }
        }
    }
    private void addShips(){
        JLabel test1 = new JLabel("Hier liegen die Schiffe: 1");
        test1.setHorizontalAlignment(SwingConstants.CENTER);
        test1.setVerticalAlignment(SwingConstants.TOP);
        shipBox.add(test1);

        for(int row = 0; row < ships.length; row++) {
            for(int column = 0; column < ships[row].length; column++) {
                ships[row][column] = new JButton(row + "/" + column);
                ships[row][column].setFont(gridFont);
                ships[row][column].setBackground(Color.GRAY);
                shipBox.add(ships[row][column]);
            }
            JButton testing = new JButton(String.valueOf(ships));
            shipBox.add(testing);
        }

        JButton battleStart = new JButton("Start Game");
        battleStart.setHorizontalAlignment(SwingConstants.CENTER);
        battleStart.setVerticalAlignment(SwingConstants.BOTTOM);
        shipBox.add(battleStart);
        battleStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==battleStart) {
                    gridframe.dispose();
                    BattleScreen createWindow = new BattleScreen();
                }
            }
        });

    }
}
