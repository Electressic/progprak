package progprak;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainMenu extends Component{
    private JFrame frame;
    static final int gridmin = 5;
    static final int gridmax = 30;
    static final int gridinit = 15; // variablen für slider
    final JFileChooser fc = new JFileChooser();
    int returnVal = fc.showOpenDialog(fc);
    JTextArea log;
    static private final String newline = "\n"; // shit für fileexplorer
    private JButton loadButton;
    private JButton startButton;
    private JButton mpButton; //Buttons im Hauptmenü so far

    public MainMenu() 
    {
        log = new JTextArea(5,20); // log für File explorer shit?

        // Creating the Window:
        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(300 ,200));

        // Creating Layout mit den Weights und Insets für abstand
        JPanel menu = new JPanel(new GridBagLayout());
        frame.setContentPane(menu);

        GridBagConstraints mlayout = new GridBagConstraints();
        mlayout.weightx = 1;
        mlayout.weighty = 1;
        mlayout.fill = GridBagConstraints.HORIZONTAL;
        mlayout.insets = new Insets(5,5,5,5);

        //title. simple.
        JLabel title = new JLabel("BATTLESHIP");
        mlayout.gridx = 0;
        mlayout.gridwidth = 3;
        mlayout.gridy = 0;
        menu.add(title, mlayout);

        //start button. hat noch keine funktion, wird alles rausschmeißen und neues layout mit grid etc laden
        JButton startButton = new JButton("START");
        mlayout.gridx = 0;
        mlayout.gridwidth = 1;
        mlayout.gridy = 1;
        menu.add(startButton, mlayout);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.getContentPane().remove(startButton);
                frame.getContentPane().remove(loadButton);
                frame.getContentPane().remove(mpButton);
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();

            }
        });

        // load button. opens file explorer für die datei zum laden
        JButton loadButton = new JButton("LOAD");
        mlayout.gridx = 1;
        mlayout.gridwidth = 1;
        mlayout.gridy = 1;
        menu.add(loadButton, mlayout);
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == loadButton) {
                    int returnVal = fc.showOpenDialog(MainMenu.this);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        log.append("Opening: " + file.getName() + "." + newline);
                    } else {
                        log.append("Open command cancelled by user." + newline);
                    }
                }
            }
        });

        //popup menu für multiplayer wenn man hosten oder connecten will
        JPopupMenu mpMenu = new JPopupMenu();
        mlayout.gridx = 2;
        mlayout.gridwidth = 1;
        mlayout.gridy = 1;
        mpMenu.add(new JMenuItem(new AbstractAction("HOST") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"Host selected");
            }
        }));
        mpMenu.add(new JMenuItem(new AbstractAction("CONNECT") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,"connect selected");
            }
        }));
        JButton mpButton = new JButton("Multiplayer");
        menu.add(mpButton, mlayout);
        mpButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mpMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        frame.setVisible(true);
    }
    public void loadCreateScreen(){
        JPanel create = new JPanel(new GridBagLayout());
        frame.setContentPane(create);

        GridBagConstraints clayout = new GridBagConstraints();
        clayout.weightx = 1;
        clayout.weighty = 1;
        clayout.fill = GridBagConstraints.HORIZONTAL;
        clayout.insets = new Insets(5,5,5,5);

        JLabel title = new JLabel("BATTLESHIP");
        clayout.gridx = 0;
        clayout.gridwidth = 3;
        clayout.gridy = 0;
        create.add(title, clayout);

        JSlider gridgroesse = new JSlider(JSlider.HORIZONTAL, gridmin, gridmax, gridinit);
        gridgroesse.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                gridgroesse.setMajorTickSpacing(1);
                gridgroesse.setMinorTickSpacing(1);
                gridgroesse.setPaintTicks(true);
                gridgroesse.setPaintLabels(true);
            }
        });
        create.add(gridgroesse, clayout);

        // glaube hier bieten sich keine Buttons an. muss mir mal forms oder so anschauen wo man dann schön auswählen kann
        // mit checkbox welche größe und dann mit buttons oder so einstellt wieviele
        JButton shipsize = new JButton("Schiffgröße?");
        create.add(mpButton, clayout);

        JButton mpButton = new JButton("weiß nicht");
        create.add(mpButton, clayout);

        frame.setVisible(true);
    }
}