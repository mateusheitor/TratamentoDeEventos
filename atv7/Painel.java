package atv7;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.*;
import javax.swing.*;

public class Painel extends JPanel {
    private JButton[][] botoes;
    private char turno;
    private Clip clip;

    public Painel() {
        setLayout(new GridLayout(3, 3));
        botoes = new JButton[3][3];
        turno = 'X';

        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                botoes[linha][coluna] = new JButton("");
                botoes[linha][coluna].setFont(new Font("Arial", Font.PLAIN, 40));
                botoes[linha][coluna].addActionListener(new JogadaListener(linha, coluna));
                add(botoes[linha][coluna]);
            }
        }
    }

    private void verificarVencedor() {
        // Verifica linhas
        for (int linha = 0; linha < 3; linha++) {
            if (botoes[linha][0].getText().equals(botoes[linha][1].getText()) &&
                    botoes[linha][0].getText().equals(botoes[linha][2].getText()) &&
                    !botoes[linha][0].getText().isEmpty()) {
                reproduzirSom("C:\\developer\\IFES\\atividade\\src\\jogo\\cavalo.wav");
                JOptionPane.showMessageDialog(this, "Jogador " + turno + " venceu!");
                reiniciarJogo();
                return;
            }
        }

        // Verifica colunas
        for (int coluna = 0; coluna < 3; coluna++) {
            if (botoes[0][coluna].getText().equals(botoes[1][coluna].getText()) &&
                    botoes[0][coluna].getText().equals(botoes[2][coluna].getText()) &&
                    !botoes[0][coluna].getText().isEmpty()) {
                reproduzirSom("C:\\developer\\IFES\\atividade\\src\\jogo\\cavalo.wav");
                JOptionPane.showMessageDialog(this, "Jogador " + turno + " venceu!");
                reiniciarJogo();
                return;
            }
        }

        // Verifica diagonais
        if (botoes[0][0].getText().equals(botoes[1][1].getText()) &&
                botoes[0][0].getText().equals(botoes[2][2].getText()) &&
                !botoes[0][0].getText().isEmpty()) {
            reproduzirSom("C:\\developer\\IFES\\atividade\\src\\jogo\\cavalo.wav");
            JOptionPane.showMessageDialog(this, "Jogador " + turno + " venceu!");
            reiniciarJogo();
            return;
        } else if (botoes[2][0].getText().equals(botoes[1][1].getText()) &&
                botoes[2][0].getText().equals(botoes[0][2].getText()) &&
                !botoes[2][0].getText().isEmpty()) {
            reproduzirSom("C:\\developer\\IFES\\atividade\\src\\jogo\\cavalo.wav");
            JOptionPane.showMessageDialog(this, "Jogador " + turno + " venceu!");
            reiniciarJogo();
            return;
        }

        // Verifica empate
        boolean empate = true;
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (botoes[linha][coluna].getText().isEmpty()) {
                    empate = false;
                }
            }
        }

        if (empate) {
            reproduzirSom("C:\\developer\\IFES\\atividade\\src\\jogo\\empate.wav");
            JOptionPane.showMessageDialog(this, "Empate!");
            reiniciarJogo();
            return;
        }

        // Troca o turno
        turno = (turno == 'X') ? 'O' : 'X';
    }

    private void reiniciarJogo() {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                botoes[linha][coluna].setText("");
            }
        }
        turno = 'X';
    }

    private class JogadaListener implements ActionListener {
        private int linha;
        private int coluna;

        public JogadaListener(int linha, int coluna) {
            this.linha = linha;
            this.coluna = coluna;
        }

        public void actionPerformed(ActionEvent e) {
            if (botoes[linha][coluna].getText().isEmpty()) {
                botoes[linha][coluna].setText(String.valueOf(turno));
                reproduzirSom("C:\\developer\\IFES\\atividade\\src\\jogo\\som.wav");
                verificarVencedor();
            }
        }
    }

    public void reproduzirSom(String nomeArquivo) {
        try {
            File arquivo = new File(nomeArquivo);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(arquivo);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao reproduzir o áudio: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Jogo da Velha");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null); // centraliza a janela na tela

        Painel panel = new Painel();
        frame.add(panel);

        frame.setVisible(true);
    }
}
