package UI;

import Logik.Ship;
import Logik.Spielfeld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class BattleScreen extends JPanel implements ButtonUndLabel{
    Spielfeld spielfeld;
    Ship ship;
    ShipPlacement shipPlacement;
    ShipPlacementKi shipPlacementKi;

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth(), h = getHeight();
        Color color2 = new Color(52,58,60);
        Color color1 = new Color(24,40,72);
        GradientPaint gp = new GradientPaint(-1000,0,color1,w,h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0,0,w,h);
    }
    BattleScreen(JPanel content) {
        Cell[][] playercells = shipPlacement.cells;
        Cell[][] enemycells = shipPlacementKi.enemycells;
        setLayout(new BorderLayout());
        Container midContainer = new Container();
        midContainer.setLayout(new GridLayout(1,3,10,1));
        add(midContainer, BorderLayout.CENTER);
        Container topContainer = new Container();
        topContainer.setLayout(new GridLayout(1,4,5,1));
        add(topContainer, BorderLayout.PAGE_START);
        Container botContainer = new Container();
        botContainer.setLayout(new GridLayout(1,4,5,1));
        add(botContainer, BorderLayout.PAGE_END);


        Container player1Panel = new Container();
        player1Panel.setLayout(new GridLayout(Spielfeld.SpielfeldSize,Spielfeld.SpielfeldSize));

        Container player2Panel = new Container();
        player2Panel.setLayout(new GridLayout(Spielfeld.getSpielfeldSize(),Spielfeld.getSpielfeldSize()));

        for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                player1Panel.add(playercells[col][row]);
                int finalI = col;
                int finalJ = row;
                playercells[col][row].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(spielfeld.gameState.equals("Battle")) {
                            if(spielfeld.getPlayerTurn() == false) {
                                if(spielfeld.getString().equals("miss")) {
                                    playercells[finalI][finalJ].setBackground(misscolor);
                                    playercells[finalI][finalJ].setEnabled(false);
                                } else if(spielfeld.getString().equals("shiphit")) {
                                    playercells[finalI][finalJ].setBackground(hitcolor);
                                    playercells[finalI][finalJ].setEnabled(false);
                                } else if (spielfeld.getString().equals("shipsunk")) {
                                    for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                                        for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                                            if (ship.getShipFleet()[row][col] == 0) {
                                                playercells[row][col].setBackground(sunkcolor);
                                                playercells[row][col].setEnabled(false);
                                            }
                                        }
                                    }
                                }
                                spielfeld.setPlayerTurn(true);
                            }
                        }
                    }
                });
            }
        }
        midContainer.add(player1Panel);

        JLabel whoseTurn = new UiLabel("Player1 : " + spielfeld.getPlayerTurn());
        whoseTurn.setHorizontalAlignment(SwingConstants.CENTER);
        topContainer.add(whoseTurn);

        for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                player2Panel.add(enemycells[row][col]);
                enemycells[row][col].setBackground(watercolor);
                int finalI = col;
                final int[] key = new int[1];
                int finalJ = row;
                enemycells[col][row].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(spielfeld.gameState.equals("Battle")) {
                            if(spielfeld.getPlayerTurn() == true) {
                                key[0] = ship.getShipFleet()[finalI][finalJ];
                                spielfeld.shoot(finalJ, finalI, ship);
                                if(spielfeld.getString().equals("miss")) {
                                    enemycells[finalI][finalJ].setBackground(misscolor);
                                    enemycells[finalI][finalJ].setEnabled(false);
                                } else if(spielfeld.getString().equals("shiphit")) {
                                    enemycells[finalI][finalJ].setBackground(hitcolor);
                                    enemycells[finalI][finalJ].setEnabled(false);
                                } else if (spielfeld.getString().equals("shipsunk")) {
                                    for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                                        for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                                            if (ship.getShipFleet()[row][col] == key[0]) {
                                                enemycells[row][col].setBackground(sunkcolor);
                                                enemycells[row][col].setEnabled(false);
                                            }
                                        }
                                    }
                                    if(gameWon(content))
                                    {
                                        return;
                                    }
                                }
                                if (spielfeld.getString().equals("shiphit") || spielfeld.getString().equals("shipsunk")) {
                                    spielfeld.setPlayerTurn(true);
                                } else {
                                    spielfeld.setPlayerTurn(false);
                                }
                                int x = spielfeld.rn.nextInt(spielfeld.getSpielfeldSize()) ;
                                int y = spielfeld.rn.nextInt(spielfeld.getSpielfeldSize());
                                System.out.println("x/y" + x + "/" + y);
                                if (spielfeld.getPlayerTurn() == false) {
                                    playercells[x][y].doClick();
                                    spielfeld.aiShoot(x,y, ship);
                                    //if (spielfeld.getString().equals("shiphit") || spielfeld.getString().equals("shipsunk")) {
                                    //    spielfeld.setPlayerTurn(false);
                                    //} else {
                                    //    spielfeld.setPlayerTurn(true);
                                    //}
                                }
                            }
                        }
                    }
                });
            }
        }
        midContainer.add(player2Panel);

        JButton save = new UiButton("SAVE");
        save.setHorizontalAlignment(SwingConstants.CENTER);
        botContainer.add(save);
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveFile = new JFileChooser();
                int returnval = saveFile.showSaveDialog(content);
                String suffix = ".txt";
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    File file = saveFile.getSelectedFile();
                    if (file == null) {
                        return;
                    }
                    if(!saveFile.getSelectedFile().getAbsolutePath().endsWith(suffix)) {
                        file = new File(saveFile.getSelectedFile() + suffix);
                    }
                    try {
                        file.createNewFile();
                        spielfeld.speichern(file.getName(), ship);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JButton giveUp = new UiButton("GIVE UP");
        giveUp.setHorizontalAlignment(SwingConstants.CENTER);
        botContainer.add(giveUp);
        giveUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "YOU LOST");
                content.removeAll();
                content.add(new MainMenu(content, ship, spielfeld));
                content.revalidate();
            }
        });
        JLabel player1text = new UiLabel("PLAYER 1");
        player1text.setHorizontalAlignment(SwingConstants.CENTER);
        topContainer.add(player1text);

        JLabel fight = new UiLabel("Click on the Field to shoot a Ship.");
        fight.setHorizontalAlignment(SwingConstants.CENTER);
        topContainer.add(fight);

        JLabel player2text = new UiLabel("PLAYER 2");
        player2text.setHorizontalAlignment(SwingConstants.CENTER);
        topContainer.add(player2text);
    }
    boolean gameWon(JPanel content)
    {
        for (Integer Entry : ship.getEnemyFleet().keySet()) {
            if( ship.getEnemyFleet().get(Entry) != 0)
            {
                return false;
            }
        }
        int n = JOptionPane.showConfirmDialog(null, "You Won!", "test",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
        if (n == JOptionPane.OK_OPTION) {
            content.removeAll();
            content.add(new MainMenu(content, ship, spielfeld));
            content.revalidate();
        }
        System.out.println("ende");
        return true;
    }
}