import javax.swing.*;
import java.awt.*;

public class TemperatureConverter extends JFrame {

    private JTextField temperatureField;
    private JComboBox<String> fromUnit;
    private JComboBox<String> toUnit;
    private JLabel resultLabel;

    public TemperatureConverter() {
        setTitle("Temperature Converter");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        panel.setBackground(new Color(230, 240, 255));

        JLabel title = new JLabel("Temperature Converter", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        temperatureField = new JTextField();
        temperatureField.setFont(new Font("Arial", Font.PLAIN, 16));

        String[] units = {"Celsius", "Fahrenheit", "Kelvin"};
        fromUnit = new JComboBox<>(units);
        toUnit = new JComboBox<>(units);
        toUnit.setSelectedItem("Fahrenheit");

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Arial", Font.BOLD, 16));
        convertButton.setBackground(new Color(50, 100, 200));
        convertButton.setForeground(Color.WHITE);

        resultLabel = new JLabel("Result will appear here", JLabel.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        panel.add(title);
        panel.add(new JLabel("Enter Temperature:"));
        panel.add(temperatureField);
        panel.add(fromUnit);
        panel.add(toUnit);
        panel.add(convertButton);

        add(panel, BorderLayout.CENTER);
        add(resultLabel, BorderLayout.SOUTH);

        convertButton.addActionListener(e -> convertTemperature());
        setVisible(true);
    }

    private void convertTemperature() {
        try {
            double temperature =
                Double.parseDouble(temperatureField.getText().trim());

            String from = (String) fromUnit.getSelectedItem();
            String to = (String) toUnit.getSelectedItem();

            double celsius;
            switch (from) {
                case "Fahrenheit":
                    celsius = (temperature - 32) * 5.0 / 9.0;
                    break;
                case "Kelvin":
                    celsius = temperature - 273.15;
                    break;
                default:
                    celsius = temperature;
            }

            if (celsius < -273.15) {
                JOptionPane.showMessageDialog(
                    this,
                    "Temperature cannot be below absolute zero!",
                    "Invalid Temperature",
                    JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            double result;
            switch (to) {
                case "Fahrenheit":
                    result = (celsius * 9.0 / 5.0) + 32;
                    break;
                case "Kelvin":
                    result = celsius + 273.15;
                    break;
                default:
                    result = celsius;
            }

            String unitSymbol = to.equals("Celsius") ? "°C"
                : to.equals("Fahrenheit") ? "°F" : "K";
            resultLabel.setText(String.format("Result: %.2f %s", result, unitSymbol));

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid number!",
                "Input Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TemperatureConverter::new);
    }
}
