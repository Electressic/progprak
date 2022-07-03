package progprak.src.UI;

import progprak.src.Logik.Ship;
import progprak.src.Logik.Spielfeld;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

public class UI {
    // Define new Colors
    Color watercolor = new Color(212,241,249);
    Color shipcolor = new Color(78, 78, 76);
    Color sunkcolor = new Color (139,0,0);
    Color hitcolor = Color.black;
    Color misscolor = new Color(19, 23, 128);
    // Kram für Filechooser

    static private final String newline = "\n";
    JTextArea log;
    final JFileChooser fc = new JFileChooser();

    // Panel for adding Content
    JPanel content;

    // int to save shipSum, percentage relative to Board and StartButton for Actionevent
    int shipSum;
    int percentage;
    JButton createStart;
    //create new MainFrame
    JFrame main = new JFrame("TEST");

    //create Spielfeld and Ship Class for ui
    Spielfeld spielfeld = new Spielfeld();
    int[] SpinnerArr;

    int shipValue2,shipValue3,shipValue4,shipValue5,shipValue6;

    // make 2D-Array for the Board
    Cell[][] cells = new Cell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
    Cell[][] enemycells = new Cell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
    // Schiffarray
    JButton[][] schiffe = new JButton[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];
    // Gamestate Schiffe setzen oder Battle
    String gameState;
    //which turn it is
    boolean isPlayerTurn;
    //check if Mp or Computer
    String enemy;

    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }
    public String getPlayerTurn() {
        if (isPlayerTurn == true) {
            return "Player 1";
        }
        return "Player 2";
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
                    spielfeld = new Spielfeld();
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
        // gets the percentage
        public int getPercentage() {
            return percentage;
        }
        public int[] getShipCounting() {
            int[] temp;
            temp = spielfeld.ship.getAnzahlderSchiffe();
            return temp;
        }
        // if ShipSum is exactly 30% make start enabled and launch the placement
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
        // method for background
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
                    spielfeld.ship.setRichtung(true);
                }
            });

            JButton loadButton = new UiButton("Load");
            cLayout.gridx = 2;
            cLayout.gridy = 0;
            cLayout.gridwidth = 1;
            add(loadButton, cLayout);
            loadButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() == loadButton) {
                        int returnVal = fc.showOpenDialog(BoardCreator.this);
                        if (returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = fc.getSelectedFile();
                            log.append("Opening: " + file.getName() + "." + newline);
                        } else {
                            log.append("Open command cancelled by user." + newline);
                        }
                    }
                }
            });

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
            inputText.setBorder(BorderFactory.createLoweredBevelBorder());
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
            prozent.setFont(new Font("serif", Font.PLAIN,15));
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
                    ship2Text.setText("Anzahl der Schipsize2: " + ship2.getValue());
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
                    ship3Text.setText("Anzahl der Schipsize3: " + ship3.getValue());
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
                    ship4Text.setText("Anzahl der Schipsize4: " + ship4.getValue());
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
                    ship5Text.setText("Anzahl der Schipsize5: " + ship5.getValue());
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
                    ship6Text.setText("Anzahl der Schipsize6: " + ship6.getValue());
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
            cLayout.gridx = 1;
            cLayout.gridy = 2;
            cLayout.gridwidth = 2;
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
        int sumofShips = IntStream.of(spielfeld.ship.getAnzahlderSchiffe()).sum();

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

        ShipPlacementPlayer1() {
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
                    gameState = "Setzen";
                    isPlayerTurn = true;

                    spielfeld.resetKey(0);
                    new ShipPlacementPlayer2();
                    gameState = "Battle";
                    content.add(new BattleScreen());
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
                            if (gameState.equals("Setzen")) {
                                for (int i = 0; i < spielfeld.ship.getShipList().get(0); i++) {
                                    if (spielfeld.ship.getRichtung() == true) {
                                        Spielfeld.getSurroundings(spielfeld.ship.getShipFleet(), finalCol, finalRow + i);
                                        if ((finalRow + spielfeld.ship.getShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                                            return;
                                        }
                                        for (int c : spielfeld.getListDirection()) {
                                            if (c > 0) {
                                                return;
                                            }
                                        }
                                    } else {
                                        Spielfeld.getSurroundings(spielfeld.ship.getShipFleet(), finalCol + i, finalRow);
                                        if ((finalCol + spielfeld.ship.getShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                                            return;
                                        }
                                        for (int c : spielfeld.getListDirection()) {
                                            if (c > 0) {
                                                return;
                                            }
                                        }
                                    }
                                }
                                if (spielfeld.ship.getShipList().size() == 1) {
                                    startGame.setBackground(Color.gray);
                                    startGame.setEnabled(true);
                                    Spielfeld.setSpielfeldSize(Spielfeld.getSpielfeldSize());
                                }
                                if (gameState.equals("Setzen") && spielfeld.ship.getShipList().size() > 0) {
                                    spielfeld.setKey(1);
                                    for (int s = 0; s < spielfeld.ship.getFleet().get(spielfeld.getKey()); s++) {
                                        if (spielfeld.ship.getRichtung() == true) {
                                            spielfeld.placeShips(finalRow, finalCol);
                                            cells[finalRow + s][finalCol].setBackground(shipcolor);
                                        } else {
                                            spielfeld.placeShips(finalRow, finalCol);
                                            cells[finalRow][finalCol + s].setBackground(shipcolor);
                                        }
                                        if (finalRow + s < Spielfeld.getSpielfeldSize() && finalRow + 1 < 0) ;
                                    }
                                    spielfeld.ship.getShipList().remove(0);
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
                    if (spielfeld.ship.getRichtung() == false) {
                        spielfeld.ship.setRichtung(true);
                        richtung.setText("Richtung: Horizontal");
                    } else {
                        spielfeld.ship.setRichtung(false);
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
                for (int j = 0; j < spielfeld.ship.getShipList().get(i); j++) {
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
    class ShipPlacementPlayer2 {
        ShipPlacementPlayer2() {
            for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                    enemycells[row][col] = new Cell(row, col);
                }
            }
            while (spielfeld.ship.getEnemyShipList().size() > 0) {
                Spielfeld.res = new ArrayList<>();
                int finalRow = spielfeld.rn.nextInt(Spielfeld.getSpielfeldSize());
                int finalCol = spielfeld.rn.nextInt(Spielfeld.getSpielfeldSize());
                int randomrichtung = spielfeld.rn.nextInt(1);
                boolean isHorzon;
                if(randomrichtung ==0)
                {
                    isHorzon = false;
                }
                else
                {
                    isHorzon = true;
                }
                spielfeld.ship.setRichtung(isHorzon);
                if (gameState.equals("Setzen")) {
                    for (int i = 0; i < spielfeld.ship.getEnemyShipList().get(0); i++) {
                        if (spielfeld.ship.getRichtung() == true) {
                            Spielfeld.getSurroundings(spielfeld.ship.getEnemyShipFleet(), finalCol, finalRow + i);
                            if ((finalRow + spielfeld.ship.getEnemyShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                                break;
                            }
                            for (int c : spielfeld.getListDirection()) {
                                if (c > 0) {
                                    break;
                                }
                            }
                        } else {
                            Spielfeld.getSurroundings(spielfeld.ship.getEnemyShipFleet(), finalCol + i, finalRow);
                            if ((finalCol + spielfeld.ship.getEnemyShipList().get(0)) > Spielfeld.getSpielfeldSize()) {
                                break;
                            }
                            for (int c : spielfeld.getListDirection()) {
                                if (c > 0) {
                                    break;
                                }
                            }
                        }
                    }
                }
                spielfeld.setKey(1);
                System.out.println(spielfeld.getKey());
                for (int s = 0; s < spielfeld.ship.getEnemyFleet().get(spielfeld.getKey()); s++) {
                    if (spielfeld.ship.getRichtung() == true) {
                        spielfeld.placeEnemyShips(finalRow, finalCol);
                    } else {
                        spielfeld.placeEnemyShips(finalRow, finalCol);
                    }
                    if (finalRow + s < Spielfeld.getSpielfeldSize() && finalRow + 1 < 0) ;
                }
                spielfeld.ship.getEnemyShipList().remove(0);
                spielfeld.displayEnemyFeld();
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
                    player1Panel.add(cells[row][col]);
                    int finalI = col;
                    int finalJ = row;
                    cells[col][row].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(gameState.equals("Battle")) {
                                if(isPlayerTurn == false) {
                                    // hier passiert erstmal nix theoretisch war das enemyshoot
                                    if(spielfeld.getString().equals("miss")) {
                                        cells[finalI][finalJ].setBackground(misscolor);
                                        cells[finalI][finalJ].setEnabled(false);
                                    } else if(spielfeld.getString().equals("shiphit")) {
                                        cells[finalI][finalJ].setBackground(hitcolor);
                                        cells[finalI][finalJ].setEnabled(false);
                                    } else if (spielfeld.getString().equals("shipsunk")) {
                                        for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                                            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                                                if (spielfeld.getZustandSpielfeld(row, col) == 0) {
                                                    cells[row][col].setBackground(sunkcolor);
                                                    cells[row][col].setEnabled(false);
                                                }
                                            }
                                        }
                                    }
                                    isPlayerTurn = true;
                                }
                            }
                        }
                    });
                }
            }
            midContainer.add(player1Panel);

            JLabel whoseTurn = new UiLabel("Whose turn it is : " + getPlayerTurn());
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
                            if(gameState.equals("Battle")) {
                                if(isPlayerTurn == true) {
                                    key[0] = spielfeld.getZustandEnemySpielfeld(finalI, finalJ);
                                    spielfeld.shoot(finalJ, finalI);
                                    if(spielfeld.getString().equals("miss")) {
                                        enemycells[finalI][finalJ].setBackground(misscolor);
                                        enemycells[finalI][finalJ].setEnabled(false);
                                    } else if(spielfeld.getString().equals("shiphit")) {
                                        enemycells[finalI][finalJ].setBackground(hitcolor);
                                        enemycells[finalI][finalJ].setEnabled(false);
                                    } else if (spielfeld.getString().equals("shipsunk")) {
                                        for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                                            for (int row = 0; row < Spielfeld.getSpielfeldSize(); row++) {
                                                if (spielfeld.getZustandEnemySpielfeld(row, col) == key[0]) {
                                                    enemycells[row][col].setBackground(sunkcolor);
                                                    enemycells[row][col].setEnabled(false);
                                                }
                                            }
                                        }
                                        if(imLost())
                                        {
                                            return;
                                        }
                                    }
                                    if (spielfeld.getString().equals("shiphit") || spielfeld.getString().equals("shipsunk")) {
                                        isPlayerTurn = true;
                                    } else {
                                        isPlayerTurn = false;
                                    }
                                    int x = spielfeld.rn.nextInt(spielfeld.getSpielfeldSize()) ;
                                    int y = spielfeld.rn.nextInt(spielfeld.getSpielfeldSize());
                                    System.out.println("x/y" + x + "/" + y);
                                    if (isPlayerTurn == false) {
                                        cells[x][y].doClick();
                                        spielfeld.aiShoot(x,y);
                                        isPlayerTurn=true;
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
                    if (returnval == JFileChooser.APPROVE_OPTION) {
                        File file = saveFile.getSelectedFile();
                        if (file == null) {
                            return;
                        }
                        if (!file.getName().toLowerCase().endsWith(".txt")) {
                            file = new File(file.getParentFile(), file.getName() + ".txt");
                        }
                        try {
                            spielfeld.speichern();
                            Desktop.getDesktop().open(file);
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
                    content.add(new MainMenu());
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
        boolean imLost()
        {
            for (Integer Entry : spielfeld.ship.enemyFleet.keySet()) {
                if( spielfeld.ship.enemyFleet.get(Entry) != 0)
                {
                    return false;
                }
            }
            int n = JOptionPane.showConfirmDialog(null, "You lost!", "test",JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE);
            if (n == JOptionPane.OK_OPTION) {
                content.removeAll();
                content.add(new MainMenu());
                content.revalidate();
            }
            System.out.println("ende");
            return true;
        }
    }
    class Cell extends JButton {
    private final int row;
    private final int col;
    protected Color testcolor;
        Cell(final int row, final int col) {
            this.row = row;
            this.col = col;
            setOpaque(true);
            setBackground(watercolor);
        }

        public Color getTestcolor() {
            return testcolor;
        }

        public void setTestcolor(Color testcolor) {
            this.testcolor = testcolor;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }
    }
}
