
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculadoraConComboYLista extends JFrame {
    private JComboBox<String> operadorCombo;
    private JList<String> historialLista;
    private DefaultListModel<String> historialModel;
    private JTextField campo1, campo2, resultado;

    public CalculadoraConComboYLista() {
        setTitle("Calculadora con JComboBox y JList");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        campo1 = new JTextField(5);
        campo2 = new JTextField(5);

        String[] operadores = { "+", "-", "*", "/" };
        operadorCombo = new JComboBox<>(operadores);

        JButton calcularBtn = new JButton("Calcular");
        resultado = new JTextField(10);
        resultado.setEditable(false);

        historialModel = new DefaultListModel<>();
        historialLista = new JList<>(historialModel);
        JScrollPane scroll = new JScrollPane(historialLista);
        scroll.setPreferredSize(new Dimension(350, 100));

        add(new JLabel("Número 1:"));
        add(campo1);
        add(new JLabel("Operación:"));
        add(operadorCombo);
        add(new JLabel("Número 2:"));
        add(campo2);
        add(calcularBtn);
        add(new JLabel("Resultado:"));
        add(resultado);
        add(new JLabel("Historial:"));
        add(scroll);

        calcularBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double num1 = Double.parseDouble(campo1.getText());
                    double num2 = Double.parseDouble(campo2.getText());
                    String operador = (String) operadorCombo.getSelectedItem();
                    double res = 0;

                    switch (operador) {
                        case "+": res = num1 + num2; break;
                        case "-": res = num1 - num2; break;
                        case "*": res = num1 * num2; break;
                        case "/": res = num2 != 0 ? num1 / num2 : Double.NaN; break;
                    }

                    resultado.setText(String.valueOf(res));
                    historialModel.addElement(num1 + " " + operador + " " + num2 + " = " + res);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Por favor ingrese números válidos.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CalculadoraConComboYLista().setVisible(true);
        });
    }
}
