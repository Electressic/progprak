package progprak.src.Multiplayer.MultiUi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiplayerUi {
    //create new MainFrame
    JFrame main = new JFrame("TEST");
    // Panel for adding Content
    JPanel content;
    public MultiplayerUi() {
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
        content.add(new BoardCreator());
        main.add(content);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }
}
