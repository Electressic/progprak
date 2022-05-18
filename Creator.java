package progprak;

import javax.swing.*;
import java.awt.*;

public class Creator {
    JFrame create = new JFrame();
    JLabel test = new JLabel("hi");

    public Creator(){
        test.setAlignmentX(Component.CENTER_ALIGNMENT);
        create.add(test);
        create.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        create.setMinimumSize(new Dimension(300 ,200));
        create.setVisible(true);
    }
    public boolean canShipsFitOnBoard() {
        int totalShipSize = (BattleShip.)
    }
}
