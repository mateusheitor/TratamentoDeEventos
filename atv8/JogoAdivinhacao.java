package atv8;

import java.util.Scanner;
import javax.sound.sampled.*;

import java.io.File;

public class JogoAdivinhacao {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			String resposta;

			do {
			    System.out.println("Bem-vindo ao Jogo da Adivinhação!");
			    System.out.println("Pense em um número de 1 a 100 e eu vou tentar adivinhar qual é. Vamos lá!\n");

			    int limiteInferior = 1;
			    int limiteSuperior = 100;
			    int palpite;
			    boolean acertou = false;

			    while (!acertou) {
			        palpite = (limiteInferior + limiteSuperior) / 2;

			        System.out.println("O número que você pensou é maior, menor ou igual a " + palpite + "?");
			        resposta = scanner.nextLine();

			        if (resposta.equalsIgnoreCase("maior")) {
			            limiteInferior = palpite + 1;
			        } else if (resposta.equalsIgnoreCase("menor")) {
			            limiteSuperior = palpite - 1;
			        } else if (resposta.equalsIgnoreCase("igual")) {
			            acertou = true;
			            System.out.println("\nÓtimo! Eu adivinhei. O número que você pensou é " + palpite + "!\n");

			            try {
			                File audioFile = new File("C:\\developer\\IFES\\atividade\\src\\atv8\\acertou.wav");
			                playAudio(audioFile);
			            } catch (Exception e) {
			                System.out.println("Ocorreu um erro ao reproduzir o áudio: " + e.getMessage());
			            }
			        } else {
			            System.out.println("\nDesculpe, não entendi sua resposta. Por favor, digite 'maior', 'menor' ou 'igual'.\n");
			        }
			    }

			    System.out.println("Fim do jogo. Se quiser jogar novamente, digite 'começar'.");
			    resposta = scanner.nextLine();
			} while (resposta.equalsIgnoreCase("começar"));
		}
        System.out.println("\nObrigado por jogar! Até a próxima.");
    }

    public static void playAudio(File audioFile) throws Exception {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    }
}
