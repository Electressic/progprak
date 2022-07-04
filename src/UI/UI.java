package UI;

import Logik.Ship;
import Logik.Spielfeld;
import Multiplayer.MultiUi.MultiplayerUi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import java.util.stream.IntStream;

public class UI {

    //create new MainFrame
    JFrame main = new JFrame("Battleship");
    // Panel for adding Content
    JPanel content;

    //create Spielfeld and Ship Class for ui
    Spielfeld spielfeld;
    Ship ship;
    MultiplayerUi mpUi;

    //Constructor that creates the UI
    public UI() {
        ship = new Ship();
        spielfeld = new Spielfeld();
        createGUI();
    }
    //creating UI
    private void createGUI() {
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setPreferredSize(new Dimension(800,700));
        content = new JPanel(new BorderLayout());
        content.add(new MainMenu(content, ship, spielfeld));
        main.add(content);
        main.pack();
        main.setLocationRelativeTo(null);
        main.setVisible(true);
    }
}
