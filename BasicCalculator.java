// basic calculator with interface

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BasicCalculator extends JFrame implements ActionListener {
    private JTextField displayField;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "x",
            "C", "0", "=", "/"
    };

    private double firstNum;
    private String operator;

    public BasicCalculator() {
        setTitle("Calculator");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        displayField = new JTextField();
        displayField.setEditable(false);
        displayField.setFont(new Font("Consolas",Font.BOLD,40));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setPreferredSize(new Dimension(300, 50));
        add(displayField, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            if (buttonLabels[i].equals("+") || buttonLabels[i].equals("-") ||
                    buttonLabels[i].equals("x") || buttonLabels[i].equals("/")) {
                buttons[i].setBackground(Color.ORANGE);
                buttons[i].setForeground(Color.WHITE);
                buttons[i].setOpaque(true);
                buttons[i].setBorderPainted(false);
                buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
                buttons[i].setFocusPainted(false);
                buttons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            } else {
                buttons[i].setBackground(Color.WHITE);
                buttons[i].setOpaque(true);
                buttons[i].setBorderPainted(false);
                buttons[i].setFont(new Font("Arial", Font.PLAIN, 20));
                buttons[i].setFocusPainted(false);
                buttons[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            }
            buttonPanel.add(buttons[i]);
        }

        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "+":
            case "-":
            case "x":
            case "/":
                firstNum = Double.parseDouble(displayField.getText());
                operator = command;
                displayField.setText("");
                break;
            case "=":
                double secondNum = Double.parseDouble(displayField.getText());
                double result = 0;
                switch (operator) {
                    case "+":
                        result = firstNum + secondNum;
                        break;
                    case "-":
                        result = firstNum - secondNum;
                        break;
                    case "x":
                        result = firstNum * secondNum;
                        break;
                    case "/":
                        if (secondNum != 0)
                            result = firstNum / secondNum;
                        else
                            JOptionPane.showMessageDialog(this, "Error: Division by zero!");
                        break;
                }
                displayField.setText(String.valueOf(result));
                break;
            case "C":
                displayField.setText("");
                break;
            default:
                displayField.setText(displayField.getText() + command);
                break;
        }
    }

    public static void main(String[] args) {
        new BasicCalculator();
    }
}
