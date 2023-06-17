package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		//inicia a janela
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
	}

}
