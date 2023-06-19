package main;

import java.io.IOException;

import javax.swing.JFrame;

import arquivos.Arquivos;

public class Main {

	public static void main(String[] args) {
		//inicia a janela
		
		Arquivos arquivosObjeto = new Arquivos();
		try {
			arquivosObjeto.loadFiles();
			
			JFrame window = new JFrame();
			window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			window.setResizable(false);
			window.setTitle("Jogo 2D");
			window.setLocationRelativeTo(null);
			
			//cria, adiciona e ajusta a janela ao gamepanel
			GamePanel gamePanel = new GamePanel();
			window.add(gamePanel);
			window.pack();
			
			//inicia looping do jogo
			
			gamePanel.startGameThread();
			
			window.setVisible(true);
		}catch (IOException e) {
			System.out.println("Erro ao carregar as imagens!");
		}
		
	}

}
