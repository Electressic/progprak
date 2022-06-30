package progprak.src.UI;

import progprak.src.Logik.PlaceShips;
import progprak.src.Logik.Ship;
import progprak.src.Logik.Spielfeld;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

public class UI {
    // Define new Colors
    Color watercolor = new Color(212,241,249);
    Color shipcolor = new Color(78, 78, 76);
    Color hitcolor = new Color (139,0,0);
    Color misscolor = new Color(19, 23, 128);

    // Panel for adding Content
    JPanel content;

    // int to save shipSum, percentage relative to Board and StartButton for Actionevent
    int shipSum;
    int percentage;
    JButton createStart;
    //create new MainFrame
    JFrame main = new JFrame("TEST");

    //create Spielfeld and Ship Class for ui
    Spielfeld spielfeld = new Spielfeld(Spielfeld.SpielfeldSize);
    int[] SpinnerArr;

    int shipValue2,shipValue3,shipValue4,shipValue5,shipValue6;

    // make 2D-Array for the Board
    Cell[][] cells = new Cell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];

    public Cell[][] getCells() {
        return cells;
    }

    public Cell[][] setCells(int x, int y) {
        Cell[][] cells = new Cell[x][y];
        return cells;
    }

    // Gamestate Schiffe setzen oder Battle
    String gameState;
    //which turn it is
    boolean isPlayerTurn;
    //check if Mp or Computer
    String enemy;

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    public Cell[][] getBoard() {
        return cells;
    }
    //Constructor that creates the UI
    public UI() {
        createGUI();
    }
    //creating UI
    private void createGUI() {
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setPreferredSize(new Dimension(800,700));
        JButton test = new JButton();
        test.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        content = new JPanel(new BorderLayout());
        content.add(new MainMenu());
        main.add(content);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }
    //makes a Custom Button with a certain Look
    public static class UiButton extends JButton {
        public UiButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setForeground(Color.white);
            setOpaque(false);
            setFocusable(false);
            setBorderPainted(true);
            setBorder(null);
            setFont(new Font("Serif", Font.PLAIN, 30));
        }
    }
    //makes a Custom Label with a certain Look
    public static class UiLabel extends JLabel {
        public UiLabel(String text) {
            super(text);
            setForeground(Color.white);
            setFont(new Font("Serif", Font.PLAIN,20).deriveFont(20.0f));
        }
    }
    //creates the MainMenu
    class MainMenu extends JPanel{
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
        MainMenu() {
            setLayout(new GridLayout(4,1,20,50));
            JLabel welcome = new JLabel("BATTLESHIP");
            welcome.setForeground(Color.white);
            welcome.setFont(new Font("Serif", Font.PLAIN, 50));
            welcome.setHorizontalAlignment(JLabel.CENTER);
            add(welcome);

            JButton startbtn = new UiButton("Start");
            startbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    content.add(new BoardCreator());
                    content.revalidate();
                    spielfeld = new Spielfeld(Spielfeld.SpielfeldSize);
                    spielfeld.ship = new Ship();
                }
            });

            JButton mpbtn = new UiButton("Multiplayer");
            JButton aibtn = new UiButton("Computer gegen Computer");

            add(startbtn);
            add(mpbtn);
            add(aibtn);
        }
    }
    //creates the BoardCreator for setting BoardSize and ShipCount
    class BoardCreator extends JPanel {
        // counts all the Ships + gets them
        public int setShipSum() {
            shipSum = 0;
            SpinnerArr = new int[]{ shipValue2,shipValue3,shipValue4,shipValue5,shipValue6};
            spielfeld.ship.createShips(SpinnerArr);
            //ship.createShips(SpinnerArr);
            for (int i = 0; i <spielfeld.ship.getShipList().size(); i++) {
                shipSum += spielfeld.ship.getShipList().get(i);
            }
            return shipSum;
        }

        public int getShipSum() {
            return shipSum;
        }
        // sets the Percentage relative to the Board
        public int setPercentage() {
            percentage = getShipSum() * 100 /(Spielfeld.getSpielfeldSize() * Spielfeld.getSpielfeldSize());
            return percentage;
        }
        public int getPercentage() {
            return percentage;
        }
        public int[] getShipCounting() {
            int[] temp;
            temp = spielfeld.ship.getAnzahlderSchiffe();
            return temp;
        }
        // if ShipSum is exactly 30%
        public void isFitting() {
            if (getPercentage() == 30) {
                createStart.setEnabled(true);
                createStart.setContentAreaFilled(false);
                createStart.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        content.removeAll();
                        content.add(new ShipPlacementPlayer1());
                        content.revalidate();
                        spielfeld.initFeld();
                        spielfeld.initEnemyFeld();
                        spielfeld.displayFeld();
                        spielfeld.displayEnemyFeld();
                        gameState = "Setzen";
                    }
                });
            } else {
                createStart.setEnabled(false);
                createStart.setBackground(Color.red);
            }
        }
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
        // Values für JSlider
        JSlider boardSize;
        static final int gridmin = 5;
        static final int gridmax = 30;
        static final int gridinit = 15;
        JLabel boardSizeText;
        //Variable für JSpinner
        // JSpinner ship2,ship3,ship4,ship5,ship6;
        JLabel prozent;
        //Variable für JLabel/Spinner
        JLabel ship2Text,ship3Text,ship4Text,ship5Text,ship6Text;
        GridBagConstraints cLayout;

        BoardCreator() {
            setBackground(Color.black);
            setLayout(new GridBagLayout());
            cLayout = new GridBagConstraints();
            cLayout.weightx = 1;
            cLayout.weighty = 1;
            cLayout.fill = GridBagConstraints.HORIZONTAL;
            cLayout.insets = new Insets(2,2,2,2);

            createStart = new JButton("Start");
            createStart.setFont(new Font("Serif", Font.PLAIN, 30));
            createStart.setEnabled(false);
            createStart.setForeground(Color.white);
            createStart.setBackground(Color.red);
            cLayout.gridx = 3;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(createStart, cLayout);
            createStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    isFitting();
                    shipSum = 0;
                    percentage = 0;
                    shipValue2 = 0;
                    shipValue3 = 0;
                    shipValue4 = 0;
                    shipValue5 = 0;
                    shipValue6 = 0;

                }
            });

            JButton load = new UiButton("Load");
            cLayout.gridx = 2;
            cLayout.gridy = 0;
            cLayout.gridwidth = 1;
            add(load, cLayout);

            JButton goback = new UiButton("Back");
            cLayout.gridx = 3;
            cLayout.gridy = 0;
            cLayout.gridwidth = 1;
            add(goback, cLayout);
            goback.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    content.add(new MainMenu());
                    content.revalidate();
                }
            });

            JLabel inputText = new UiLabel("Lege die Spielfeldsize und Anzahl Schiffe fest: ");
            cLayout.gridx = 0;
            cLayout.gridy = 1;
            cLayout.gridwidth = 3;
            add(inputText, cLayout);

            boardSizeText = new UiLabel("Spielfeldgroesse: " + Spielfeld.getSpielfeldSize());
            cLayout.gridx = 0;
            cLayout.gridy = 2;
            cLayout.gridwidth = 3;
            add(boardSizeText, cLayout);

            prozent = new JLabel("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
            prozent.setEnabled(false);
            prozent.setFont(new Font("serif", Font.PLAIN,25));
            prozent.setForeground(Color.white);
            cLayout.gridx = 2;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(prozent, cLayout);

            ship2Text = new UiLabel("Anzahl der Schipsize2: " + 0);
            cLayout.gridx = 0;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            add(ship2Text, cLayout);

            JSpinner ship2 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            ship2.setForeground(Color.white);
            ship2.setOpaque(false);
            ship2.setFocusable(false);
            cLayout.gridx = 1;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            ship2.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ship2Text.setText("Shipsize2: " + ship2.getValue());
                    shipValue2= (int)(ship2.getValue());
                    setShipSum();
                    setPercentage();
                    isFitting();
                    prozent.setText("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
                }
            });
            add(ship2, cLayout);

            ship3Text = new UiLabel("Anzahl der Schipsize3: " + 0);
            cLayout.gridx = 0;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship3Text, cLayout);

            JSpinner ship3 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 1;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship3, cLayout);
            ship3.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ship3Text.setText("Shipsize3: " + ship3.getValue());
                    shipValue3= (int)(ship3.getValue());
                    setShipSum();
                    setPercentage();
                    isFitting();
                    prozent.setText("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
                }
            });

            ship4Text = new UiLabel("Anzahl der Schipsize4: " + 0);
            cLayout.gridx = 0;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(ship4Text, cLayout);

            JSpinner ship4 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 1;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(ship4, cLayout);
            ship4.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ship4Text.setText("Shipsize4: " + ship4.getValue());
                    shipValue4= (int)(ship4.getValue());
                    setShipSum();
                    setPercentage();
                    isFitting();
                    prozent.setText("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
                }
            });

            ship5Text = new UiLabel("Anzahl der Schipsize5: " + 0);
            cLayout.gridx = 2;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            add(ship5Text, cLayout);

            JSpinner ship5 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 3;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            add(ship5, cLayout);
            ship5.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ship5Text.setText("Shipsize5: " + ship5.getValue());
                    shipValue5= (int)(ship5.getValue());
                    setShipSum();
                    setPercentage();
                    isFitting();
                    prozent.setText("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
                }
            });

            ship6Text = new UiLabel("Anzahl der Schipsize6: " + 0);
            cLayout.gridx = 2;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship6Text, cLayout);

            JSpinner ship6 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 3;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship6, cLayout);
            ship6.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    ship6Text.setText("Shipsize6: " + ship6.getValue());
                    shipValue6= (int)(ship6.getValue());
                    setShipSum();
                    setPercentage();
                    isFitting();
                    prozent.setText("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
                }
            });

            boardSize = new JSlider(JSlider.HORIZONTAL,gridmin,gridmax,gridinit);
            boardSize.setBackground(Color.black);
            boardSize.setForeground(Color.white);
            boardSize.setOpaque(false);
            boardSize.setFocusable(false);
            boardSize.setBorder(null);
            cLayout.gridx = 0;
            cLayout.gridy = 3;
            cLayout.gridwidth = 3;
            add(boardSize, cLayout);
            boardSize.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    Spielfeld.setSpielfeldSize(boardSize.getValue());
                    setPercentage();
                    isFitting();
                    prozent.setText("<html><body>" + "Total Ships" + getShipSum() + "<br>" + "Percentage erreicht: " + getPercentage() + "%" + "<html><body>");
                    boardSizeText.setText("Spielfeldgroesse: " + boardSize.getValue());
                    // Logik für Schiffgröße 2 und 6. Disabled und Enabled sie.
                    if (boardSize.getValue() >= 20) {
                        ship2.setEnabled(false);
                        ship2.setValue(0);
                        shipValue2 = 0;
                        ship6.setEnabled(true);
                    }
                    else {
                        ship2.setEnabled(true);
                        ship6.setEnabled(false);
                        ship6.setValue(0);
                        shipValue6 = 0;
                    }
                }
            });
            boardSize.setMajorTickSpacing(5);
            boardSize.setMinorTickSpacing(1);
            boardSize.setPaintLabels(true);
            boardSize.setPaintTicks(true);
        }
    }
    class ShipPlacementPlayer1 extends JPanel {
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
        JButton richtung;
        Action actOne = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actTwo);
                richtung.setText("Horizontal");
                spielfeld.ship.setRichtung(true);
            }
        };
        Action actTwo = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actOne);
                richtung.setText("  Vertikal  ");
                spielfeld.ship.setRichtung(false);
            }
        };
        ShipPlacementPlayer1() {
            setLayout(new BorderLayout());
            ShipCell[][] shipcells = new ShipCell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
            Container ships = new Container();
            ships.setLayout(new GridLayout(10,10,1,1));
            Container cellField = new Container();
            cellField.setLayout(new GridLayout(Spielfeld.getSpielfeldSize(),Spielfeld.getSpielfeldSize()));

            JButton startGame = new UiButton("Start Game");
            startGame.setEnabled(false);
            startGame.setBackground(Color.red);
            startGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    gameState = "Battle";
                    isPlayerTurn = true;
                    //if (enemy.equals("Player")) {
                    //    content.add(new ShipPlacementPlayer2());
                    //} else {
                        content.add(new BattleScreen());
                        content.revalidate();
                    //}
                }
            });
            ships.add(startGame);
            //initialisiert das Spielfeld
            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                    cells[row][col] = new Cell(row, col);
                    cells[row][col].setActionCommand(cells[row][col].getRow() + "," + cells[row][col].getCol());
                    cellField.add(cells[row][col]);
                    int finalRow = row;
                    int finalCol = col;
                    cells[row][col].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (gameState.equals("Setzen")) {
                                if (spielfeld.ship.getShipList().size() > 0) {
                                    spielfeld.setKey(1);
                                    spielfeld.placeShips(finalCol,finalRow);
                                    for (int i = 0; i <Spielfeld.getSpielfeldSize(); i++) {
                                        for (int j = 0; j <Spielfeld.getSpielfeldSize(); j++) {
                                            if (spielfeld.getZustandSpielfeld(i, j) != -1) {
                                                cells[j][i].setBackground(shipcolor);
                                                cells[j][i].setStatusColor(shipcolor);
                                            }
                                        }
                                    } spielfeld.ship.getShipList().remove(0);
                                } else {
                                    startGame.setBackground(Color.gray);
                                    startGame.setEnabled(true);
                                    Spielfeld.setSpielfeldSize(Spielfeld.getSpielfeldSize());
                                }
                            }
                        }
                    });
                }
            }
            add(cellField,BorderLayout.CENTER);
            richtung = new JButton(actTwo);
            richtung.setContentAreaFilled(false);
            richtung.setForeground(Color.white);
            richtung.setOpaque(false);
            richtung.setBorderPainted(true);
            richtung.setFont(new Font("Serif", Font.PLAIN, 30));
            ships.add(richtung);
            for (int i = 0; i < spielfeld.ship.getAnzahlderSchiffe().length; i++) {
                JPanel shipBox = new JPanel();
                shipBox.setPreferredSize(new Dimension(300,500));
                shipBox.setBorder(BorderFactory.createLineBorder(Color.black,2));
                ships.add(shipBox);
                for (int j = 0; j < spielfeld.ship.getAnzahlderSchiffe()[i]; j++) {
                    if (spielfeld.ship.getAnzahlderSchiffe()[i] > 0) {
                        for (int h = 0; h < i +2; h++) {
                            shipcells[i][j] = new ShipCell(i,j);
                            shipBox.add(shipcells[i][j]);
                        }
                    } else {
                        break;
                    }
                }
            }
            JButton backbtn = new UiButton("Go Back");
            backbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    content.add(new BoardCreator());
                    content.revalidate();
                }
            });
            ships.add(backbtn);
            add(ships, BorderLayout.EAST);
        }
    }
    /*
    class ShipPlacementPlayer2 extends JPanel {
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
        JButton richtung;
        Action actOne = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actTwo);
                richtung.setText("Horizontal");
                spielfeld.ship.setRichtung(true);
            }
        };
        Action actTwo = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actOne);
                richtung.setText(" Vertikal ");
                spielfeld.ship.setRichtung(false);
            }
        };
        ShipPlacementPlayer2() {
            ShipCell[][] shipcells = new ShipCell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
            setLayout(new BorderLayout());
            Container cellField = new Container();
            cellField.setLayout(new GridLayout(Spielfeld.getSpielfeldSize(),Spielfeld.getSpielfeldSize()));
            for (int row = 1; row < Spielfeld.getSpielfeldSize() ; row++) {
                for (int col = 1; col < Spielfeld.getSpielfeldSize(); col++) {
                    cells[row][col] = new Cell(row, col);
                    cells[row][col].setActionCommand(cells[row][col].getRow() + "," + cells[row][col].getCol());
                    cellField.add(cells[row][col]);
                    int finalRow = row;
                    int finalCol = col;
                    cells[row][col].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                                spielfeld.placeEnemyShips();
                        }
                    });
                }
            }
            add(cellField);
            Container ships = new Container();
            ships.setLayout(new BorderLayout());
            richtung = new JButton(actTwo);
            ships.add(richtung,BorderLayout.PAGE_START);
            JButton startGame = new JButton("Test");
            startGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    content.add(new BattleScreen());
                    content.revalidate();
                }
            });
            ships.add(startGame,BorderLayout.PAGE_START);
            JButton backbtn = new JButton("BACK");
            backbtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    content.add(new ShipPlacementPlayer1());
                    content.revalidate();
                }
            });
            ships.add(backbtn,BorderLayout.PAGE_END);
            for (int i = 0; i < spielfeld.ship.getAnzahlderSchiffe().length; i++) {
                JPanel shipBox = new JPanel();
                shipBox.setBorder(BorderFactory.createLineBorder(Color.black,2));
                ships.add(shipBox,BorderLayout.EAST);
                for (int j = 0; j < spielfeld.ship.getAnzahlderSchiffe()[i]; j++) {
                    for (int k = 0; k < i +2; k++) {
                        shipcells[i][j] = new ShipCell(i,j);
                        shipBox.add(shipcells[i][j]);
                    }
                }
            }

            /*
            Container ships = new Container();
            ships.setLayout(new GridLayout(10,10));
            richtung = new JButton(actOne);
            BattleShip [] vorhandeneSchiffe = spielfeld.getVorhandeneSchiffe();
            for (int i = 0; i < spielfeld.getVorhandeneSchiffe().length; i++) { //hier muss die Anzahl der Schiffe rein
                JPanel shipBox = new JPanel();
                shipBox.setBorder(BorderFactory.createLineBorder(Color.black,2));
                ships.add(shipBox, BorderLayout.EAST);
                System.out.println("Schiffanzahl: " + i);
                for (int j = 0; j < spielfeld.getVorhandeneSchiffe().length; j++) { // hier muss die Schiffsize rein
                    shipcells[i][j] = new ShipCell(i,j);
                    shipBox.add(shipcells[i][j]);
                }
            }
            add(ships, BorderLayout.LINE_END);
        }
    }*/
    class ShipPlacementPlayer2 extends JPanel {
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
        JButton richtung;
        Action actOne = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actTwo);
                richtung.setText("Horizontal");
                spielfeld.ship.setRichtung(true);
            }
        };
        Action actTwo = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actOne);
                richtung.setText("  Vertikal  ");
                spielfeld.ship.setRichtung(false);
            }
        };
        ShipPlacementPlayer2() {
            if (enemy.equals("Player")) {
                setLayout(new BorderLayout());
                ShipCell[][] shipcells = new ShipCell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
                Container ships = new Container();
                ships.setLayout(new GridLayout(10, 10, 1, 1));
                Container cellField = new Container();
                cellField.setLayout(new GridLayout(Spielfeld.getSpielfeldSize(), Spielfeld.getSpielfeldSize()));

                JButton startGame = new UiButton("Start Game");
                startGame.setEnabled(false);
                startGame.setBackground(Color.red);
                startGame.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        content.removeAll();
                        gameState = "Battle";
                        isPlayerTurn = false;
                        content.add(new BattleScreen());
                        content.revalidate();
                    }
                });
                ships.add(startGame);
                //initialisiert das Spielfeld
                for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                    for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                        cells[row][col] = new Cell(row, col);
                        cells[row][col].setActionCommand(cells[row][col].getRow() + "," + cells[row][col].getCol());
                        cellField.add(cells[row][col]);
                        int finalRow = row;
                        int finalCol = col;
                        cells[row][col].addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (gameState.equals("Setzen")) {
                                    if (spielfeld.ship.getShipList().size() > 0) {
                                        spielfeld.setKey(1);
                                        spielfeld.placeEnemyShips(finalCol, finalRow);
                                        for (int i = 0; i < Spielfeld.getSpielfeldSize(); i++) {
                                            for (int j = 0; j < Spielfeld.getSpielfeldSize(); j++) {
                                                if (spielfeld.getZustandSpielfeld(i, j) != -1) {
                                                    cells[j][i].setBackground(shipcolor);
                                                    cells[j][i].setStatusColor(shipcolor);
                                                }
                                            }
                                        }
                                        spielfeld.ship.getShipList().remove(0);
                                    } else {
                                        startGame.setBackground(Color.gray);
                                        startGame.setEnabled(true);
                                        Spielfeld.setSpielfeldSize(Spielfeld.getSpielfeldSize());
                                    }
                                }
                            }
                        });
                    }
                }
                add(cellField, BorderLayout.CENTER);
                richtung = new JButton(actTwo);
                richtung.setContentAreaFilled(false);
                richtung.setForeground(Color.white);
                richtung.setOpaque(false);
                richtung.setBorderPainted(true);
                richtung.setFont(new Font("Serif", Font.PLAIN, 30));
                ships.add(richtung);
                for (int i = 0; i < spielfeld.ship.getAnzahlderSchiffe().length; i++) {
                    JPanel shipBox = new JPanel();
                    shipBox.setPreferredSize(new Dimension(300, 500));
                    shipBox.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    ships.add(shipBox);
                    for (int j = 0; j < spielfeld.ship.getAnzahlderSchiffe()[i]; j++) {
                        if (spielfeld.ship.getAnzahlderSchiffe()[i] > 0) {
                            for (int h = 0; h < i + 2; h++) {
                                shipcells[i][j] = new ShipCell(i, j);
                                shipBox.add(shipcells[i][j]);
                            }
                        } else {
                            break;
                        }
                    }
                }
                JButton backbtn = new UiButton("Go Back");
                backbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        content.removeAll();
                        content.add(new BoardCreator());
                        content.revalidate();
                    }
                });
                ships.add(backbtn);
                add(ships, BorderLayout.EAST);
            }
        }
    }
    class BattleScreen extends JPanel {
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
        public BattleScreen() {
            setLayout(new BorderLayout());
            Container midContainer = new Container();
            midContainer.setLayout(new GridLayout(1,2,10,1));
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

            for (int i = 0; i < Spielfeld.getSpielfeldSize(); i++) {
                for (int j = 0; j < Spielfeld.getSpielfeldSize(); j++) {
                    cells = getCells();
                    cells[i][j].getBackground();
                    player1Panel.add(cells[i][j]);
                    int finalI = i;
                    int finalJ = j;
                    cells[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println(gameState);
                            System.out.println(finalI +"/" + finalJ);
                            if(gameState.equals("Battle")) {
                                if(isPlayerTurn == true) {
                                    spielfeld.shoot(finalJ, finalI);
                                    if (spielfeld.getZustandSpielfeld(finalI,finalJ) == -2) {
                                        cells[finalJ][finalI].setStatusColor(misscolor);
                                        cells[finalJ][finalI].setBackground(misscolor);
                                    }
                                }
                            }
                        }
                    });
                }
            }
            midContainer.add(player1Panel);
            for(int i = 0; i < Spielfeld.getSpielfeldSize(); i++){
                for(int j = 0; j < Spielfeld.getSpielfeldSize(); j++){
                    cells[i][j] = new Cell(i,j);
                    player2Panel.add(cells[i][j]);
                }
            }
            midContainer.add(player2Panel);

            JLabel fight = new UiLabel("Fight until no more Ships are left");
            fight.setHorizontalAlignment(SwingConstants.CENTER);
            botContainer.add(fight);
            JButton save = new UiButton("SAVE");
            save.setHorizontalAlignment(SwingConstants.CENTER);
            botContainer.add(save);
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        spielfeld.speichern();
                    } catch (Exception ex) {
                        ex.printStackTrace();
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
                    content.add(new MainMenu());
                    content.revalidate();
                }
            });

            JLabel player1text = new UiLabel("PLAYER 1");
            player1text.setHorizontalAlignment(SwingConstants.CENTER);
            topContainer.add(player1text);

            JLabel whosTurn = new UiLabel("Whos turn is it: ");
            whosTurn.setHorizontalAlignment(SwingConstants.CENTER);
            topContainer.add(whosTurn);

            JLabel player2text = new UiLabel("PLAYER 2");
            player2text.setHorizontalAlignment(SwingConstants.CENTER);
            topContainer.add(player2text);
        }
    }
    class Cell extends JButton {
    private final int row;
    private final int col;
    Color statusColor;
        Cell(final int row, final int col) {
            this.row = row;
            this.col = col;
            setOpaque(true);
            setBackground(watercolor);
            setText("~");
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public Color getStatusColor() {
            return statusColor;
        }

        public void setStatusColor(Color statusColor) {
            this.statusColor = statusColor;
        }
    }
    class ShipCell extends JButton {
    private final int x;
    private final int y;
        ShipCell(final int x, final int y) {
            this.x = x;
            this.y = y;
            setBackground(shipcolor);
            setText("lol");
        }
    }
}
