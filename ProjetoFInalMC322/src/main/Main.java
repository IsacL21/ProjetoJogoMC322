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
		
		//cria engine
		Engine engine = new Engine();
		
		//adiciona e ajusta a janela ao gamepanel
		
		window.add(engine.getGamePanel());
		window.pack();
		
		//inicia looping do jogo
		engine.startGameThread();
		
		
		window.setVisible(true);
	}

}
