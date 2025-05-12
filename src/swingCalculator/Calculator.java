package swingCalculator;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Calculator extends JFrame implements ActionListener {
	private JTextField textField;
    private String operator;
    private double num1, num2, result;

    public Calculator() {
        setTitle("Simple Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.BOLD, 24));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 22));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
    	String input = e.getActionCommand();

        // If the input is a number (0-9), append it to the text field
        if (input.matches("[0-9]")) {
            textField.setText(textField.getText() + input);
        }
        // If the input is 'C' (Clear), reset everything
        else if (input.equals("C")) {
            textField.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
        // If the input is '=', perform the calculation
        else if (input.equals("=")) {
            try {
                // Get the second number entered
                num2 = Double.parseDouble(textField.getText());
                
                // Perform the calculation based on the operator
                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            textField.setText("Cannot divide by 0");
                            return;
                        } else {
                            result = num1 / num2;
                        }
                        break;
                    default:
                        textField.setText("Error");
                        return;
                }

                // Show the result
                textField.setText(String.valueOf(result));
                num1 = result; // Save the result for subsequent calculations
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }
        // For all other inputs (operators), store the first number and operator
        else {
            try {
                // Store the first number and operator
                num1 = Double.parseDouble(textField.getText());
                operator = input;

                // Clear the text field for the next number
                textField.setText("");
            } catch (Exception ex) {
                textField.setText("Error");
            }
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
	