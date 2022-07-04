package UI;

import Logik.Ship;
import Logik.Spielfeld;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class BoardCreator extends JPanel implements ButtonUndLabel {
    int shipSum;
    int percentage;
    JButton createStart;
    int[] SpinnerArr;
    int shipValue2,shipValue3,shipValue4,shipValue5,shipValue6;
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
    BoardCreator(JPanel content, Ship ship, Spielfeld spielfeld) {
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
                isFitting(content, ship, spielfeld);
                shipSum = 0;
                percentage = 0;
                shipValue2 = 0;
                shipValue3 = 0;
                shipValue4 = 0;
                shipValue5 = 0;
                shipValue6 = 0;
                ship.setRichtung(true);
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
                JFileChooser loadFile = new JFileChooser();
                int returnval = loadFile.showOpenDialog(content);
                if (returnval == JFileChooser.APPROVE_OPTION){
                    File file = loadFile.getSelectedFile();
                    try {
                        System.out.println("test");
                        spielfeld.laden(file.getName());
                    } catch (Exception ex) {}
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
                content.add(new MainMenu(content, ship, spielfeld));
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
                setShipSum(ship);
                setPercentage();
                isFitting(content, ship, spielfeld);
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
                setShipSum(ship);
                setPercentage();
                isFitting(content, ship, spielfeld);
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
                setShipSum(ship);
                setPercentage();
                isFitting(content, ship, spielfeld);
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
                setShipSum(ship);
                setPercentage();
                isFitting(content, ship, spielfeld);
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
                setShipSum(ship);
                setPercentage();
                isFitting(content, ship, spielfeld);
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
                isFitting(content, ship, spielfeld);
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
    // counts all the Ships + gets them
    public int setShipSum(Ship ship) {
        shipSum = 0;
        SpinnerArr = new int[]{ shipValue2,shipValue3,shipValue4,shipValue5,shipValue6};
        ship.createShips(SpinnerArr);
        for (int i = 0; i <ship.getShipList().size(); i++) {
            shipSum += ship.getShipList().get(i);
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
    // if ShipSum is exactly 30% make start enabled and launch the placement
    public void isFitting(JPanel content,Ship ship, Spielfeld spielfeld) {
        if (getPercentage() == 30) {
            createStart.setEnabled(true);
            createStart.setContentAreaFilled(false);
            createStart.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    content.removeAll();
                    spielfeld.initFeld(ship);
                    spielfeld.initEnemyFeld(ship);
                    spielfeld.displayFeld(ship);
                    spielfeld.displayEnemyFeld(ship);
                    spielfeld.gameState = "Setzen";
                    content.add(new ShipPlacement(content, ship, spielfeld));
                    content.revalidate();
                }
            });
        } else {
            createStart.setEnabled(false);
            createStart.setBackground(Color.red);
        }
    }
}