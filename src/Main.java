import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    JFrame frame;
    JPanel pMain;
    JButton[][] buttons;
    boolean isPlayerOne;

    public Main() {
        initComponents();
        configureFrame();
    }

    private void initComponents() {
        frame = new JFrame("TicTacToe");
        buttons = new JButton[3][3];

        isPlayerOne = Math.random() >= 0.5;
        frame.setTitle((isPlayerOne) ? "X" : "O");
    }

    private void configureFrame() {
        pMain = new JPanel();
        pMain.setLayout(new GridLayout(3, 3, 1, 1));
        pMain.setPreferredSize(new Dimension(300, 300));

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j] = new JButton();
                pMain.add(buttons[i][j]);

                buttons[i][j].addActionListener(this::actionPerformed);
                buttons[i][j].setUI(new BasicButtonUI());
                buttons[i][j].setActionCommand(i + "," + j);
                buttons[i][j].setFont(new Font(null, Font.BOLD, 30));
            }
        }

        frame.add(pMain);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new Main();
    }

    public void actionPerformed(ActionEvent e) {
        int i = Integer.parseInt(e.getActionCommand().split(",")[0]);
        int j = Integer.parseInt(e.getActionCommand().split(",")[1]);

        if (buttons[i][j].getText().isEmpty()) {

            if (isPlayerOne) {
                buttons[i][j].setText("X");
                isPlayerOne = false;
            } else {
                buttons[i][j].setText("O");
                isPlayerOne = true;
            }

            frame.setTitle((isPlayerOne) ? "X" : "O");
            checkWinner();

        }
    }

    private void checkWinner() {
        String winner = "";

        // For Lines
        for (int k = 0; k < 3; k++) {
            if (buttons[k][0].getText().isEmpty()) {
                continue;
            }

            if (buttons[k][0].getText().equals(buttons[k][1].getText())
                    && buttons[k][1].getText().equals(buttons[k][2].getText())) {
                winner = buttons[k][0].getText();
            }
        }

        // For Columns
        for (int k = 0; k < 3; k++) {
            if (buttons[0][k].getText().isEmpty()) {
                continue;
            }

            if (buttons[0][k].getText().equals(buttons[1][k].getText())
                    && buttons[1][k].getText().equals(buttons[2][k].getText())) {
                winner = buttons[0][k].getText();
            }
        }

        // Main Diagonal
        if (buttons[0][0].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][2].getText())) {
            winner = buttons[0][0].getText();
        }

        // Secundary Diagonal
        if (buttons[0][2].getText().equals(buttons[1][1].getText())
                && buttons[1][1].getText().equals(buttons[2][0].getText())) {
            winner = buttons[2][0].getText();
        }

        // Checkingn the winner
        if (!winner.isEmpty()) {
            JOptionPane.showMessageDialog(null, winner + " WON !");
            reset();
        }

        boolean draw = true;
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                if (buttons[i][j].getText().isEmpty()) {
                    draw = false;
                }
            }
        }

        if (draw) {
            JOptionPane.showMessageDialog(null, " DRAW !");
            reset();
        }

    }

    // Reseting the game;
    private void reset() {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("");
            }
        }
    }

}
