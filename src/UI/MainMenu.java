package progprak.src.UI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class MainMenu extends Component{
    private JFrame frame = new JFrame("Game");
    private JButton startButton;
    private JButton mpButton; //Buttons im Hauptmenü so far

    public MainMenu() 
    {
        // Creating the Window:
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setMinimumSize(new Dimension(300 ,200));
        frame.setVisible(true);

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

        startButton = new JButton("START");
        mlayout.gridx = 0;
        mlayout.gridwidth = 1;
        mlayout.gridy = 1;
        menu.add(startButton, mlayout);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==startButton) {
                    frame.dispose();
                    Creator createWindow = new Creator();
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
}