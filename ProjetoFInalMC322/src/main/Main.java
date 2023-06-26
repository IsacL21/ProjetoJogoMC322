package main;

import java.io.IOException;

import javax.swing.JFrame;

import arquivos.Arquivos;

public class Main {
	
	public static void main(String[] args) {
		//inicia e tenta carregar arquivos
		Arquivos arquivosObjeto = new Arquivos();
		try {
			arquivosObjeto.loadFiles();
		
		//inicia a janela
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Jogo 2D");
		window.setLocationRelativeTo(null);
		
		int mapa_atual = 1;
		//cria engine
		Engine engine = new Engine(mapa_atual);
		
		//adiciona e ajusta a janela ao gamepanel
		
		window.add(engine.getGamePanel());
		window.pack();
		
		//inicia looping do jogo
		engine.startGameThread();
			
		window.setVisible(true);
		
		}catch (IOException e) {
			System.out.println("Erro ao carregar as imagens!");
		}
	}

}
