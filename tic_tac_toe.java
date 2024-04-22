
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tic_tac_toe extends JFrame implements ActionListener {
    private JButton[][] buttons;
    private boolean playerX;
    private JLabel label;

    public tic_tac_toe() {
        setTitle("Tic Tac Toe");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        playerX = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 90));
                buttons[i][j].setFocusable(false);
                buttons[i][j].setBackground(Color.BLACK);
                buttons[i][j].addActionListener(this);
                gridPanel.add(buttons[i][j]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);

        label = new JLabel("Player X's turn");
        add(label, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (buttonClicked.getText().equals("")) {
            if (playerX) {
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.RED); // Set foreground color to red for X
                label.setText("Player O's turn");
            } else {
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.GREEN); // Set foreground color to green for O
                label.setText("Player X's turn");
            }
            playerX = !playerX;

            if (checkWin()) {
                String winner = playerX ? "O" : "X";
                JOptionPane.showMessageDialog(this, "Player " + winner + " wins!");
                resetGame();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetGame();
            }
        }
    }

    private boolean checkWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") && buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                    buttons[i][0].getText().equals(buttons[i][2].getText())) {
                return true;
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[0][i].getText().equals("") && buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                    buttons[0][i].getText().equals(buttons[2][i].getText())) {
                return true;
            }
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") && buttons[0][0].getText().equals(buttons[1][1].getText()) &&
                buttons[0][0].getText().equals(buttons[2][2].getText())) {
            return true;
        }
        if (!buttons[0][2].getText().equals("") && buttons[0][2].getText().equals(buttons[1][1].getText()) &&
                buttons[0][2].getText().equals(buttons[2][0].getText())) {
            return true;
        }

        return false;
    }

    private boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setForeground(Color.BLACK); // Reset foreground color to black
            }
        }
        playerX = true;
        label.setText("Player X's turn");
    }

    public static void main(String[] args) {
        new tic_tac_toe();
    }
}
