package UI;

import Logik.Ship;
import Logik.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.stream.IntStream;

class ShipPlacement extends JPanel implements ButtonUndLabel {
    Cell[][] cells = new Cell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
    JButton[][] schiffe = new JButton[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color2 = new Color(52, 58, 60);
        Color color1 = new Color(24, 40, 72);
        GradientPaint gp = new GradientPaint(-1000, 0, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
    }

    JButton richtung;

    ShipPlacement(JPanel content, Ship ship, Spielfeld spielfeld) {
        int sumofShips = IntStream.of(ship.getAnzahlderSchiffe()).sum();

        setLayout(new BorderLayout());
        Container ships = new Container();
        ships.setLayout(new GridLayout((sumofShips + 4), 6, 5, 5));
        Container cellField = new Container();
        cellField.setLayout(new GridLayout(Spielfeld.getSpielfeldSize(), Spielfeld.getSpielfeldSize()));

        JButton startGame = new UiButton("Start Game");
        startGame.setEnabled(false);
        startGame.setBackground(Color.red);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                spielfeld.gameState = "Setzen";
                spielfeld.setPlayerTurn(true);
                spielfeld.resetKey(0);
                new ShipPlacementKi();
                spielfeld.gameState = "Battle";
                content.add(new BattleScreen(content));
                content.revalidate();
            }
        });
        ships.add(startGame);
        //initialisiert das Spielfeld
        for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                cells[row][col] = new Cell(row, col);
                cells[row][col].setActionCommand(cells[row][col].getRow() + "," + cells[row][col].getCol());
                cellField.add(cells[row][col]);
                int finalRow = row;
                int finalCol = col;
                cells[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Spielfeld.res = new ArrayList<>();
                        if (spielfeld.gameState.equals("Setzen")) {
                            for (int i = 0; i < ship.getShipList().get(0); i++) {
                                if (ship.getRichtung() == true) {
                                    Spielfeld.getSurroundings(ship.getShipFleet(), finalCol, finalRow + i);
                                    if ((finalRow + ship.getShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                                        return;
                                    }
                                    for (int c : spielfeld.getListDirection()) {
                                        if (c > 0) {
                                            return;
                                        }
                                    }
                                } else {
                                    Spielfeld.getSurroundings(ship.getShipFleet(), finalCol + i, finalRow);
                                    if ((finalCol + ship.getShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                                        return;
                                    }
                                    for (int c : spielfeld.getListDirection()) {
                                        if (c > 0) {
                                            return;
                                        }
                                    }
                                }
                            }
                            if (ship.getShipList().size() == 1) {
                                startGame.setBackground(Color.gray);
                                startGame.setEnabled(true);
                                Spielfeld.setSpielfeldSize(Spielfeld.getSpielfeldSize());
                            }
                            if (ship.getShipList().size() > 0) {
                                spielfeld.setKey(1);
                                for (int s = 0; s < ship.getFleet().get(spielfeld.getKey()); s++) {
                                    if (ship.getRichtung() == true) {
                                        spielfeld.placeShips(finalRow, finalCol, ship);
                                        cells[finalRow + s][finalCol].setBackground(shipcolor);
                                    } else {
                                        spielfeld.placeShips(finalRow, finalCol, ship);
                                        cells[finalRow][finalCol + s].setBackground(shipcolor);
                                    }
                                    if (finalRow + s < Spielfeld.getSpielfeldSize() && finalRow + 1 < 0) ;
                                }
                                ship.getShipList().remove(0);
                            }
                        }
                        return;
                    }
                });
            }
        }
        add(cellField, BorderLayout.CENTER);
        richtung = new JButton("Richtung: Horizontal");
        richtung.setContentAreaFilled(false);
        richtung.setForeground(Color.white);
        richtung.setOpaque(false);
        richtung.setBorderPainted(true);
        richtung.setFont(new Font("Serif", Font.PLAIN, 30));
        ships.add(richtung);
        richtung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ship.getRichtung() == false) {
                    ship.setRichtung(true);
                    richtung.setText("Richtung: Horizontal");
                } else {
                    ship.setRichtung(false);
                    richtung.setText("Richtung: Vertikal");
                }
            }
        });
        JLabel shipText = new UiLabel("Place the ship from top to bottom:");
        shipText.setHorizontalAlignment(JLabel.CENTER);
        ships.add(shipText);
        for (int i = 0; i < sumofShips; i++) {
            JPanel shipBox = new JPanel();
            shipBox.setLayout(new GridLayout());
            shipBox.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            ships.add(shipBox);
            for (int j = 0; j < ship.getShipList().get(i); j++) {
                schiffe[i][j] = new JButton("Ship " + (i + 1) + "/" + (j + 1));
                schiffe[i][j].setBackground(shipcolor);
                schiffe[i][j].setEnabled(false);
                shipBox.add(schiffe[i][j]);
            }
        }
        JButton backbtn = new UiButton("Go Back");
        backbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                content.removeAll();
                content.revalidate();
            }
        });
        ships.add(backbtn);
        add(ships, BorderLayout.EAST);
    }
}
