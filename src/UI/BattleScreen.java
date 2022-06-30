package progprak.src.UI;

import javax.swing.*;
import java.awt.*;

public class BattleScreen {
    JFrame battleFrame = new JFrame("BATTLE");
    JButton[][] cells = new JButton[10][10];
    Font gridFont = new Font(Font.DIALOG, Font.PLAIN, 24);
    JPanel player1 = new JPanel();
    JPanel player2 = new JPanel();
    JPanel status = new JPanel();
    public BattleScreen () {
        battleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        battleFrame.setMinimumSize(new Dimension(600 ,600));
        battleFrame.setLocationRelativeTo(null);
        battleFrame.add(player1, BorderLayout.LINE_START);
        battleFrame.add(player2, BorderLayout.LINE_END);
        battleFrame.add(status, BorderLayout.CENTER);

        player1.setLayout(new GridLayout(10, 10,1,1));
        player1.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        player1.setBorder(BorderFactory.createLineBorder(Color.black));

        player2.setLayout(new GridLayout(10,10,1,1));
        player2.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        player2.setBorder(BorderFactory.createLineBorder(Color.black));

        status.setLayout(new GridLayout(10,10,1,1));
        status.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));
        status.setBorder(BorderFactory.createLineBorder(Color.black));

        player1Grid();
        player2Grid();
        Status();

        battleFrame.pack();
        battleFrame.setVisible(true);
    }
    public void player1Grid(){
        for(int row = 0; row < cells.length; row++) {
            for(int column = 0; column < cells[row].length; column++) {
                cells[row][column] = new JButton("");
                cells[row][column].setFont(gridFont);
                cells[row][column].setBackground(Color.GRAY);
                player1.add(cells[row][column]);
            }
        }
    }
    public void Status(){

    }
    public void player2Grid(){
        for(int row = 0; row < cells.length; row++) {
            for(int column = 0; column < cells[row].length; column++) {
                cells[row][column] = new JButton("");
                cells[row][column].setFont(gridFont);
                cells[row][column].setBackground(Color.GRAY);
                player2.add(cells[row][column]);
            }
        }
    }
}
