package progprak.src.UI;

import progprak.src.Logik.BattleShip;
import progprak.src.Logik.Spielfeld;
import progprak.src.Main.LaunchGame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UI {
    // Define new Colors
    Color watercolor = new Color(212,241,249);
    Color shipcolor = new Color(78, 78, 76);
    Color hitcolor = new Color (139,0,0);

    // Panel for adding Content
    JPanel content;

    // int to save boardSize
    int maxgroesse;

    //create new MainFrame
    JFrame main = new JFrame("TEST");

    //create Spielfeld for ui
    Spielfeld spielfeld;
    BattleShip battleShip;

    // make 2D-Array for the Board
    Cell[][] cells = new Cell[Spielfeld.getSpielfeldSize()][Spielfeld.getSpielfeldSize()];

    //Actionlistener for what happens
    ActionListener cellClick = actionEvent ->{
        Object source = actionEvent.getSource();

    };
    //Constructor that creates the UI
    public UI() {
        spielfeld = new Spielfeld(maxgroesse);
        createGUI();
    }
    //creating UI
    private void createGUI() {
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setPreferredSize(new Dimension(800,600));
        main.setLocationRelativeTo(null);
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
        main.setVisible(true);
    }
    //makes a Custom Button with a certain Look
    public static class UiButton extends JButton {
        public UiButton(String text) {
            super(text);
            setContentAreaFilled(false);
            setForeground(Color.white);
            setOpaque(false);
            setContentAreaFilled(false);
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
        static final int gridmin = 5;
        static final int gridmax = 30;
        static final int gridinit = 15;
        //Variable für JSpinner
        JSpinner ship2,ship3,ship4,ship5,ship6;

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

            JButton start = new UiButton("Start");
            cLayout.gridx = 3;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            start.setEnabled(true);
            add(start, cLayout);
            start.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    content.add(new ShipPlacementPlayer1());
                    content.revalidate();
                    int[] SpinnerArr = { (int) ship2.getValue(), (int) ship3.getValue(), (int) ship4.getValue(), (int) ship5.getValue(), (int) ship6.getValue() };
                    for (int i = 0; i < 5; i++) {
                        for (int j = 0; j < SpinnerArr[i]; j++) {
                            spielfeld.InitializeShip((i +2));
                        }
                    }
                    /*int totalShipSize = ((SpinnerArr[0] * 2) + (SpinnerArr[1] * 3) + (SpinnerArr[2] * 4) + (SpinnerArr[3] * 5) + (SpinnerArr[4] * 6));
                    if (totalShipSize > (0.3 * (maxgroesse * maxgroesse))) {
                        start.setEnabled(false);
                    } else {
                        start.setEnabled(true);
                    }*/
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

            JLabel boardSizeText = new UiLabel("Spielfeldgroesse: " + Spielfeld.getSpielfeldSize());
            cLayout.gridx = 0;
            cLayout.gridy = 2;
            cLayout.gridwidth = 3;
            add(boardSizeText, cLayout);

            JSlider boardSize = new JSlider(JSlider.HORIZONTAL,gridmin,gridmax,gridinit);
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
                    maxgroesse = Spielfeld.setSpielfeldSize(boardSize.getValue() + 1);
                    boardSizeText.setText("Spielfeldgroesse: " + boardSize.getValue());
                    // Logik für Schiffgröße 2 und 6. Disabled und Enabled sie.
                    if (boardSize.getValue() >= 20) {
                        ship2.setEnabled(false);
                        ship2.setValue(0);
                        ship6.setEnabled(true);
                    }
                    else {
                        ship2.setEnabled(true);
                        ship6.setEnabled(false);
                        ship6.setValue(0);
                    }
                }
            });
            boardSize.setMajorTickSpacing(5);
            boardSize.setMinorTickSpacing(1);
            boardSize.setPaintLabels(true);
            boardSize.setPaintTicks(true);


            ship2Text = new UiLabel("Anzahl der Schipsize2: " + 0);
            cLayout.gridx = 0;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            add(ship2Text, cLayout);

            ship2 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
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
                }
            });
            add(ship2, cLayout);

            ship3Text = new UiLabel("Anzahl der Schipsize3: " + 0);
            cLayout.gridx = 0;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship3Text, cLayout);

            ship3 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 1;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship3, cLayout);

            ship4Text = new UiLabel("Anzahl der Schipsize4: " + 0);
            cLayout.gridx = 0;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(ship4Text, cLayout);

            ship4 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 1;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(ship4, cLayout);

            ship5Text = new UiLabel("Anzahl der Schipsize5: " + 0);
            cLayout.gridx = 2;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            add(ship5Text, cLayout);

            ship5 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 3;
            cLayout.gridy = 4;
            cLayout.gridwidth = 1;
            add(ship5, cLayout);

            ship6Text = new UiLabel("Anzahl der Schipsize6: " + 0);
            cLayout.gridx = 2;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship6Text, cLayout);

            ship6 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
            cLayout.gridx = 3;
            cLayout.gridy = 5;
            cLayout.gridwidth = 1;
            add(ship6, cLayout);

            JLabel prozent = new UiLabel("Schiffe sollen 30% betragen: " + "%");
            cLayout.gridx = 2;
            cLayout.gridy = 6;
            cLayout.gridwidth = 1;
            add(prozent, cLayout);
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
            }
        };
        Action actTwo = new AbstractAction("Horizontal") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((JButton)e.getSource()).setAction(actOne);
                richtung.setText("Vertikal");
            }
        };
        ShipPlacementPlayer1() {
            ShipCell[][] shipcells = new ShipCell[5][5];
            setLayout(new BorderLayout());
            Container cellField = new Container();
            LaunchGame.displayFeld();
            LaunchGame.placeShips();
            cellField.setLayout(new GridLayout(Spielfeld.SpielfeldSize,Spielfeld.SpielfeldSize));
            for (int row = 0; row < Spielfeld.getSpielfeldSize() ; row++) {
                for (int col = 0; col < Spielfeld.getSpielfeldSize(); col++) {
                    cells[row][col] = new Cell(row, col);
                    cells[row][col].setActionCommand(cells[row][col].getRow() + "," + cells[row][col].getCol());
                    cellField.add(cells[row][col]);
                    int finalRow = row;
                    int finalCol = col;
                    cells[row][col].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //spielfeld.setzeSchiff([finalRow], [finalCol], true, )
                        }
                    });
                }
            }
            add(cellField, BorderLayout.WEST);

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
            add(ships, BorderLayout.CENTER);
        }
    }
    class Cell extends JButton {
    private final int row;
    private final int col;

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
