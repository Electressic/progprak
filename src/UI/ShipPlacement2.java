package progprak.src.UI;

import progprak.src.Logik.BattleShip;
import progprak.src.Logik.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class ShipPlacement2 extends Container {
    JFrame gridframe = new JFrame();
    int maxgroesse = Spielfeld.SpielfeldSize;
    int battleshipcount = BattleShip.shipCount6;
    JPanel gridBox = new JPanel();
    JPanel shipBox = new JPanel();
    Font gridFont = new Font(Font.DIALOG, Font.PLAIN, 24);
    JButton[][] cells = new JButton[maxgroesse][maxgroesse];
    JButton[][] ships = new JButton[battleshipcount][6];
    JButton[][] ship = new JButton[5][5];
    private String gameState;
    public ArrayList<String> gridbuttons = new ArrayList<String>();

    public ShipPlacement2() {
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
                cells[row][column] = new JButton(row +"" + column);
                gridbuttons.add(String.valueOf(cells[row][column]));
                cells[row][column].setFont(gridFont);
                cells[row][column].setBackground(Color.GRAY);
                gridBox.add(cells[row][column]);
                System.out.println(gridbuttons);
            }
        }
    }

    private void addShips(){
        JButton backbutton = new JButton("BACK");
        shipBox.add(backbutton);
        backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==backbutton) {
                    gridframe.dispose();
                    Creator createWindow = new Creator();
                }
            }
        });

        JLabel test1 = new JLabel("Hier liegen die Schiffe: 1");
        test1.setHorizontalAlignment(SwingConstants.CENTER);
        test1.setVerticalAlignment(SwingConstants.TOP);
        shipBox.add(test1);

        for(int row = 0; row < ships.length; row++) {
            JPanel testing = new JPanel();
            testing.setBorder(BorderFactory.createLineBorder(Color.black));
            shipBox.add(testing);
            for(int column = 0; column < ships[row].length; column++) {
                ships[row][column] = new JButton(row + "/" + column);
                ships[row][column].setFont(gridFont);
                ships[row][column].setBackground(Color.GRAY);
                testing.add(ships[row][column]);
            }
            testing.addMouseMotionListener(new MouseMotionAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        JPanel component = (JPanel) e.getComponent().getParent().getParent();
                        Point pt = new Point(SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), component));
                        testing.setLocation((int) pt.getX(), (int) pt.getY());
                    }
                }
            });
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
                    BatteloScreeno createWindow = new BatteloScreeno();
                }
            }
        });

    }
}
