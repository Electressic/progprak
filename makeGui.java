import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class makeGui {
    public static int Rows = 10;
    public static int Cols = 10;
    public static int boardSize = 10;
    JButton[][] gameBoard = new JButton[Rows][Cols];
    public static int computerShips;
    JPanel content = new JPanel();
    JPanel enemycontent = new JPanel();
    JPanel header = new JPanel();
    private JFrame main = new JFrame("Game");
    JLabel output = new JLabel("test");
    JCheckBox state = new JCheckBox("state");
    JButton next = new JButton("next");
    String gameState;

    Map<String, Integer[]> aMap = new HashMap<String, Integer[]>();

    class ValuePair
    {
        public String Inhalt;
        public int x;
        public int y;
    }

    public makeGui(){

        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setMinimumSize(new Dimension(600,600));
        main.setLocationRelativeTo(null);
        main.add(content, BorderLayout.LINE_END);
        main.add(enemycontent, BorderLayout.LINE_START);
        main.add(header, BorderLayout.PAGE_START);
        header.add(output);
        header.add(state);
        header.add(next);
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == next){
                    fillEnemyBoard();
                }
            }
        });
        state.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (state.isSelected()) {
                    gameState ="setzen";
                    output.setText("setzen");
                } else {
                    gameState = "battle";
                    output.setText("battle");
                }
            }
        });
        content.setLayout(new GridLayout(boardSize,boardSize,1,1));
        enemycontent.setLayout(new GridLayout(boardSize,boardSize,1,1));
        fillBoard();
        main.pack();
        main.setVisible(true);
    }

    private void fillEnemyBoard() {

    }
    void AusgabeMap()
    {
        Map<String, Integer[]> aVal = new HashMap<String, Integer[]>();
        for (Map.Entry ent : aMap.entrySet())
        {
            Integer[] Wert = (Integer[])ent.getValue();
            System.out.println("Hashmap: " + ent.getKey() + " on "+ Wert[0] + "/"+ Wert[1]);
        }
    }
    public void fillBoard() {
        Integer[] arrEintrag = new Integer[2];
        for(int i = 0; i < Rows; i++) {
            for (int j = 0; j < Cols; j++) {
                JButton button = new JButton(i + "/" + j);
                gameBoard[i][j] = button;
                gameBoard[i][j].setActionCommand("Wasser");
                int finalI = i;
                int finalJ = j;
                gameBoard[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (gameState == "setzen") {
                            setShip(e.getSource());
                        }else if (gameState == "battle") {
                            if (e.getSource().equals(gameBoard[finalI][finalJ])){
                                if(e.getActionCommand().equals("Wasser")) {
                                    gameBoard[finalI][finalJ].setActionCommand("Miss");
                                    saveMap(e.getSource());
                                    AusgabeMap();
                                    //liste.set(0, (gameBoard[finalI][finalJ].getActionCommand(), ggameBoardameBoard[finalI], gameBoard[finalJ]));
                                    //liste.set(gameBoard[finalI][finalJ], gameBoard[finalI][finalJ].getActionCommand(),);
                                    gameBoard[finalI][finalJ].setBackground(Color.black);
                                }
                                if (e.getSource().equals(gameBoard[finalI][finalJ]) && e.getActionCommand().equals("Schiff")){
                                    button.setActionCommand("Hit");
                                    button.setBackground(Color.red);
                                    gameBoard[finalI][finalJ].setActionCommand("Hit");
                                    saveMap(e.getSource());
                                    AusgabeMap();
                                }
                            }
                        }
                    }

                    private void setShip(Object c) {
                        for (int x = 0; x < gameBoard.length; x++) {
                            for (int y = 0; y < gameBoard[0].length; y++) {
                                if (c.equals(gameBoard[x][y])) {
                                    gameBoard[finalI][finalJ].setActionCommand("Schiff");
                                }
                            }
                        }
                    }
                    private void saveMap(Object d) {
                        for (int x = 0; x < gameBoard.length; x++) {
                            for (int y = 0; y < gameBoard[0].length; y++) {
                                if (d.equals(gameBoard[x][y])) {
                                    arrEintrag[0] = x;
                                    arrEintrag[1] = y;
                                    aMap.put(gameBoard[x][y].getActionCommand(), arrEintrag );
                                }
                            }
                        }
                    }
                });
                content.add(gameBoard[i][j]);
            }
        }
    }
}
