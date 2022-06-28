package progprak.src.UI;

import progprak.src.Logik.BattleShip;
import progprak.src.Logik.Spielfeld;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Creator extends Component {
    JFrame create = new JFrame();
    private JLabel errorMessage;
    private JLabel shipSize;
    private JSlider gridSizeSlider;
    //Boxes für Groessenauswahl
    private JCheckBox size2 = new JCheckBox("2");
    private JCheckBox size3 = new JCheckBox("3");
    private JCheckBox size4 = new JCheckBox("4");
    private JCheckBox size5 = new JCheckBox("5");
    private JCheckBox size6 = new JCheckBox("6");
    //Spinner für Anzahl
    private static JSpinner s2,s3,s4,s5,s6;
    // variablen für slider
    static final int gridmin = 5;
    static final int gridmax = 30;
    static final int gridinit = 15;
    // shit für fileexplorer
    static private final String newline = "\n";
    JTextArea log;
    final JFileChooser fc = new JFileChooser();


    public Creator(){
        create.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        create.setMinimumSize(new Dimension(600 ,400));
        create.setLocationRelativeTo(null);
        create.setVisible(true);

        JPanel creator = new JPanel(new GridBagLayout());
        create.setContentPane(creator);

        GridBagConstraints clayout = new GridBagConstraints();
        clayout.weightx = 1;
        clayout.weighty = 1;
        clayout.fill = GridBagConstraints.HORIZONTAL;
        clayout.insets = new Insets(5,5,5,5);

        createWindow(clayout);
    }
    public boolean shipsunder30 () {
        int totalShipSize = (BattleShip.shipCount2 * 2) +
                (BattleShip.shipCount3 * 3) +
                (BattleShip.shipCount4 * 4) +
                (BattleShip.shipCount5 * 5) +
                (BattleShip.shipCount6 * 6);
        int groesse = Spielfeld.SpielfeldSize * Spielfeld.SpielfeldSize;
        if (totalShipSize > (0.3 * groesse)) {
            return false;
        }
        return true;
    }
    public void createWindow(GridBagConstraints clayout) {
        log = new JTextArea(5,20); // log für File explorer shit?
        JButton startGame = new JButton("START");
        clayout.gridx = 0;
        clayout.gridwidth = 1;
        clayout.gridy = 2;
        create.add(startGame, clayout);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create.dispose();
                ShipPlacement Battlegrid = new ShipPlacement();
            }
        });
        // load button. opens file explorer für die datei zum laden
        JButton loadButton = new JButton("LOAD");
        clayout.gridx = 0;
        clayout.gridwidth = 1;
        clayout.gridy = 3;
        create.add(loadButton, clayout);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadButton) {
                    int returnVal = fc.showOpenDialog(Creator.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        log.append("Opening: " + file.getName() + "." + newline);
                    } else {
                        log.append("Open command cancelled by user." + newline);
                    }
                }
            }
        });
        JButton backbutton = new JButton("BACK");
        clayout.gridx = 0;
        clayout.gridwidth = 1;
        clayout.gridy = 4;
        create.add(backbutton, clayout);
        backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==backbutton) {
                    create.dispose();
                    MainMenu createWindow = new MainMenu();
                }
            }
        });

        errorMessage = new JLabel("Error: Grid zu klein");
        clayout.gridx = 0;
        clayout.gridwidth = 2;
        clayout.gridy = 5;
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        create.add(errorMessage,clayout);

        JLabel gridSize = new JLabel("Spielfeldgroesse: " + Spielfeld.SpielfeldSize);
        clayout.gridx = 0;
        clayout.gridwidth = 1;
        clayout.gridy = 0;
        create.add(gridSize, clayout);

        gridSizeSlider = new JSlider(JSlider.HORIZONTAL,gridmin,gridmax,gridinit);
        clayout.gridx = 0;
        clayout.gridwidth = 2;
        clayout.gridy = 1;
        gridSizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gridSize.setText("Spielfeldgroesse: " + gridSizeSlider.getValue());
                Spielfeld.SpielfeldSize = gridSizeSlider.getValue();
                boolean shipsFit = shipsunder30();
                startGame.setEnabled(shipsFit);
                errorMessage.setVisible(!shipsFit);
                // Logik für Schiffgröße 2 und 6. Disabled und Enabled sie.
                if (gridSizeSlider.getValue() >= 20) {
                    size2.setEnabled(false);
                    size2.setSelected(false);
                    s2.setEnabled(false);
                    s2.setValue(0);
                    size6.setEnabled(true);
                    s6.setEnabled(true);
                }
                else {
                    size2.setEnabled(true);
                    s2.setEnabled(true);
                    size6.setEnabled(false);
                    size6.setSelected(false);
                    s6.setEnabled(false);
                    s6.setValue(0);
                }
            }
        });
        create.add(gridSizeSlider, clayout);
        gridSizeSlider.setMajorTickSpacing(5);
        gridSizeSlider.setMinorTickSpacing(1);
        gridSizeSlider.setPaintLabels(true);
        gridSizeSlider.setPaintTicks(true);

        size2 = new JCheckBox("Groesse 2 Anzahl: " + BattleShip.shipCount2);
        clayout.gridx = 2;
        clayout.gridwidth = 1;
        clayout.gridy = 0;
        create.add(size2, clayout);

        s2 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
        clayout.gridx = 3;
        clayout.gridwidth = 1;
        clayout.gridy = 0;
        create.add(s2, clayout);
        s2.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               size2.setText("Groesse 2 Anzahl: " + s2.getValue());
                int value2 = (Integer) s2.getValue();
                BattleShip.shipCount2 = value2;
                boolean shipsFit = shipsunder30();
                startGame.setEnabled(shipsFit);
                errorMessage.setVisible(!shipsFit);
            }
        });

        size3 = new JCheckBox("Groesse 3 Anzahl: " + BattleShip.shipCount3);
        clayout.gridx = 2;
        clayout.gridwidth = 1;
        clayout.gridy = 1;
        create.add(size3, clayout);

        s3 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
        clayout.gridx = 3;
        clayout.gridwidth = 1;
        clayout.gridy = 1;
        create.add(s3, clayout);
        s3.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size3.setText("Groesse 3 Anzahl: " + s3.getValue());
                int value3 = (Integer) s3.getValue();
                BattleShip.shipCount3 = value3;
                boolean shipsFit = shipsunder30();
                startGame.setEnabled(shipsFit);
                errorMessage.setVisible(!shipsFit);
            }
        });

        size4 = new JCheckBox("Groesse 4 Anzahl: " + BattleShip.shipCount4);
        clayout.gridx = 2;
        clayout.gridwidth = 1;
        clayout.gridy = 2;
        create.add(size4, clayout);

        s4 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
        clayout.gridx = 3;
        clayout.gridwidth = 1;
        clayout.gridy = 2;
        create.add(s4, clayout);
        s4.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size4.setText("Groesse 4 Anzahl: " + s4.getValue());
                int value4 = (Integer) s4.getValue();
                BattleShip.shipCount4 = value4;
                boolean shipsFit = shipsunder30();
                startGame.setEnabled(shipsFit);
                errorMessage.setVisible(!shipsFit);
            }
        });

        size5 = new JCheckBox("Groesse 5 Anzahl: " + BattleShip.shipCount5);
        clayout.gridx = 2;
        clayout.gridwidth = 1;
        clayout.gridy = 3;
        create.add(size5, clayout);

        s5 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
        clayout.gridx = 3;
        clayout.gridwidth = 1;
        clayout.gridy = 3;
        create.add(s5, clayout);
        s5.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size5.setText("Groesse 5 Anzahl: " + s5.getValue());
                int value5 = (Integer) s5.getValue();
                BattleShip.shipCount5 = value5;
                boolean shipsFit = shipsunder30();
                startGame.setEnabled(shipsFit);
                errorMessage.setVisible(!shipsFit);
            }
        });

        size6 = new JCheckBox("Groesse 6 Anzahl: " + BattleShip.shipCount6);
        clayout.gridx = 2;
        clayout.gridwidth = 1;
        clayout.gridy = 4;
        create.add(size6, clayout);

        s6 = new JSpinner(new SpinnerNumberModel((0),0,null,1));
        clayout.gridx = 3;
        clayout.gridwidth = 1;
        clayout.gridy = 4;
        create.add(s6, clayout);
        s6.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                size6.setText("Groesse 6 Anzahl: " + s6.getValue());
                int value6 = (Integer) s6.getValue();
                BattleShip.shipCount6 = value6;
                boolean shipsFit = shipsunder30();
                startGame.setEnabled(shipsFit);
                errorMessage.setVisible(!shipsFit);
            }
        });


        create.setVisible(true);
    }

}
