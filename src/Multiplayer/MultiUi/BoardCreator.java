package Multiplayer.MultiUi;

import javax.swing.*;
import java.awt.*;

public class BoardCreator extends JPanel {
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
    BoardCreator() {
        setLayout(new GridLayout(4,1,20,50));
        JLabel welcome = new JLabel("BATTLESHIP");
        welcome.setForeground(Color.white);
        welcome.setFont(new Font("Serif", Font.PLAIN, 50));
        welcome.setHorizontalAlignment(JLabel.CENTER);
        add(welcome);
    }
}