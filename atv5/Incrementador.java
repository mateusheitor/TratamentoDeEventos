package atv5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Incrementador extends JFrame {
    private JLabel label;
    private JTextField textField;
    private JButton incrementarButton;
    private JButton zerarButton;
    private int valor;

    public Incrementador() {
        setTitle("Incrementador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);

        JPanel panel = new JPanel(new GridLayout(2, 2));

        label = new JLabel("Valor: ");
        textField = new JTextField("0");
        textField.setEditable(false);

        incrementarButton = new JButton("Incrementar");
        incrementarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                incrementar();
            }
        });

        zerarButton = new JButton("Zerar");
        zerarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                zerar();
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(incrementarButton);
        panel.add(zerarButton);

        add(panel);
        setVisible(true);

        valor = 0;
        atualizarCampoTexto();
    }

    private void incrementar() {
        valor++;
        atualizarCampoTexto();
    }

    private void zerar() {
        valor = 0;
        atualizarCampoTexto();
    }

    private void atualizarCampoTexto() {
        textField.setText(Integer.toString(valor));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Incrementador();
            }
        });
    }
}
