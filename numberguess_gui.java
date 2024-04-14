import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class numberguess_gui extends JFrame implements ActionListener {
    private JTextField guessField;
    private JLabel infoLabel;
    private JButton guessButton;
    private Random random;
    private int targetNumber;
    private int maxAttempts;
    private int attemptsLeft;
    private int score;

    public numberguess_gui() {
        ImageIcon icon =new ImageIcon("D:\\javacodes\\javacoding\\src\\icon.png");  //you can set the icon as you like

        setIconImage(icon.getImage());

        setTitle("Guess the Number Game");
        setSize(1000, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1,5,0));

        random = new Random();
        maxAttempts = 5;
        score = 0;

        infoLabel = new JLabel("I've picked a number between 1 and 100. Can you guess what it is? You have " + maxAttempts + " attempts.");
        add(infoLabel);

        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(40,40));
        guessField.setFont(new Font("Consolas",Font.PLAIN,50));
        guessField.setForeground(Color.BLACK);
        guessField.setBackground(Color.lightGray);
        guessField.setCaretColor(Color.BLACK);



        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setFocusable(false);
        guessButton.setBorder(BorderFactory.createEtchedBorder());
        guessButton.setFont(new Font("Comic sans", Font.BOLD,24));
        guessButton.setForeground(Color.white);
        guessButton.setBackground(Color.gray);

        guessButton.addActionListener(this);
        add(guessButton);

        setVisible(true);

        startNewRound();
    }

    private void startNewRound() {
        targetNumber = random.nextInt(100) ;
        attemptsLeft = maxAttempts;
        infoLabel.setText("I've picked a number between 1 and 100. Can you guess what it is? You have " + attemptsLeft + " attempts left.");
        infoLabel.setFont(new Font("Comic Sans",Font.BOLD,21));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attemptsLeft--;

                if (guess == targetNumber) {
                    JOptionPane.showMessageDialog(this, "Congratulations! You guessed the correct number in " + (maxAttempts - attemptsLeft) + " attempts.");
                    score++;
                    startNewRound();
                } else if (guess < targetNumber) {
                    JOptionPane.showMessageDialog(this, "Too low! Try again. You have " + attemptsLeft + " attempts left.");
                } else {
                    JOptionPane.showMessageDialog(this, "Too high! Try again. You have " + attemptsLeft + " attempts left.");
                }

                if (attemptsLeft == 0) {
                    JOptionPane.showMessageDialog(this, "Sorry, you've run out of attempts. The correct number was " + targetNumber + ".");
                    startNewRound();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number.");

            }
            guessField.setText("");
        }
    }

    public static void main(String[] args) {
        new numberguess_gui();

    }
}


