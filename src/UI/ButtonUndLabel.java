package UI;

import javax.swing.*;
import java.awt.*;

public interface ButtonUndLabel {
    Color watercolor = new Color(212,241,249);
    Color shipcolor = new Color(78, 78, 76);
    Color sunkcolor = new Color (139,0,0);
    Color hitcolor = Color.black;
    Color misscolor = new Color(19, 23, 128);
    class UiButton extends JButton {
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
    class UiLabel extends JLabel {
        public UiLabel(String text) {
            super(text);
            setForeground(Color.white);
            setFont(new Font("Serif", Font.PLAIN,20).deriveFont(20.0f));
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
            setText("~");
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
