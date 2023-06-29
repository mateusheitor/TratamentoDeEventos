package atv6;

import javax.swing.*;

public class JogoDaVelha {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null); // centraliza a janela na tela
        
        JPanel panel = new JPanel(); // Corrigido o nome da classe para JPanel
        frame.add(panel);
        
        frame.setVisible(true);
    }
}
