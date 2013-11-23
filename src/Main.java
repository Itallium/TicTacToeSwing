import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static JButton[][] field = new JButton[Field.FIELD_SIZE][Field.FIELD_SIZE];
    public static int difficulty;
    public static String winner = "";
    public static JFrame frame;
    public static JFrame startFrame = new JFrame("Difficulty");

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                startTicTacToe();
            }
        });
    }

    public static void startTicTacToe() {
        startFrame.setPreferredSize(new Dimension(30, 140));

        JPanel startPanel = new JPanel();
        startPanel.setPreferredSize(new Dimension(30, 30));
        startPanel.setLayout(new GridLayout(3, 0));
        startFrame.setContentPane(startPanel);

        JLabel label = new JLabel("Select difficulty:");
        label.setVerticalAlignment(AbstractButton.CENTER);
        label.setHorizontalAlignment(AbstractButton.CENTER);
        startPanel.add(label);

        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 1;
//                startFrame.dispatchEvent(new WindowEvent(startFrame, WindowEvent.WINDOW_CLOSING));
                startFrame.setVisible(false);
                createField("Easy");
            }
        });
        startPanel.add(easyButton);

        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                difficulty = 2;
//                startFrame.dispatchEvent(new WindowEvent(startFrame, WindowEvent.WINDOW_CLOSING));
                startFrame.setVisible(false);
                createField("Hard");
            }
        });
        startPanel.add(hardButton);

        startFrame.setLocationRelativeTo(null);
        startFrame.setResizable(false);
        startFrame.pack();
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);
    }

    public static void createField(String difficulty) {

        frame = new JFrame("Tic-Tac-Toe Swing Edition. Difficulty is " + difficulty);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(50, 50));
        panel.setLayout(new GridLayout(Field.FIELD_SIZE, Field.FIELD_SIZE));
        frame.getContentPane().add(panel);

        for (int i = 0; i < Field.FIELD_SIZE; i++) {
            for (int j = 0; j < Field.FIELD_SIZE; j++) {
                final int x = i;
                final int y = j;
                JButton currentButton = new JButton("");
                field[j][i] = currentButton;
                currentButton.setPreferredSize(new Dimension(50, 50));
                currentButton.setVerticalTextPosition(AbstractButton.CENTER);
                currentButton.setHorizontalTextPosition(AbstractButton.CENTER);
                currentButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        makeAMove(y, x);
                    }
                });
                panel.add(currentButton);
                Field.field[j][i] = Field.DEFAULT_SYMBOL;
            }
        }

        frame.setPreferredSize(new Dimension(150, 200));

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setResizable(false);
        frame.setVisible(true);
    }

    public static void makeAMove(int y, int x) {

        if(CheckGame.CanMove()) {
            HumanMove.move(y, x);
            if(!HumanMove.moveComplete) {
                return;
            }
        } else {
            winner = "Field is full";
            winnerGUI(winner);
            return;
        }

        if(CheckGame.check() != Field.DEFAULT_SYMBOL) {
            winner = String.valueOf(CheckGame.check());
            winnerGUI(winner);
            return;
        }

        if(CheckGame.CanMove()) {
            CompMove.move();
        } else {
            winner = "Field is full";
            winnerGUI(winner);
            return;
        }

        if(CheckGame.check() != Field.DEFAULT_SYMBOL) {
            winner = String.valueOf(CheckGame.check());
            winnerGUI(winner);
            return;
        }

        if(!CheckGame.CanMove()) {
            winner = "Field is full";
            winnerGUI(winner);
            return;
        }

    }

    public static void winnerGUI(String winner) {
        for (int i = 0; i < Field.FIELD_SIZE; i++) {
            for (int j = 0; j < Field.FIELD_SIZE; j++) {
                field[j][i].setEnabled(false);
            }
        }

        final JFrame winnerFrame = new JFrame("Winner");
        winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel winnerPanel = new JPanel();
        winnerPanel.setPreferredSize(new Dimension(100, 140));
        winnerFrame.setContentPane(winnerPanel);

        JLabel text = new JLabel();
        text.setHorizontalAlignment(AbstractButton.CENTER);
        text.setVerticalAlignment(AbstractButton.CENTER);
        if (winner.equals(String.valueOf(Field.COMPUTER_SYMBOL))) {
            text.setText("Computer is win.");
        } else if (winner.equals(String.valueOf(Field.HUMAN_SYMBOL))) {
            text.setText("Human is win.");
        } else {
            text.setText("Field is full.");
        }
        winnerPanel.add(text);

        JButton retryButton = new JButton("Retry");
        retryButton.setPreferredSize(new Dimension(100, 30));
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winnerFrame.setVisible(false);
                Field.clearField();
                for (int i = 0; i < Field.FIELD_SIZE; i++) {
                    for (int j = 0; j < Field.FIELD_SIZE; j++) {
                        field[j][i].setEnabled(true);
                    }
                }
            }
        });
        winnerFrame.add(retryButton);

        JButton difficultyButton = new JButton("Select difficulty");
        difficultyButton.setPreferredSize(new Dimension(100, 30));
        difficultyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                winnerFrame.setVisible(false);
                startFrame.setVisible(true);
            }
        });
        winnerFrame.add(difficultyButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(100, 30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        winnerPanel.add(exitButton);

        winnerFrame.pack();
        winnerFrame.setLocationRelativeTo(null);
        winnerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winnerFrame.setVisible(true);
    }
}
