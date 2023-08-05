import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGeneratorWidget extends JFrame {
    private JTextField inputField;
    private JButton startButton;
    private JLabel numberLabel;

    public NumberGeneratorWidget() {
        setTitle("Number Generator Widget");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 150));
        System.out.println();

        inputField = new JTextField(10);
        startButton = new JButton("Start");
        numberLabel = new JLabel();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText();
                try {
                    int inputNumber = Integer.parseInt(inputText);
                    int randomNumber = (int) (Math.random() * (inputNumber + 1));
                    animateNumber(randomNumber);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(NumberGeneratorWidget.this, "Invalid input. Please enter a number.");
                }
            }
        });

        setLayout(new FlowLayout());
        add(inputField);
        add(startButton);
        add(numberLabel);
        pack();
    }

    private void animateNumber(int targetNumber) {
        Timer timer = new Timer(50, null);
        timer.addActionListener(new ActionListener() {
            int currentNumber = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentNumber <= targetNumber) {
                    numberLabel.setText(String.valueOf(currentNumber));
                    currentNumber++;
                } else {
                    numberLabel.setText(String.valueOf(targetNumber));
                    timer.stop();
                }
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGeneratorWidget widget = new NumberGeneratorWidget();
                widget.setVisible(true);
            }
        });
    }
}

